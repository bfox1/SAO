package net.teamsao.mcsao.item;

import net.minecraft.item.ItemFood;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;

/**
 * Created by bfox1 on 7/9/2014.
 */
public class SItemFood extends ItemFood {
    public SItemFood(int heartsHealed, float saturation, boolean wolfsLike, String name) {
        super(heartsHealed, saturation, wolfsLike);
        this.setUnlocalizedName(name);
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(SAOTabsManager.saoFood);
    }
}
