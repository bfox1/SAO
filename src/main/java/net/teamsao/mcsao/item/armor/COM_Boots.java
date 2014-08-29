package net.teamsao.mcsao.item.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.ItemSAO;
import net.teamsao.mcsao.lib.SAOTabsManager;
//

/**
 * Created by bfox1 on 7/9/2014.
 */
public class COM_Boots extends ItemArmor {
    public COM_Boots(ArmorMaterial material, int armorType, String name) {
        super(material, 3, armorType);
        setUnlocalizedName(name);
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab( SAOTabsManager.SAO);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(stack.getItem() == SAOItems.CoatOfMidnightBoots)
        {
            return Reference.MODID + ":models/armor/COM_Armor1.png";
        }
        else if(stack.getItem() == SAOItems.CoatOfMidnightLeggings)
        {
            return Reference.MODID + ":models/armor/COM_Armor2.png";
        }

        else
        {
            System.out.println("Invalid Item Armor");
        }
        return null;

    }
}
