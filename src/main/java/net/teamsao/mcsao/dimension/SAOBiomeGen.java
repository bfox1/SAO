package net.teamsao.mcsao.dimension;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

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
		this.topBlock = Blocks.air;
		this.fillerBlock = Blocks.air;
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
}
