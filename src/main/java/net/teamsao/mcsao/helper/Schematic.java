package net.teamsao.mcsao.helper;

import net.minecraft.nbt.NBTTagList;

public class Schematic
{
	public  NBTTagList tileentities;
    public  short width;
    public  short height;
    public short length;
    public byte[] blocks;
    public byte[] data;
    
    public Schematic(NBTTagList tileentities, short width, short height, short length, byte[] blocks, byte[] data)
    {
        this.tileentities = tileentities;
        this.width = width;
        this.height = height;
        this.length = length;
        this.blocks = blocks;
        this.data = data;
    }

	public NBTTagList getTileentities() {
		return tileentities;
	}

	public void setTileentities(NBTTagList tileentities) {
		this.tileentities = tileentities;
	}

	public short getWidth() {
		return width;
	}

	public void setWidth(short width) {
		this.width = width;
	}

	public short getHeight() {
		return height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	public short getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
	}

	public byte[] getBlocks() {
		return blocks;
	}

	public void setBlocks(byte[] blocks) {
		this.blocks = blocks;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
    
}
