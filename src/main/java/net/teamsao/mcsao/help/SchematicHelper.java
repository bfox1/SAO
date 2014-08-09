package net.teamsao.mcsao.help;

import java.io.File;
import java.io.InputStream;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCompressedPowered;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTorch;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import net.teamsao.mcsao.SwordArtOnline;

/**
 * Class for dealing with schematics, converting them from block and data arrays into 3D arrays of BlockData.
 * @author Ian
 *
 */
public class SchematicHelper
{
	public Schematic getSchematic(String filename)
	{
		try
		{
			String filepath = "assets" + File.separator + Reference.MODID + File.separator + "schematics" + File.separator + 
					(filename.contains(".schematic") ? filename : filename + ".schematic");
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(filepath);
            NBTTagCompound nbtdata = CompressedStreamTools.readCompressed(is);
            short width = nbtdata.getShort("Width");
            short height = nbtdata.getShort("Height");
            short length = nbtdata.getShort("Length");

            byte[] blocks = nbtdata.getByteArray("Blocks");
            byte[] data = nbtdata.getByteArray("Data");

            System.out.println("schem size:" + width + " x " + height + " x " + length);
            NBTTagList tileentities = nbtdata.getTagList("TileEntities", 10);
            is.close();

            return new Schematic(tileentities, width, height, length, blocks, data);
        }
		catch (Exception e)
		{
            System.out.println("I can't load schematic, because " + e.toString());
            return null;
        }
	}
	
	/**
	 * Used in the nullifying empty space method to determine whether this current block at a location is inside of or
	 * in-between other structures, which is essentially the basis for determining whether it should have an air block
	 * or be a null block. If there is only one block along any one axis, it could be the corner of a roof or something.
	 * But, if it's in-between two blocks along an axis it could easily be between two houses or towers, or inside.
	 * When blocks become null, they no longer act as part of the schema and are skipped in the checkBounds algorithm.
	 * @param schema is the object traversed using the input coordinates and direction.
	 * @param x
	 * @param y
	 * @param z
	 * @return true if the block has non-air blocks on both sides of at least one of the three axes. Returns false if
	 * on any one axis only one side has a block
	 */
	public static boolean blocksExistAlongAxes(BlockData[][][] schema, int x, int y, int z)
	{
		int xStart = 0;
		int yStart = 0;
		int zStart = 0;
		boolean side1 = false;
		for(xStart = 0; xStart < x; xStart++)
		{
			if(schema[xStart][y][z] != null && schema[xStart][y][z].getBlock() != Blocks.air)
			{
				side1 = true;
				break;
			}
		}
		for(xStart = x+1; xStart < schema.length; xStart++)
		{
			if(schema[xStart][y][z] != null && schema[xStart][y][z].getBlock() != Blocks.air)
			{
				if(side1)
				{
					return true;
				}
			}
		}
		side1 = false;
		for(yStart = 0; yStart < y; yStart++)
		{
			if(schema[x][yStart][z] != null && schema[x][yStart][z].getBlock() != Blocks.air)
			{
				side1 = true;
				break;
			}
		}
		for(yStart = y+1; yStart < schema[0].length; yStart++)
		{
			if(schema[x][yStart][z] != null && schema[x][yStart][z].getBlock() != Blocks.air)
			{
				if(side1)
				{
					return true;
				}
			}
		}
		side1 = false;
		for(zStart = 0; zStart < z; zStart++)
		{
			if(schema[x][y][zStart] != null && schema[x][y][zStart].getBlock() != Blocks.air)
			{
				side1 = true;
				break;
			}
		}
		for(zStart = z+1; zStart < schema[0][0].length; zStart++)
		{
			if(schema[x][y][zStart] != null && schema[x][y][zStart].getBlock() != Blocks.air)
			{
				return side1;
			}
		}
		return false;
	}
	
	/**
	 * Attempts to identify air blocks which contribute to the structure of the schema in order to find the list of those
	 * that do not.
	 * @param schema
	 */
	public static void trimSchema(BlockData[][][] schema)
	{
		int xWidth = 0;
		int yHeight = 0;
		int zLength = 0;
		try
		{
			xWidth = schema.length;
			yHeight = schema[0].length;
			zLength = schema[0][0].length;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return;
		}
		for(int x = 0; x < xWidth; x++)
		for(int y = 0; y < yHeight; y++)
		for(int z = 0; z < zLength; z++)
		{
			if(schema[x][y][z] == null || schema[x][y][z].getBlock() == null)
			{
				/*
				 * Don't let people try to trim the schema more than once. Also don't let people edit
				 * in null blocks by themselves and try to trim it after.
				 */
				return;
			}
			if(schema[x][y][z].getBlock() == Blocks.air)
			{
				if(!(blocksExistAlongAxes(schema, x, y, z)))
				{
					schema[x][y][z].setBlock(null);
				}
			}
		}
	}
	
