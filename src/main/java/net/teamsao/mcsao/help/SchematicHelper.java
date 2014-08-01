package net.teamsao.mcsao.help;

import java.io.File;
import java.io.InputStream;

import net.minecraft.block.Block;
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
	
	public BlockData[][][] expandSchema(Schematic schem)
	{
		int xWidth = schem.width;
		int yHeight = schem.height;
		int zLength = schem.length;
		BlockData[][][] schematic = new BlockData[xWidth][yHeight][zLength];
		for(int x = 0; x < xWidth; x++)
		for(int y = 0; y < yHeight; y++)
		for(int z = 0; z < zLength; z++)
		{
			Block block = Block.getBlockById(schem.getBlocks()[x + xWidth*(y+yHeight*z)]);
			int metadata = schem.getData()[x + xWidth*(y+yHeight*z)];
			schematic[x][y][z] = new BlockData(block, metadata, x, y, z);
		}
		
		return schematic;
	}
}
