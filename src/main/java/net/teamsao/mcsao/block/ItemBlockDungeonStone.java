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

	public ItemBlockDungeonStone(Block p_i45328_1_) {
		super(p_i45328_1_);
		//this.setUnlocalizedName("DungeonStone");
		//this.setCreativeTab(SCreativeTab.SAO);
		this.setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name = "";
		switch(itemstack.getItemDamage())
		{
		case 0:
		{
			name = "SmoothStone";
			break;
		}
		case 1:
		{
			name = "BrickedStone";
			break;
		}
		case 2:
		{
			name = "ChiseledStone";
			break;
		}
		case 3:
		{
			name = "KnockBackStone";
			break;
		}
		default: name = "broken";
		}
		return getUnlocalizedName() + "." + name;
	}
	
	@Override
	public int getMetadata(int damageValue)
	{
		return damageValue;
	}
	


}
