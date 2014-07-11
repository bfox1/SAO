package net.teamsao.mcsao.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.item.SItem;
import net.teamsao.mcsao.item.SItemArmor;

/**
 * Created by bfox1 on 7/11/2014.
 */
public class Debug_Leggings extends SItemArmor {
    public Debug_Leggings(ArmorMaterial material, int armorType, String name) {
        super(material, 2, name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)

    {
        if(stack.getItem() != SItem.debugLeggings)
        {
            return Reference.MODID + ":models/armor/DEBUG_ARMOR1.png";
        }
        else if(stack.getItem() == SItem.debugLeggings)
        {
            return Reference.MODID + ":models/armor/DEBUG_ARMOR2.png";
        }

        else
        {
            System.out.println("Invalid Item Armor");
        }
        return null;

    }
}
