package net.teamsao.mcsao.item.foods;

import net.minecraft.item.ItemFood;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;


/**
 * Created by bfox1 on 7/9/2014.
 */
public class RabbitMeat extends ItemFood {
        public RabbitMeat(int heartsHeal, float saturation, boolean wolfLike, String name)
        {
            super(heartsHeal, saturation, wolfLike);
            this.setUnlocalizedName(name);
            this.setTextureName(ReferenceHelper.setItemName(this));
            this.setCreativeTab(SAOTabsManager.saoFood);

        }


}