	public static boolean isSideSolid(Block block, ForgeDirection side, int metadata)
    {
        if (block instanceof BlockSlab)
        {
            return (((metadata & 8) == 8 && (side == ForgeDirection.UP)) || block.func_149730_j());
        }
        else if (block instanceof BlockFarmland)
        {
            return (side != ForgeDirection.DOWN && side != ForgeDirection.UP);
        }
        else if (block instanceof BlockStairs)
        {
            boolean flipped = ((metadata & 4) != 0);
            return ((metadata & 3) + side.ordinal() == 5) || (side == ForgeDirection.UP && flipped);
        }
        else if (block instanceof BlockSnow)
        {
            return (metadata & 7) == 7;
        }
        else if (block instanceof BlockHopper && side == ForgeDirection.UP)
        {
            return true;
        }
        else if (block instanceof BlockCompressedPowered)
        {
            return true;
        }
        return isNormalCube(block);
    }
	
	public static boolean isNormalCube(Block block)
    {
        return block.getMaterial().isOpaque() && block.renderAsNormalBlock() && !block.canProvidePower();
    }
	
	public static BlockData[] schemaBlockNeighbors(BlockData[][][] schema, int blockX, int blockY, int blockZ)
	{
		int xWidth = schema.length;
		int yHeight = schema[0].length;
		int zLength = schema[0][0].length;
		BlockData[] blocks = new BlockData[6];
		if(blockX < xWidth)
		{
			blocks[0] = schema[blockX+1][blockY][blockZ];
		}
		if(blockX > 0)
		{
			blocks[1] = schema[blockX-1][blockY][blockZ];
		}
		if(blockY < yHeight)
		{
			blocks[2] = schema[blockX][blockY+1][blockZ];
		}
		if(blockY > 0)
		{
			blocks[3] = schema[blockX][blockY-1][blockZ];
		}
		if(blockZ < zLength)
		{
			blocks[4] = schema[blockX][blockY][blockZ+1];
		}
		if(blockZ > 0)
		{
			blocks[5] = schema[blockX][blockY][blockZ-1];
		}
		return blocks;
	}
	
	/**
	 * This method tries to determine whether a block side is solid from the point of view of the other block.
	 * @param block
	 * @param x
	 * @param y
	 * @param z
	 * @return an integer flag where the 1 bits in the resulting byte are true flags. 2 checks against east, 1 against west, 8 against down, 4 against up,
	 * 32 against south, and 16 against north
	 */
	public static int solidSides(BlockData[] blocks)
	{
		int flag = 0;
		if(blocks[1] != null && isSideSolid(blocks[1].getBlock(), ForgeDirection.EAST, blocks[1].getMetadata()))
		{
			flag |= 1;
		}
		if(blocks[0] != null && isSideSolid(blocks[0].getBlock(), ForgeDirection.WEST, blocks[0].getMetadata()))
		{
			flag |= 2;
		}
		if(blocks[2] != null && isSideSolid(blocks[2].getBlock(), ForgeDirection.DOWN, blocks[2].getMetadata()))
		{
			flag |= 4;
		}
		if(blocks[3] != null && isSideSolid(blocks[3].getBlock(), ForgeDirection.UP, blocks[3].getMetadata()))
		{
			flag |= 8;
		}
		if(blocks[5] != null && isSideSolid(blocks[5].getBlock(), ForgeDirection.SOUTH, blocks[5].getMetadata()))
		{
			flag |= 16;
		}
		if(blocks[4] != null && isSideSolid(blocks[4].getBlock(), ForgeDirection.NORTH, blocks[4].getMetadata()))
		{
			flag |= 32;
		}
		return flag;
	}
	
