package net.teamsao.mcsao.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class LeafBlockMetaData extends ItemBlock
{
	public final static String[] subBlocks = {"SakuraLeaves", "SummerLeaves", "SpringLeaves", "AutumnLeaves"};

	public LeafBlockMetaData(Block material)
	{
		super(material);
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
