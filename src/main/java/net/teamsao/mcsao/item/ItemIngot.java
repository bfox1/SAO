package net.teamsao.mcsao.item;

import net.teamsao.mcsao.lib.SAOTabsManager;

// Created by Skymmer on 8/8/2014.

public class ItemIngot extends ItemSAO {

    protected int malleability;

	public ItemIngot() {
		super();
		this.setCreativeTab(SAOTabsManager.saoIngots);
	}

    // Sets ingot malleability, or what tier of hammer is needed to make a weapon out of it. 0-9
    public int setMalleability(int malleability) {
        this.malleability = malleability;
        return malleability;
    }

    // Gets ingot malleability
    public int getMalleability() {
        return this.malleability;
    }

}
