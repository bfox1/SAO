package net.teamsao.mcsao.item.swords;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.SItemSword;
//

/**
 * Created by bfox1 on 7/8/2014.
 */
public class KarakurenaiPowered extends SItemSword {
    public KarakurenaiPowered(ToolMaterial p_i45356_1_) {
        super(p_i45356_1_);
        this.setUnlocalizedName("KarakurenaiPowered");
        this.setTextureName(ReferenceHelper.setItemName(SAOItems.Karakurenai));
        this.setCreativeTab(null);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        ItemStack sword = new ItemStack(SAOItems.Karakurenai);
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
