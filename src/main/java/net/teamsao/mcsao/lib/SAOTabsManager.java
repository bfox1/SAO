package net.teamsao.mcsao.lib;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.teamsao.mcsao.item.SItem;

/**
 * @author bfox1
 *
 */
public class SAOTabsManager extends CreativeTabs {

	public SAOTabsManager(int id, String name) {
		super(id, name);
	}

	@Override
	public Item getTabIconItem() {
		return SItem.Elucidator;
	}

}
