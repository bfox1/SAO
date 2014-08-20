package net.teamsao.mcsao.item.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.teamsao.mcsao.init.SAOBlocks;

public class ItemCobbleSlab extends ItemSlab
{
	public ItemCobbleSlab(Block block) 
	{
		super(block, (BlockSlab)SAOBlocks.AincradCobbleHalfSlab, 
				(BlockSlab)SAOBlocks.AincradCobbleDoubleSlab, false);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

}
