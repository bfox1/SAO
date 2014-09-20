package net.teamsao.mcsao.world.generation;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.teamsao.mcsao.helper.StructureGenHelper;
import net.teamsao.mcsao.init.SAOBlocks;

public class SAOBiomeGen extends BiomeGenBase
{
	public Block wallBlock;
	private int wallSegmentHeight = 20;
	private int wallThickness = 5;
	private int wallStart = 999;
	private int wallEnd = wallStart+wallThickness;
	
	private int floor2HeightStart = 40+wallSegmentHeight*wallThickness;
	private int floor2WallStart = wallEnd;
	private int floor2WallEnd = floor2WallStart + wallThickness;
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
	
	public void genVoidTerrain(World world, Random random, Block[] blocks, byte[] metadata, int x, int z, double noiseGenSeed)
    {
		int distance = StructureGenHelper.distance2D(x, z);
		int chunkX = x & 15;
        int chunkZ = z & 15;
        int chunkHeight = blocks.length / 256;
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
	        int noiseGenRand = (int)Math.pow(noiseGenSeed / 3.0D + 3.0D, 1.4D);
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
		if(distance <= floor2WallEnd && distance > floor2WallStart)
		{
			int floor2WallTop = ((wallThickness-(floor2WallEnd - distance)) * wallSegmentHeight) + floor2HeightStart;
			int floor2WallBottom = floor2WallTop-wallSegmentHeight;
			
			for (int blockHeight = 255; blockHeight >= 0; --blockHeight)
	        {
				int blockIndex = (chunkZ * 16 + chunkX) * chunkHeight + blockHeight;
	        	metadata[blockIndex] = 0;
	        	if(blockHeight <= floor2WallTop && blockHeight >= floor2WallBottom)
	        	{
	        		blocks[blockIndex] = this.wallBlock;
	        	}
	        }
		}
		else if(distance <= floor2WallStart)
		{
			Block aincradGrass = this.topBlock;
	        byte blockData = (byte)(this.field_150604_aj & 255);
	        Block aincradDirt = this.fillerBlock;
	        int noiseGenRand = (int)Math.pow(noiseGenSeed / 3.0D + 3.0D, 1.8D);
	        int blockMaxHeight = noiseGenRand + 5 + floor2HeightStart;
	        for (int blockHeight = 255; blockHeight >= 0; --blockHeight)
	        {
	        	int blockIndex = (chunkZ * 16 + chunkX) * chunkHeight + blockHeight;
	        	metadata[blockIndex] = 0;
	        	if(blockHeight == blockMaxHeight)
	        	{
	        		blocks[blockIndex] = aincradGrass;
	        	}
	        	else if(blockHeight < blockMaxHeight && blockHeight >= floor2HeightStart)
	        	{
	        		blocks[blockIndex] = aincradDirt;
	        	}
	        }
		}
    }
}
