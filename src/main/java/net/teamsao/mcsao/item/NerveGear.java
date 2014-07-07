package net.teamsao.mcsao.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.lib.SCreativeTab;

/**
 * This file was forked from HeartOfKobold (thanks, bfox! :D).
 * @author 5chris100
 *
 */
public class NerveGear extends Item {
	
	public NerveGear(){
		super();
		setUnlocalizedName("NerveGear");
		this.setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(13));
		this.setCreativeTab(SCreativeTab.SAO);
	}
	

	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3Player)
	{
		if(!par2World.isRemote)
		{
			if(par3Player.isSneaking())
			{
				//Opens Gui for insertion of Cartridge. 
			}
		}
		return par1ItemStack;
		
	}

}
