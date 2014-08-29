package net.teamsao.mcsao.item.potions;

import net.minecraft.item.Item;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;

/**
 * Created by bfox1 on 8/21/2014.
 */
public class EmptyVial extends Item {

    public EmptyVial()
    {
        this.setUnlocalizedName("EmptyVial");
        this.setMaxStackSize(16);
        this.setMaxDamage(0);
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(SAOTabsManager.SAO);
    }
}
