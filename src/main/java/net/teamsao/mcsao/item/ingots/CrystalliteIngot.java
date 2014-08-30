package net.teamsao.mcsao.item.ingots;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.item.ItemIngot;

public class CrystalliteIngot extends ItemIngot {
	
	public CrystalliteIngot()
	{
		super();
		this.setUnlocalizedName("CrystalliteIngot");
		this.setTextureName(ReferenceHelper.setItemName(this));
		this.setMalleability(9);
	}
	
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4){
		list.add(EnumChatFormatting.DARK_AQUA + "" + "A Very Sought After Metal");
	}
}
