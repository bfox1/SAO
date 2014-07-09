package net.teamsao.mcsao.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SCreativeTab;

/**
 * Created by bfox1 on 7/8/2014.
 */
public class ShadowDagger extends SItemSword {
    public ShadowDagger(ToolMaterial p_i45356_1_) {
        super(p_i45356_1_);
        this.setUnlocalizedName("ShadowDagger");
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(SCreativeTab.SAO_Tools);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        ItemStack sword = new ItemStack(SItem.ShadowDaggerPowered);

        if(!par2.isRemote)
        {
            if(par3.isSneaking())
            {
                sword.addEnchantment(Enchantment.sharpness, 1);
                sword.setItemDamage(par1.getItemDamage());
                return sword;
            }
        }

        return par1;
    }
}
