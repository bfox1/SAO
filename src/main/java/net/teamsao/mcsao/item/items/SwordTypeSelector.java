package net.teamsao.mcsao.item.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.item.SwordType;

// Created by Skymmer on 8/9/2014.

public class SwordTypeSelector extends SwordType {

	public SwordTypeSelector() {
		super();
		this.setUnlocalizedName("WeaponTypeSelector");
		this.setTextureName(ReferenceHelper.setItemName(this));
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if(player.isSneaking()) {
			int swordTypeArrayN = this.getSwordTypeById();
			int swordTypesArraySize = this.getSwordTypesArraySize();
			
			if(swordTypesArraySize > swordTypeArrayN) {
				this.setSwordType(swordTypeArrayN);
				
				swordTypeArrayN++;
			}else if(swordTypesArraySize <= swordTypeArrayN) {
				swordTypeArrayN = 0;
				
				this.setSwordType(swordTypeArrayN);
			}
		}
		
		return itemstack;
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool) {
		if(itemstack.stackTagCompound == null) {
			itemstack.stackTagCompound = new NBTTagCompound();
		}
		
		if(itemstack.stackTagCompound.getString("weaponType") == null && swordType == null || itemstack.stackTagCompound.getString("weaponType") == "" && swordType == null) {
			itemstack.stackTagCompound.setString("weaponType", "One-Handed");
		}else if(itemstack.stackTagCompound.getString("weaponType") == null || itemstack.stackTagCompound.getString("weaponType") == "") {
			itemstack.stackTagCompound.setString("weaponType", swordType);
		}else if(itemstack.stackTagCompound.getInteger("weaponId") == 0 && swordTypeId == 0) {
			itemstack.stackTagCompound.setInteger("weaponId", swordTypeId);
		}else if(itemstack.stackTagCompound.getInteger("weaponId") == 0) {
			itemstack.stackTagCompound.setInteger("weaponId", 1);
		}
		
		list.add("Selected Weapon Type: " + "" + swordType);
	}
}
