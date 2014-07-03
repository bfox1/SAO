package net.bfox1.sao.lib;

import net.bfox1.sao.Item.SItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

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
		return SItem.BossKillToken;
	}

}
