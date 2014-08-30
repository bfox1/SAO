package net.teamsao.mcsao.item.foods;

import net.minecraft.item.ItemFood;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;

/**
 * Created by bfox1 on 8/10/2014.
 */
public class RawRabbitMeat extends ItemFood {
    public RawRabbitMeat(int hearts, float saturation, boolean wolflikes, String name)
    {
        super(hearts, saturation, wolflikes);
        this.setUnlocalizedName(name);
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(SAOTabsManager.saoFood);

    }
}
