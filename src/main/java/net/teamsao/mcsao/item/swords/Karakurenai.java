package net.teamsao.mcsao.item.swords;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.item.SItem;
import net.teamsao.mcsao.item.SItemSword;
import net.teamsao.mcsao.lib.SCreativeTab;

/**
 * Created by bfox1 on 7/8/2014.
 */
public class Karakurenai extends SItemSword {
    public Karakurenai(ToolMaterial p_i45356_1_) {
        super(p_i45356_1_);
        this.setUnlocalizedName("Karakurenai");
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(SCreativeTab.SaoTools);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
        player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        ItemStack sword = new ItemStack(SItem.KarakurenaiPowered);
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
