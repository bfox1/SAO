package net.teamsao.mcsao.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SAOTabsManager;

/**
 * Created by bfox1 on 7/11/2014.
 */
public abstract class SItemArmor extends ItemArmor {
    public SItemArmor(ArmorMaterial material, int armorSlot, String name) {
        super( material, 0, armorSlot);
        setUnlocalizedName(name);
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(SAOTabsManager.SAO);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)

    {
        if(stack.getItem() == SItem.CoatOfMidnightBody)
        {
            return Reference.MODID + ":models/armor/1.png";
        }
        else if(stack.getItem() ==SItem.CoatOfMidnightLeggings)
        {
            return Reference.MODID + ":models/armor/2.png";
        }

        else
        {
            System.out.println("Invalid Item Armor");
        }
        return null;

    }
}
