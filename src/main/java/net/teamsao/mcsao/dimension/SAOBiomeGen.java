package net.teamsao.mcsao.dimension;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.teamsao.mcsao.help.StructureGenHelper;
import net.teamsao.mcsao.init.SAOBlocks;

public class SAOBiomeGen extends BiomeGenBase
{
	public Block wallBlock;
	
	/**
	 * 
	 * Attempts to create a biome more friendly for building with no blocks anywhere except the portal.
	 * Disabled mob spawning, rain, and snow, and attempted to replace any blocks spawned with air.
	 * 
	 * In addition, the "height" is set to reduce noise in the case that we want to add terrain back
	 * to the dimension outside the tower. This can be modified if we want to.
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
	 * Ian - Overriding this method is an attempt to prevent mobs from spawning in the dimension while we build it. Hopefully it works.
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
		double mult = 39.0D / 495.0D;
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
		if(distance <= 500 && distance >= 496)
		{
			for (int blockHeight = 255; blockHeight >= 0; --blockHeight)
	        {
				int blockIndex = (chunkZ * 16 + chunkX) * chunkHeight + blockHeight;
	        	metadata[blockIndex] = 0;
	        	int wallEnd = ((5-(500 - distance)) * 20)+40;
	        	int wallStart = wallEnd-20;
	        	if(blockHeight >= wallStart && blockHeight < wallEnd)
	        	{
	        		blocks[blockIndex] = this.wallBlock;
	        	}
	        }
		}
		if(distance < 496)
        {
	        Block aincradGrass = this.topBlock;
	        byte blockData = (byte)(this.field_150604_aj & 255);
	        Block aincradDirt = this.fillerBlock;
	        int noiseGenRand = (int)Math.pow(noiseGenSeed / 3.0D + 3.0D, 2);
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
