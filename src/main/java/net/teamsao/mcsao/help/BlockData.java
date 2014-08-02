package net.teamsao.mcsao.help;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

/**
 * Look! Blocks now store their own metadata again!
 * @author Ian
 *
 */
public class BlockData implements Comparable
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
	
	/**
	 * This comparator method compares block ID and then metadata. If both are equal it returns 0, otherwise it
	 * will return -1 if the current object has a lesser value and 1 if it has a greater value.
	 */
	@Override
	public int compareTo(Object block)
	{
		BlockData otherBlock = (BlockData) block;
		Block block1 = this.block;
		Block block2 = otherBlock.getBlock();
		if(this.equals(block))
		{
			return 0;
		}
		if(Block.getIdFromBlock(block1) < Block.getIdFromBlock(block2))
		{
			return -1;
		}
		else if(Block.getIdFromBlock(block1) > Block.getIdFromBlock(block2))
		{
			return 1;
		}
		else
		{
			return this.metadata < otherBlock.getMetadata() ? -1 : 1;
		}
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((block == null) ? 0 : block.hashCode());
		result = prime * result + blockX;
		result = prime * result + blockY;
		result = prime * result + blockZ;
		result = prime * result + metadata;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockData other = (BlockData) obj;
		if (block == null) {
			if (other.block != null)
				return false;
		} else if (!block.equals(other.block))
			return false;
		if (blockX != other.blockX)
			return false;
		if (blockY != other.blockY)
			return false;
		if (blockZ != other.blockZ)
			return false;
		if (metadata != other.metadata)
			return false;
		return true;
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
