package net.teamsao.mcsao.item.swords;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.item.SItemSword;
//

/**
 * Created by bfox1 on 7/9/2014.
 */
public class ObjectEraser extends SItemSword {
    public ObjectEraser(ToolMaterial p_i45356_1_) {
        super(p_i45356_1_);
        this.setUnlocalizedName("ObjectEraser");
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(null);

    }

    @Override
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {

        EntityPlayer player = ((EntityPlayer) entity);
        if (!world.isRemote) {
            if (itemstack.isItemEnchanted() == false) {
                itemstack.addEnchantment(Enchantment.sharpness, 5);
            }

            if (entity.isBurning()) {
                        entity.extinguish();
                    }
                    if (player.isPotionActive(Potion.wither) || player.isPotionActive(Potion.weakness) || player.isPotionActive(Potion.poison) || player.isPotionActive(Potion.confusion) || player.isPotionActive(Potion.blindness)) {
                        player.clearActivePotions();
                    }
                    if (player.getHealth() != player.getMaxHealth()) {
                        player.heal(10);
                    }



            }
        }
    }
