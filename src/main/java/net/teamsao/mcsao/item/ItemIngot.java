package net.teamsao.mcsao.item;

import net.teamsao.mcsao.lib.SAOTabsManager;

// Created by Skymmer on 8/8/2014.

public class ItemIngot extends ItemSAO {
	
	public ItemIngot() {
		super();
		this.setCreativeTab(SAOTabsManager.saoIngots);
	}

}
