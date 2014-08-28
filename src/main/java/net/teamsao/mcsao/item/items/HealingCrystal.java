package net.teamsao.mcsao.item.items;

import net.minecraft.item.Item;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SAOTabsManager;

/**
 * Created by bfox1 on 8/21/2014.
 */
public class HealingCrystal extends Item {

    public HealingCrystal(){
        super();
        this.setUnlocalizedName("HealingCrystal");
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(SAOTabsManager.SAO);
        this.setMaxDamage(12);
    }
}
