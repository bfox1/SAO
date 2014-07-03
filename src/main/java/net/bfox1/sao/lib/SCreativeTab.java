package net.bfox1.sao.lib;

import net.minecraft.creativetab.CreativeTabs;

/**
 * @author bfox1
 *
 */
public class SCreativeTab {

		public static CreativeTabs SAO;
		
		public static CreativeTabs SaoTools;
		
		public static void registerCreativeTab()
		{
			SAO = new SAOTabsManager(CreativeTabs.getNextID(), "SAO");
		}
}
