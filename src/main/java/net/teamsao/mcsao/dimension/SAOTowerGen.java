package net.teamsao.mcsao.dimension;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.teamsao.mcsao.block.SBlock;
import net.teamsao.mcsao.help.StructureGenHelper;

public class SAOTowerGen extends WorldGenerator
{

	@Override
	public boolean generate(World world, Random rand,
			int blockX, int blockY, int blockZ) {
		int startX = 0;
		int startZ = 0;
		int startY = 40;
		
		int radius = 104;
		int height = 10;
		//System.out.println("This code is running! It is trying to build the floor!");
		for(int x = startX - radius; x < startX + radius + 1; x++)
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
		}
		//world.setBlock(startX, startY, startZ, SBlock.DungeonStone);
		return true;
	}
}
