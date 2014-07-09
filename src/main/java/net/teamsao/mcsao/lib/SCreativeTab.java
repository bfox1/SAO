package net.teamsao.mcsao.lib;

import net.minecraft.creativetab.CreativeTabs;

/**
 * @author bfox1
 *
 */
public class SCreativeTab {

		public static CreativeTabs SAO;
		
		public static CreativeTabs SAO_Tools;
		
		public static CreativeTabs SAO_Blocks;
		
		public static void registerCreativeTab()
		{
			SAO = new SAOTabsManager(CreativeTabs.getNextID(), "SAO");
			SAO_Tools = new SAOTabsManager(CreativeTabs.getNextID(), "SAOTools");
			SAO_Blocks = new SAOTabsManager(CreativeTabs.getNextID(), "SAOBlocks");
		}
}
