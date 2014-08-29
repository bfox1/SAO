package net.teamsao.mcsao.item.foods;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SAOTabsManager;


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
