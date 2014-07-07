package net.teamsao.mcsao.Item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SCreativeTab;

public class CrystalliteIngot extends Item {
	
	public CrystalliteIngot()
	{
		super();
		this.setUnlocalizedName("CrystalliteIngot");
		this.setTextureName(ReferenceHelper.registerItemName(this));
		this.setCreativeTab(SCreativeTab.SAO);
	}
	
	@Override 
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
	{
		list.add(EnumChatFormatting.DARK_AQUA + "" + "A Very Sought After Metal");
	}

}
