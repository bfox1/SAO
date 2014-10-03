package net.teamsao.mcsao.world.generation;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.teamsao.mcsao.helper.FloorPoint;
import net.teamsao.mcsao.helper.StructureGenHelper;
import net.teamsao.mcsao.init.SAOBlocks;

public class SAOBiomeGen extends BiomeGenBase
{
	public Block wallBlock;
	private int wallSegmentHeight = 20;
	private int wallThickness = 5;
	private int wallStart = 999;
	private int wallEnd = wallStart+wallThickness;
	private int floorSeparation = ((wallStart*2)+512);
	private int floorsEnd = (floorSeparation*10)+wallEnd;
	
	private ArrayList<FloorPoint> floorCenters = getFloorCenterPoints();
	private FloorPoint currentFloor;
	/**
	 * 
	 * Attempts to create a biome more friendly for building with no blocks anywhere except the portal.
	 * Disabled mob spawning, rain, and snow, and attempted to replace any blocks spawned with air.
	 * 
	 * In addition, the "height" is set to reduce noise in the case that we want to add terrain back
	 * to the world outside the tower. This can be modified if we want to.
	 * 
	 * @param biomeID
	 * @param registerBiome
	 */
	public SAOBiomeGen(int biomeID, boolean registerBiome)
	{
		super(biomeID, registerBiome);
		setHeight(new BiomeGenBase.Height(-0.5F, 0F));
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.topBlock = SAOBlocks.AincradGrassBlock;
		this.fillerBlock = SAOBlocks.AincradDirtBlock;
		this.wallBlock = SAOBlocks.CrystalStone;
		this.enableRain = false;
		this.enableSnow = false;
		this.setBiomeName("Void World");
	}
	
	/**
	 * Ian - Overriding this method is an attempt to prevent mobs from spawning in the world while we build it. Hopefully it works.
	 */
	@Override
	public float getSpawningChance()
	{
		return 0.0F;
	}
	
	@Override
	public void genTerrainBlocks(World world, Random random, Block[] blocks, byte[] metadata, int x, int z, double noiseGenSeed)
	{
		this.genVoidTerrain(world, random, blocks, metadata, x, z, noiseGenSeed);
	}
	
	/**
	 * Used as a semi-weird way to make a cone underneath floor 1. This works specifically only if you use a cylindrical shape for whatever structure and if
	 * the dimensions to the cylinder and placement are exactly like floor 1. You can probably use this method as a base for other methods if nothing else.
	 * @param distance
	 * @param blockHeight
	 * @return true if the distance and block height are correct for the cone shape.
	 */
	private boolean isWithinCone(int distance, int blockHeight)
	{
		double dist = (double)distance;
		double height = (double)blockHeight;
		double mult = 39.0D / (double)wallStart;
		if(height < 40 && height >= ((dist * mult)))
		{
			return true;
		}
		return false;
	}
	
	private ArrayList<FloorPoint> getFloorCenterPoints()
	{
		ArrayList<FloorPoint> centerPoints = new ArrayList<FloorPoint>();
		for(int x = 0; x < 10; x++)
		for(int z = 0; z < 10; z++)
		{
			FloorPoint point = new FloorPoint((x*10)+(z+1), x*floorSeparation, 40, z*floorSeparation, 
				wallStart, wallThickness, 1.4D);
			centerPoints.add(point);
		}
		return centerPoints;
	}
	
	private void setFloorPointSize(int floorNumber, int radius, int wallThickness)
	{
		if(floorNumber <= 0 || floorNumber >= 100) return;
		FloorPoint floor = floorCenters.get(floorNumber-1);
		floor.setFloorRadius(radius);
		floor.setWallThickness(wallThickness);
		calculateFloorSeparation();
	}
	
	private void setFloorPointAmplitude(int floorNumber, int floorAmplitude)
	{
		if(floorNumber <= 0 || floorNumber >= 100) return;
		FloorPoint floor = floorCenters.get(floorNumber-1);
		floor.setFloorAmplitude(floorAmplitude);
	}
	
