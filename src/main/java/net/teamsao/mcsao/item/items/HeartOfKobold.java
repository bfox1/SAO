package net.teamsao.mcsao.item.items;

import net.minecraft.item.Item;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;

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
