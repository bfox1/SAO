package net.teamsao.mcsao.item.hammers;

import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.item.ItemHammer;

// Created by Skymmer on 8/8/2014.

public class ZoringenHammer extends ItemHammer {

	public ZoringenHammer() {
		super();
		this.setUnlocalizedName("ZoringenHammer");
		this.setTextureName(ReferenceHelper.setItemName(this));
		this.setHammerTier(9);
	}
}
