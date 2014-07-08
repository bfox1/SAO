package net.teamsao.mcsao.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SCreativeTab;

/**
 * Created by bfox1 on 7/8/2014.
 */
public class KarakurenaiPowered extends SItemSword {
    public KarakurenaiPowered(ToolMaterial p_i45356_1_) {
        super(p_i45356_1_);
        this.setUnlocalizedName("KarakurenaiPowered");
        this.setTextureName(ReferenceHelper.setItemName(SItem.Karakurenai));
        this.setCreativeTab(null);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        ItemStack sword = new ItemStack(SItem.Karakurenai);
        if(!world.isRemote)
        {
            if(player.isSneaking())
            {
                sword.setItemDamage(par1ItemStack.getItemDamage());
                 return sword;
            }
        }
        return par1ItemStack;
    }
}
