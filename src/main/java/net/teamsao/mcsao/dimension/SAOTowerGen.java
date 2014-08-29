package net.teamsao.mcsao.dimension;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.teamsao.mcsao.help.StructureGenHelper;
import net.teamsao.mcsao.init.SAOBlocks;

public class SAOTowerGen extends WorldGenerator
{

	private int floor1Radius = 500;
	private int floor1SegmentHeight = 10;
	
	private Block floor = Blocks.stonebrick;
	private Block wall = SAOBlocks.DungeonStone;

	/**
	 * This method is what is used to generate individual chunks at a time for the floor currently.
	 * It doesn't incorporate the "roaming" chunks I used for the schema generator quite yet. I'm still
	 * working out how to do that correctly. Basically, the generate method iterates through a 64 chunk square
	 * area, and sends data to this method which is used to grab a chunk. From that chunk the individual
	 * 16 cubic block area is grabbed, which has methods to directly modify the block and metadata for a
	 * single coordinate. While inside the loop I determine the world coordinates so that I can keep the shape
	 * of the floor cylindrical by using a <= radius function. Then, while on the radius of the circle I add
	 * in the walls. After modifying each chunk, what keeps it from bugging out is the piece at the end,
	 * generating the sky light map and telling the world the chunk has been modified. Oh, and also apparently
	 * calling getBlock on every single coordinate also helps that. Still not sure why about that part.
	 * @param world
	 * @param chunkX
	 * @param chunkZ
	 */
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

				if(distance == floor1Radius)
				{
					for(int y = 7; y < 16; y++)
					{
						chunk.getBlock(modX & 15, 32+y, modZ & 15);
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
			int blockX, int blockY, int blockZ)
	{
		long startTime = System.nanoTime();
		
		for(int chunkX = -33; chunkX < 32; chunkX++)
		{
			for(int chunkZ = -33; chunkZ < 32; chunkZ++)
			{
				setUpTower(world, chunkX, chunkZ);
			}
		}
		
		long endTime = System.nanoTime();
		long time = (endTime - startTime);
		System.out.println("Time for completion is "+time+" nanoseconds, "+time/1000000000L+" seconds, or "+time/60000000000L+" minutes.");
		return true;
	}
}
