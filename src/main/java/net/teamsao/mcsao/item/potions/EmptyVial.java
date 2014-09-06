package net.teamsao.mcsao.item.potions;

import net.minecraft.item.Item;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;

/**
<<<<<<< HEAD
 * @author bfox1
=======
 * Created by bfox1 on 8/21/2014.
>>>>>>> 79313ea16d2318340787e6ca2a63af359f0dafde
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
