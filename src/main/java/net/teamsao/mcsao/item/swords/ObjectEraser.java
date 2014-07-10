package net.teamsao.mcsao.item.swords;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.item.SItem;
import net.teamsao.mcsao.item.SItemSword;
import net.teamsao.mcsao.lib.SCreativeTab;

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

        if(!world.isRemote) {
            if (itemstack.isItemEnchanted() == false) {
                itemstack.addEnchantment(Enchantment.sharpness, 5);
            }


        }
    }
}
