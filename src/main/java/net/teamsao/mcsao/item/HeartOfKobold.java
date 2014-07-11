package net.teamsao.mcsao.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.lib.SAOTabsManager;
import net.teamsao.mcsao.lib.SCreativeTab;

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
