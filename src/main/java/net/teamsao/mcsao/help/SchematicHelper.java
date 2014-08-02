package net.teamsao.mcsao.help;

import java.io.File;
import java.io.InputStream;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
			String filepath = "assets" + File.separator + Reference.MODID + File.separator + "schematics" + File.separator + filename +".schematic";
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
		boolean side2 = false;
		for(xStart = 0; xStart < x; xStart++)
		{
			if(schema[xStart][y][z].getBlock() != Blocks.air)
			{
				side1 = true;
				break;
			}
		}
		for(xStart = x+1; xStart < schema.length; xStart++)
		{
			if(schema[xStart][y][z].getBlock() != Blocks.air)
			{
				side2 = true;
				if(side1 && side2)
				{
					return true;
				}
			}
		}
		side1 = false;
		side2 = false;
		for(yStart = 0; yStart < y; yStart++)
		{
			if(schema[x][yStart][z].getBlock() != Blocks.air)
			{
				side1 = true;
				break;
			}
		}
		for(yStart = y+1; yStart < schema[0].length; yStart++)
		{
			if(schema[x][yStart][z].getBlock() != Blocks.air)
			{
				side2 = true;
				if(side1 && side2)
				{
					return true;
				}
			}
		}
		side1 = false;
		side2 = false;
		for(zStart = 0; zStart < z; zStart++)
		{
			if(schema[x][y][zStart].getBlock() != Blocks.air)
			{
				side1 = true;
				break;
			}
		}
		for(zStart = z+1; zStart < schema[0][0].length; zStart++)
		{
			if(schema[x][y][zStart].getBlock() != Blocks.air)
			{
				return side1;
			}
		}
		return false;
	}
	
	/**
	 * Attempts to identify air blocks which contribute to the structure of the schema in order to find the list of those
	 * that do not. This uses the compare-to method of BlockData.
	 * @param schema
	 */
	public static void nullifyEmptySpace(BlockData[][][] schema)
	{
		int xWidth = schema.length;
		int yHeight = schema[0].length;
		int zLength = schema[0][0].length;
		for(int x = 0; x < xWidth; x++)
		for(int y = 0; y < yHeight; y++)
		for(int z = 0; z < zLength; z++)
		{
			if(schema[x][y][z].getBlock() == Blocks.air)
			{
				if(!(blocksExistAlongAxes(schema, x, y, z)))
				{
					schema[x][y][z].setBlock(null);
				}
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
		for(int x = 0; x < xWidth; x++)
		for(int y = 0; y < yHeight; y++)
		for(int z = 0; z < zLength; z++)
		{
			Block block = Block.getBlockById(slimSchem.getBlocks()[x + xWidth*(y+yHeight*z)]);
			int metadata = slimSchem.getData()[x + xWidth*(y+yHeight*z)];
			fatSchem[x][y][z] = new BlockData(block, metadata, x, y, z);
		}
		return fatSchem;
	}
}
