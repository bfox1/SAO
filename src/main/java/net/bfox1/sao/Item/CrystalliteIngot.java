package net.bfox1.sao.Item;

import java.util.List;

import net.bfox1.sao.help.ReferenceHelper;
import net.bfox1.sao.lib.SCreativeTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

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
