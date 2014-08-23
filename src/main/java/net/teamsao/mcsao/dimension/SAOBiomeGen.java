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
	
	public void genVoidTerrain(World world, Random random, Block[] blocks, byte[] metadata, int x, int z, double noiseGenSeed)
    {
		if(StructureGenHelper.distance2D(x, z) < 500)
        {
			boolean flag = true;
	        Block block = this.topBlock;
	        byte blockData = (byte)(this.field_150604_aj & 255);
	        Block block1 = this.fillerBlock;
	        int noiseGenRand = (int)(noiseGenSeed / 3.0D + 3.0D + random.nextDouble() * 0.25D);
	        int chunkX = x & 15;
	        int chunkZ = z & 15;
	        int chunkHeight = blocks.length / 256;
	        int blockMaxHeight = noiseGenRand + 40;
	        
	        for (int blockHeight = 255; blockHeight >= 0; --blockHeight)
	        {
	        	int blockIndex = (chunkZ * 16 + chunkX) * chunkHeight + blockHeight;
	        	metadata[blockIndex] = 0;
	        	if(blockHeight == blockMaxHeight)
	        	{
	        		blocks[blockIndex] = block;
	        	}
	        	else if(blockHeight < blockMaxHeight && blockHeight >= 40)
	        	{
	        		blocks[blockIndex] = block1;
	        	}
	        	else
	        	{
	        		blocks[blockIndex] = Blocks.air;
	        	}
            }
        }
    }
}
