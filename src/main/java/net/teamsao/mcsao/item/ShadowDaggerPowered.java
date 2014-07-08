package net.teamsao.mcsao.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;

/**
 * Created by bfox1 on 7/8/2014.
 */
public class ShadowDaggerPowered extends SItemSword {
    public ShadowDaggerPowered(ToolMaterial p_i45356_1_) {
        super(p_i45356_1_);
        this.setUnlocalizedName("ShadowDaggerPowered");
        this.setTextureName(ReferenceHelper.setItemName(SItem.ShadowDagger));
        this.setCreativeTab(null);
    }

    @Override
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving)
    {
        par2EntityLiving.addPotionEffect(new PotionEffect(Potion.blindness.id, 20 * 5, 2));
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        ItemStack sword = new ItemStack(SItem.MateChopper);

        if(!par2.isRemote)
        {
            if(par3.isSneaking())
            {
                sword.setItemDamage(par1.getItemDamage());
                return sword;
            }
        }

        return par1;
    }
}
