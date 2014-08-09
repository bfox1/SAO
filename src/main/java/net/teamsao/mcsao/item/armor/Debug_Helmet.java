package net.teamsao.mcsao.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.ItemSAO;
import net.teamsao.mcsao.item.SItemArmor;

/**
 * Created by bfox1 on 7/11/2014.
 */
public class Debug_Helmet extends SItemArmor {
    public Debug_Helmet(ArmorMaterial material, int armorType, String name) {
        super(material, 0, name);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)

    {
        if(stack.getItem() != SAOItems.debugLeggings)
        {
            return Reference.MODID + ":models/armor/DEBUG_ARMOR1.png";
        }
        else if(stack.getItem() == SAOItems.debugLeggings)
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
