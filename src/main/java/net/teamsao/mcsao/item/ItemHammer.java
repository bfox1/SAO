package net.teamsao.mcsao.item;

import net.teamsao.mcsao.lib.SAOTabsManager;

// Created by Skymmer on 8/8/2014.

public class ItemHammer extends ItemSAO {

	protected int hammerTier;
	
	public ItemHammer() {
		super();
		this.setCreativeTab(SAOTabsManager.saoTools);
	}
	
	// Sets the tier of the hammer.  Scale from 0-9.
	public int setHammerTier(int tier) {
		hammerTier = tier;
		return tier;
	}
	
	// Gets the tier of the hammer.  Scale from 0-9.
	public int getHammerTier() {
		return hammerTier;
	}
	
}
