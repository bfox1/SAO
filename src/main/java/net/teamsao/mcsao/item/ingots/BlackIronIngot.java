package net.teamsao.mcsao.item.ingots;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.item.ItemIngot;

@SuppressWarnings("unchecked")
public class BlackIronIngot extends ItemIngot {

    public BlackIronIngot()
    {
        super();
        this.setUnlocalizedName("BlackIronIngot");
        this.setTextureName(ReferenceHelper.setItemName(this));
    }

    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
    {
        list.add(EnumChatFormatting.DARK_GRAY + "A Very Sought After Metal");
    }
}
