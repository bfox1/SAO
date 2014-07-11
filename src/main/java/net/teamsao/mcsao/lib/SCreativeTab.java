package net.teamsao.mcsao.lib;

import net.minecraft.creativetab.CreativeTabs;

/**
 * @author bfox1
 *
 */
public class SCreativeTab {

		public static CreativeTabs SAO;
		public static CreativeTabs SaoFood;
		public static CreativeTabs SaoTools;
		public static CreativeTabs SaoBlocks;
		
		public static void registerCreativeTab()
		{
			SAO = new SAOTabsManager(CreativeTabs.getNextID(), "SAO");
			SaoTools = new SAOTabsManager(CreativeTabs.getNextID(), "SAOTools");
			SaoBlocks = new SAOTabsManager(CreativeTabs.getNextID(), "SAOBlocks");
            SaoFood = new SAOTabsManager(CreativeTabs.getNextID(), "SAOFoods");
		}
}
