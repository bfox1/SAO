package net.teamsao.mcsao.item.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;

public class Col extends Item {

    public Col() {
        super();
        this.setUnlocalizedName("Col");
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(SAOTabsManager.SAO);
    }

    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
    {
        list.add(EnumChatFormatting.GOLD + "A physical form of SAO's currency.");
    }

}
