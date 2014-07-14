package net.teamsao.mcsao.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SCreativeTab;

/**
 * @author bfox1
 *
 */
public class ItemBlockDungeonStone extends ItemBlock {

	/*
	 * (non-Javadoc)
	 * @see net.minecraft.item.ItemBlock#getUnlocalizedName(net.minecraft.item.ItemStack)
	 * 
	 * These are the Names of the Blocks and what they are. 
	 */
	public final static String[] subBlocks = {"SmoothStone", "BrickedStone", "ChiseledStone", "KnockBackStone"};
	
	public ItemBlockDungeonStone(Block p_i45328_1_)
	{
		super(p_i45328_1_);
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name = "";
		int damageValue = itemstack.getItemDamage();
		
		if(damageValue >= 0 && damageValue < subBlocks.length)
		{
			name = subBlocks[itemstack.getItemDamage()];
		}
		else
		{
			name = "broken";
		}
		return getUnlocalizedName() + "." + name;
	}
	
	@Override
	public int getMetadata(int damageValue)
	{
		return damageValue;
	}
	
}
