package net.teamsao.mcsao.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
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

}
