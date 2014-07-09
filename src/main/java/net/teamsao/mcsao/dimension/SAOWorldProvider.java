package net.teamsao.mcsao.dimension;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.teamsao.mcsao.SwordArtOnline;

public class SAOWorldProvider extends WorldProvider
{
	public BiomeGenBase almost_void = new SAOBiomeGen(44, true);
	
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.plains, 0.1F);
		this.dimensionId = SwordArtOnline.dimensionId;
	}
	
	public IChunkProvider createChunkGenerator()
	{
		return new SOAChunkProvider(worldObj, worldObj.getSeed(), true);
	}
	
	@Override
	public String getDimensionName()
	{
		return "Aincrad";
	}
}