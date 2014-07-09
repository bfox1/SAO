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
	
	public SAOBiomeGen(int par1, boolean register)
	{
		super(par1, register);
		setHeight(height_MidPlains);
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.topBlock = Blocks.stonebrick;
		this.fillerBlock = Blocks.air;
		this.setBiomeName("Almost Void");
	}
}