	/**
	 * Attempts to replace target blocks with possibly mod blocks with different metadata values. This is not
	 * actually hard by itself, until you realize that stairs, slabs, and torches exist. Then, you need special
	 * handling for each of those cases. Stairs and torches use metadata for placement, but slabs use metadata for
	 * which side, bottom or top, is used, and can have up to 8 different sub-blocks represented in one block.
	 * For replacing normal blocks with stairs or torches this can be a problem, since there's no template to go by
	 * the metadata needs to be calculated on its own from data from surrounding blocks. This is pretty much the
	 * reason why this method is so huge.
	 * @param schema
	 * @param replacee
	 * @param replacement
	 * @param metadata
	 */
	public static void replaceSchemaBlock(BlockData[][][] schema, Block replacee, Block replacement, int metadata)
	{
		for(int x = 0; x < schema.length; x++)
		for(int y = 0; y < schema[0].length; y++)
		for(int z = 0; z < schema[0][0].length; z++)
		{
			if(replacement instanceof BlockStairs)
			{
				schema[x][y][z].setBlock(replacement);
				BlockData[] blocks = schemaBlockNeighbors(schema, x, y, z);
				int sides = solidSides(blocks);
				if(!(replacee instanceof BlockStairs))
				{
					int stairRotation = 0;
					if((sides == 63) || ((sides & 15) == 10))
					{
						stairRotation = 0;
					}
					else if((sides & 15) == 9)
					{
						stairRotation = 1;
					}
					else if((sides & 15) == 5)
					{
						stairRotation = 2;
					}
					else if((sides & 15) == 6)
					{
						stairRotation = 3;
					}
					else if((sides & 60) == 40)
					{
						stairRotation = 4;
					}
					else if((sides & 60) == 24)
					{
						stairRotation = 5;
					}
					else if((sides & 60) == 36)
					{
						stairRotation = 6;
					}
					else if((sides & 60) == 20)
					{
						stairRotation = 7;
					}
					schema[x][y][z].setMetadata(stairRotation);
				}
			}
			else if(replacement instanceof BlockTorch)
			{
				BlockData[] blocks = schemaBlockNeighbors(schema, x, y, z);
				int sides = solidSides(blocks);
				if(!(replacee instanceof BlockTorch) && (sides != 0))
				{
					if((sides & 4) == 1)
					{
						schema[x][y][z].setMetadata(5);
					}
					else
					{
						schema[x][y][z].setMetadata((sides & 1) != 0 ? 1 :
													(sides & 2) != 0 ? 2 :
													(sides & 16) != 0 ? 3 :
													(sides & 32) != 0 ? 4 : 0);
					}
				}
				if(schema[x][y][z].getMetadata() > 0)
				{
					schema[x][y][z].setBlock(replacement);
				}
				else
				{
					schema[x][y][z].setBlock(Blocks.air);
				}
			}
			else if(replacement instanceof BlockSlab && replacee instanceof BlockSlab)
			{
				schema[x][y][z].setBlock(replacement);
				int rMetadata = schema[x][y][z].getMetadata();
				if(rMetadata == 8 && metadata < 8)
				{
					schema[x][y][z].setMetadata(metadata | 8);
				}
				else if(rMetadata == 0 && metadata >= 8)
				{
					schema[x][y][z].setMetadata(metadata - 8);
				}
				else
				{
					schema[x][y][z].setMetadata(metadata);
				}
			}
			else
			{
				schema[x][y][z].setBlock(replacement);
				schema[x][y][z].setMetadata(metadata);
			}
		}
	}
	
	/**
	 * Turns the 1D array of block binary data and metadata into a 3D array of BlockData.
	 * @param slimSchem
	 * @return the 3D array of BlockData containing all of the data recovered from the schema.
	 */
	public static BlockData[][][] expandSchema(Schematic slimSchem)
	{
		int xWidth = slimSchem.width;
		int yHeight = slimSchem.height;
		int zLength = slimSchem.length;
		BlockData[][][] fatSchem = new BlockData[xWidth][yHeight][zLength];
		byte[] blocks = slimSchem.getBlocks();
		byte[] blockData = slimSchem.getData();
		for(int x = 0; x < xWidth; x++)
		for(int y = 0; y < yHeight; y++)
		for(int z = 0; z < zLength; z++)
		{
			Block block = Block.getBlockById(blocks[x + xWidth*(y+yHeight*z)]);
			int metadata = blockData[x + xWidth*(y+yHeight*z)];
			fatSchem[x][y][z] = new BlockData(block, metadata, x, y, z);
		}
		return fatSchem;
	}
}
