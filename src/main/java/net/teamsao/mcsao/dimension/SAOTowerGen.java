package net.teamsao.mcsao.dimension;

import java.awt.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.teamsao.mcsao.block.SBlock;
import net.teamsao.mcsao.help.StructureGenHelper;

public class SAOTowerGen extends WorldGenerator
{

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

	@Override
	public boolean generate(World world, Random rand,
			int blockX, int blockY, int blockZ) {
		int startX = 0;
		int startZ = 0;
		int startY = 40;

		int radius = 500;
		int height = 10;
		long startTime = System.currentTimeMillis();
		Block floor = Blocks.stonebrick;
		Block wall = SBlock.DungeonStone;

		for(int chunkX = -32; chunkX < 32; chunkX++)
		{
			for(int chunkZ = -32; chunkZ < 32; chunkZ++)
			{
				Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);
				ExtendedBlockStorage miniChunk = chunk.getBlockStorageArray()[2] = new ExtendedBlockStorage(startY >> 4 << 4, !chunk.worldObj.provider.hasNoSky);
				for(int x = 0; x < 16; x++)
				{
					for(int z = 0; z < 16; z++)
					{
						int modX = chunkX << 4;
						int modZ = chunkZ << 4;
						if(chunkX < 0)
						{
							modX = modX - 15 + x;
						}
						else if(chunkX >= 0)
						{
							modX += x;
						}
						if(chunkZ < 0)
						{
							modZ = modZ - 15 + z;
						}
						else if(chunkZ >= 0)
						{
							modZ += z;
						}
						if(Math.abs(modX) <= 256 && Math.abs(modZ) <= 256)
						{
							if(modX % 16 == 0 && modZ % 16 == 0)
							{
								world.setBlock(modX, 38, modZ, floor);
							}
						}
						int distance = StructureGenHelper.distance2D(modX, modZ);
						if(distance == radius)
						{
							for(int y = 7; y < 16; y++)
							{
								fastSetBlock(x, y, z, wall, miniChunk);
								miniChunk.setExtBlockMetadata(x, y, z, 1);
								if(!world.isRemote)
								{
									world.markBlockForUpdate(modX, 32+y, modZ);
									world.notifyBlockChange(modX, 32+y, modZ, wall);
								}
							}
						}
						else if(distance < radius)
						{
							fastSetBlock(x, 7, z, floor, miniChunk);
							if(!world.isRemote)
							{
								world.markBlockForUpdate(modX, 39, modZ);
								world.notifyBlockChange(modX, 39, modZ, floor);
							}
						}
					}
				}
				/*if(!world.isRemote)
				{
					chunk.generateHeightMap();
				}*/
				chunk.generateSkylightMap();
				chunk.setChunkModified();
				
			}
		}

		/*for(int x = startX - radius; x < startX + radius + 1; x++)
		{
			for(int z = startZ - radius; z < startZ + radius + 1; z++)
			{	
				int distance = StructureGenHelper.distance2D(x, z);
				if(distance == radius - 4)
				{
					for(int y = startY; y < startY + height + 1; y++)
					{
						world.setBlock(x, y, z, SBlock.DungeonStone, 1, 3);
					}
				}
				else if(distance == radius - 3)
				{
					for(int y = startY+height; y < startY + height*2 + 1; y++)
					{
						world.setBlock(x, y, z, SBlock.DungeonStone, 1, 3);
					}
				}
				else if(distance == radius - 2)
				{
					for(int y = startY+height*2; y < startY + height*3 + 1; y++)
					{
						world.setBlock(x, y, z, SBlock.DungeonStone, 1, 3);
					}
				}
				else if(distance == radius - 1)
				{
					for(int y = startY+height*3; y < startY + height*4 + 1; y++)
					{
						world.setBlock(x, y, z, SBlock.DungeonStone, 1, 3);
					}
				}
				else if(distance == radius)
				{
					for(int y = startY+height*4; y < startY + height*5 + 1; y++)
					{
						world.setBlock(x, y, z, SBlock.DungeonStone, 1, 3);
					}
				}
				else if(distance < radius)
				{
					if(distance % 6 == 0)
					{
						world.setBlock(x, startY, z, Blocks.glowstone);
					}
					else
					{
						world.setBlock(x, startY, z, Blocks.stonebrick);
						//world.setBlock(x, startY+height+1, z, SBlock.DungeonStone, 1, 3);
					}
				}
				//System.out.println("Code got as far as (x:"+x+", y:"+startY+", z:"+z+")");
			}
		}*/
		long endTime = System.currentTimeMillis();
		float time = (endTime - startTime);
		System.out.println("Time for completion is "+time+" milliseconds, "+time/1000.0F+" seconds, or "+time/60000F+" minutes.");
		//world.setBlock(startX, startY, startZ, SBlock.DungeonStone);
		return true;
	}
}