	/**
	 * Whenever an update to one of the floors has been made and the position or size has changed, this method can be 
	 * used to iterate through all of the floor center points and correct the differences to ensure that the distance 
	 * between each floor's wall X and Z extreme boundaries is at least 512 blocks (32 chunks). This will most likely 
	 * make some floors look like they're out of place, but it should prevent them all from being visible from each
	 * others' boundaries.
	 */
	private void calculateFloorSeparation()
	{
		FloorPoint floor, floorWest, floorEast, floorNorth, floorSouth;
		for(int x = 0; x < 10; x++)
		for(int z = 0; z < 10; z++)
		{
			floor = floorCenters.get((x*10)+z);
			if(z > 0)
			{
				floorSouth = floorCenters.get((x*10)+(z-1));
				if(floorSouth.getPosZBoundary() + 512 >= floor.getNegZBoundary())
				{
					floor.setZ(floorSouth.getPosZBoundary() + 512 + floor.getFloorRadius());
				}
			}
			if(z < 9)
			{
				floorNorth = floorCenters.get((x*10)+(z+1));
				if(floorNorth.getNegZBoundary() - 512 <= floor.getPosZBoundary())
				{
					floorNorth.setZ(floor.getPosZBoundary() + 512 + floorNorth.getFloorRadius());
				}
			}
			if(x > 0)
			{
				floorWest = floorCenters.get(((x-1)*10)+z);
				if(floorWest.getPosXBoundary() + 512 >= floor.getNegXBoundary())
				{
					floor.setX(floorWest.getPosXBoundary() + 512 + floor.getFloorRadius());
				}
			}
			if(x < 9)
			{
				floorEast = floorCenters.get(((x+1)*10)+z);
				if(floorEast.getNegXBoundary() - 512 >= floor.getPosXBoundary())
				{
					floorEast.setX(floor.getPosXBoundary() + 512 + floorEast.getFloorRadius());
				}
			}
		}
	}
	
	private int getCurrentFloorNumber(int x, int z)
	{
		for(FloorPoint point : floorCenters)
		{
			if(x >= point.getNegXBoundary() && x <= point.getPosXBoundary() && 
					z >= point.getNegZBoundary() && z <= point.getPosZBoundary())
			{
				return point.getFloorNumber();
			}
		}
		return 0;
	}
	
	public void genVoidTerrain(World world, Random random, Block[] blocks, byte[] metadata, int x, int z, double noiseGenSeed)
    {
		if(x <= floorsEnd && x >= -wallEnd && z <= floorsEnd && z >= -wallEnd)
		{
			int floorNumber = getCurrentFloorNumber(x, z);
			if(floorNumber == 0) return;
			currentFloor = floorCenters.get(floorNumber-1);
			int distance = StructureGenHelper.distance2D(x, z, currentFloor.getX(), currentFloor.getZ());
			int chunkX = x & 15;
	        int chunkZ = z & 15;
	        int chunkHeight = blocks.length / 256;
	        int noiseGenRand = (int)Math.pow(noiseGenSeed / 3.0D + 3.0D, currentFloor.getFloorAmplitude());
			if(distance <= wallEnd && distance > wallStart)
			{
				int wallTop = ((wallThickness-(wallEnd - distance)) * wallSegmentHeight)+40;
	        	int wallBottom = wallTop-wallSegmentHeight;
	        	
				for (int blockHeight = 255; blockHeight >= 0; --blockHeight)
		        {
					int blockIndex = (chunkZ * 16 + chunkX) * chunkHeight + blockHeight;
		        	metadata[blockIndex] = 0;
		        	if(blockHeight <= wallTop && blockHeight >= wallBottom)
		        	{
		        		blocks[blockIndex] = this.wallBlock;
		        	}
		        }
			}
			else if(distance <= wallStart)
	        {
		        Block aincradGrass = this.topBlock;
		        byte blockData = (byte)(this.field_150604_aj & 255);
		        Block aincradDirt = this.fillerBlock;
		        int blockMaxHeight = noiseGenRand + 40;
		        for (int blockHeight = 255; blockHeight >= 0; --blockHeight)
		        {
		        	int blockIndex = (chunkZ * 16 + chunkX) * chunkHeight + blockHeight;
		        	metadata[blockIndex] = 0;
		        	if(blockHeight == blockMaxHeight)
		        	{
		        		blocks[blockIndex] = aincradGrass;
		        	}
		        	else if(blockHeight < blockMaxHeight && blockHeight >= 40)
		        	{
		        		blocks[blockIndex] = aincradDirt;
		        	}
		        	else if(blockHeight < 40 && blockHeight >=0 && isWithinCone(distance, blockHeight))
		        	{
		        		blocks[blockIndex] = aincradDirt;
		        	}
		        	else
		        	{
		        		blocks[blockIndex] = Blocks.air;
		        	}
	            }
	        }
		}
    }
}