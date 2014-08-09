package net.teamsao.mcsao.recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.init.SAOItems;

// Created by Skymmer on 8/8/2014.

public class ForgeStationRecipes {
	
	private static final ForgeStationRecipes forgingBase = new ForgeStationRecipes();
    private Map incineratorList = new HashMap();
    private Map experienceList = new HashMap();
    private static final String __OBFID = "CL_00000085";

    public static ForgeStationRecipes forging()  {
        return forgingBase;
    }
    
    private ForgeStationRecipes() {
    	
    }
    
    public void forge(ItemStack itemstack, ItemStack itemstack2, float exp) {
    }

    public void putRecipeAndExpInList(ItemStack itemstack, ItemStack itemstack2, float exp) {
        this.incineratorList.put(itemstack, itemstack2);
        this.experienceList.put(itemstack2, Float.valueOf(exp));
    }
    
    public ItemStack getForgingResult(ItemStack itemstack) {
        Iterator iterator = this.incineratorList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.canSmelt(itemstack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    } // getValue is output, getKey is input
    
    private boolean canSmelt(ItemStack itemstack, ItemStack itemstack2) {
        return itemstack2.getItem() == itemstack.getItem() && (itemstack2.getItemDamage() == 32767 || itemstack2.getItemDamage() == itemstack.getItemDamage());
    }

    public Map getIncineratingList() {
        return this.incineratorList;
    }

    public float func_151398_b(ItemStack itemstack) {
        float ret = itemstack.getItem().getSmeltingExperience(itemstack);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do {
            if (!iterator.hasNext()) {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.canSmelt(itemstack, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }

}
