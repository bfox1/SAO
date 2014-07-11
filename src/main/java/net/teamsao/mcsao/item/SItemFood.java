package net.teamsao.mcsao.item;

import net.minecraft.item.ItemFood;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SAOTabsManager;
import net.teamsao.mcsao.lib.SCreativeTab;

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
