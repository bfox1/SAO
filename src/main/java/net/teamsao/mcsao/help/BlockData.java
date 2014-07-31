package net.teamsao.mcsao.help;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * Look! Blocks now store their own metadata again!
 * @author Ian
 *
 */
public class BlockData
{

	private Block block;
	private int metadata;
	private int blockX;
	private int blockY;
	private int blockZ;
	
	/**
	 * Allows you to set both the block and a specific metadata.
	 * @param block
	 * @param metadata
	 */
	public BlockData(Block block, int metadata, int x, int y, int z)
	{
		this.block = block;
		this.metadata = metadata;
		this.blockX = x;
		this.blockY = y;
		this.blockZ = z;
	}
	
	/**
	 * Allows you to just set the block and default the metadata to 0.
	 * @param block
	 */
	public BlockData(Block block, int x, int y, int z)
	{
		this.block = block;
		this.metadata = 0;
		this.blockX = x;
		this.blockY = y;
		this.blockZ = z;
	}
	
	/**
	 * Well, there's nothing here, so I guess the block is air?
	 */
	public BlockData(int x, int y, int z)
	{
		this.block = Blocks.air;
		this.metadata = 0;
		this.blockX = x;
		this.blockY = y;
		this.blockZ = z;
	}
	
	public Block getBlock()
	{
		return block;
	}

	public void setBlock(Block block)
	{
		this.block = block;
	}

	public int getMetadata()
	{
		return metadata;
	}

	public void setMetadata(int metadata)
	{
		this.metadata = metadata;
	}
	
	public int getBlockX()
	{
		return blockX;
	}

	public int getBlockY()
	{
		return blockY;
	}

	public int getBlockZ()
	{
		return blockZ;
	}
}
