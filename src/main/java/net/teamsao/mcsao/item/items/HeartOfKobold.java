package net.teamsao.mcsao.item.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.lib.SAOTabsManager;

/**
 * @author bfox1
 *
 */
public class HeartOfKobold extends Item {
	
	public HeartOfKobold(){
		super();
		setUnlocalizedName("HeartOfKobold");
		this.setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(SAOTabsManager.SAO);
	}
	

}
