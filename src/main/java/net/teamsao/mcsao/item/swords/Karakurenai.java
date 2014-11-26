package net.teamsao.mcsao.item.swords;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.SItemSword;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;
//

/**
 * Created by bfox1 on 7/8/2014.
 */
public class Karakurenai extends SItemSword {
    public Karakurenai(ToolMaterial p_i45356_1_, String typeName ) {
        super(p_i45356_1_,typeName);
        this.setUnlocalizedName("Karakurenai");
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(SAOTabsManager.saoTools);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        ItemStack sword = new ItemStack(SAOItems.KarakurenaiPowered);
        if(!world.isRemote)
        {
            if(player.isSneaking())
            {
                sword.addEnchantment(Enchantment.sharpness, 1);
                sword.setItemDamage(par1ItemStack.getItemDamage());
                return sword;
            }
        }
        return par1ItemStack;
    }


}
