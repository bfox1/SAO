package net.bfox1.sao.Item;

import net.bfox1.sao.help.Reference;
import net.bfox1.sao.lib.SCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * @author bfox1
 *
 */
public class HeartOfKobold extends Item {
	
	public HeartOfKobold(){
		super();
		setUnlocalizedName("HeartOfKobold");
		this.setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setCreativeTab(SCreativeTab.SAO);
	}
	

}
