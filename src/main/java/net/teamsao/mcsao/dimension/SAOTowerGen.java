package net.teamsao.mcsao.dimension;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.teamsao.mcsao.block.BlockSAO;
import net.teamsao.mcsao.help.StructureGenHelper;
import net.teamsao.mcsao.init.SAOBlocks;

public class SAOTowerGen extends WorldGenerator
{


	private int floor1Radius = 500;
	private int floor1SegmentHeight = 10;
	
	private Block floor = Blocks.stonebrick;
	private Block wall = SAOBlocks.DungeonStone;
	

	private void fastSetBlock(int x, int y, int z, Block block, ExtendedBlockStorage miniChunk)
	{
		int i1 = Block.getIdFromBlock(block);
		miniChunk.getBlockLSBArray()[y << 8 | z << 4 | x] = (byte)(i1 & 255);

		if (i1 > 255)
		{
			if (miniChunk.getBlockMSBArray() == null)
			{
				miniChunk.setBlockMSBArray(new NibbleArray(miniChunk.getBlockLSBArray().length, 4));
			}

			miniChunk.getBlockMSBArray().set(x, y, z, (i1 & 3840) >> 8);
		}
		else if (miniChunk.getBlockMSBArray() != null)
		{
			miniChunk.getBlockMSBArray().set(x, y, z, 0);
		}
	}


	private void setUpTower(World world, int chunkX, int chunkZ)
	{
		int startX = 0;
		int startZ = 0;
		int startY = 39;

		Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);
		ExtendedBlockStorage miniChunk = chunk.getBlockStorageArray()[2] = new ExtendedBlockStorage(startY >> 4 << 4, !chunk.worldObj.provider.hasNoSky);
		for(int x = 0; x < 16; x++)
		{
			for(int z = 0; z < 16; z++)
			{
				int modX = getWorldCoord(chunkX, x);
				int modZ = getWorldCoord(chunkZ, z);

				int distance = StructureGenHelper.distance2D(modX, modZ);

				//hackyFix(world, chunk, modX, modZ);
				if(distance == floor1Radius)
				{
					for(int y = 7; y < 16; y++)
					{
						chunk.getBlock(modX & 15, 32+y, modZ & 15);
						//fastSetBlock(x, y, z, wall, miniChunk);
						miniChunk.func_150818_a(x, y & 15, z, wall);
						miniChunk.setExtBlockMetadata(x, y & 15, z, 1);
						if(!world.isRemote)
						{
							world.markBlockForUpdate(modX, 32+y, modZ);
							world.notifyBlockChange(modX, 32+y, modZ, wall);
						}

					}
				}
				else if(distance < floor1Radius)
				{
					chunk.getBlock(modX & 15, startY, modZ & 15);
					//fastSetBlock(x, 7, z, floor, miniChunk);
					miniChunk.func_150818_a(x, startY & 15, z, floor);
					miniChunk.setExtBlockMetadata(x, startY & 15, z, 0);
					if(!world.isRemote)
					{

						world.markBlockForUpdate(modX, startY, modZ);
						world.notifyBlockChange(modX, startY, modZ, floor);
					}
				}
			}
		}
		chunk.generateSkylightMap();
		chunk.setChunkModified();
	}
	
	/**
	 * This method is used because for whatever stupid reason the blocks around the initial dimension load-up
	 * don't render client-side. I don't know why it happens but this fixes it. I am adding code to duplicate
	 * setBlock without getting rid of the advantages of modifying the chunk directly, which will hopefully
	 * make this method disappear.
	 * @author Ian
	 * @param world - reference to world for usage of setBlock
	 * @param x - world block x coordinate
	 * @param z - world block z coordinate
	 */
	private void hackyFix(World world, Chunk chunk, int x, int z)
	{
		if(Math.abs(x) <= 256 && Math.abs(z) <= 256)
		{
			if(x % 16 == 0 && z % 16 == 0)
			{
				world.setBlock(x, 38, z, floor);
			}
		}
	}
	
	/**
	 * For use when looping through chunk coords to get the internal block coords.
	 * @author Ian
	 * @param chunkVal - Chunk X or Z coordinate
	 * @param modifier - Modifying x or z block coordinate
	 * @return the block coordinate of the chunk plus what the looping x or z value modifies it to be.
	 */
	private int getWorldCoord(int chunkVal, int modifier)
	{
		int coord = chunkVal << 4;
		if(chunkVal < 0)
		{
			coord = coord - 15 + modifier;
		}
		else if(chunkVal >= 0)
		{
			coord += modifier;
		}
		return coord;
	}

	@Override
	public boolean generate(World world, Random rand,
			int blockX, int blockY, int blockZ) {

		long startTime = System.currentTimeMillis();

		for(int chunkX = -33; chunkX < 32; chunkX++)
		{
			for(int chunkZ = -33; chunkZ < 32; chunkZ++)
			{
				setUpTower(world, chunkX, chunkZ);
			}
		}
		long endTime = System.currentTimeMillis();
		float time = (endTime - startTime);
		System.out.println("Time for completion is "+time+" milliseconds, "+time/1000.0F+" seconds, or "+time/60000F+" minutes.");

		return true;
	}
}
