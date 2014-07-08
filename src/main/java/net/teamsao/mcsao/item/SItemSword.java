package net.teamsao.mcsao.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

/**
 * Created by Bobby on 7/8/2014.
 */
public abstract class SItemSword extends ItemSword {
    public SItemSword(ToolMaterial p_i45356_1_) {
        super(p_i45356_1_);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        //ItemStack sword = new ItemStack(SItem.EbonDagger);
        if(!world.isRemote)
        {
            if(player.isSneaking())
            {
                //sword.setItemDamage(par1ItemStack.getItemDamage());
               // return sword;
            }
        }
        return par1ItemStack;
    }
}
