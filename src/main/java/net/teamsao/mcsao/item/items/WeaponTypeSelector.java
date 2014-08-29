package net.teamsao.mcsao.item.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.item.WeaponType;

// Created by Skymmer on 8/8/2014.

public class WeaponTypeSelector extends WeaponType {

	public WeaponTypeSelector() {
		super();
		this.setUnlocalizedName("WeaponTypeSelector");
		this.setTextureName(ReferenceHelper.setItemName(this));
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
		if(player.isSneaking()) {
			int weaponTypeArrayN = this.getWeaponTypeById();
			int weaponTypesArraySize = this.getWeaponTypesArraySize();
			
			if(weaponTypesArraySize > weaponTypeArrayN) {
				this.setWeaponType(weaponTypeArrayN);
				
				weaponTypeArrayN++;
			}else if(weaponTypesArraySize <= weaponTypeArrayN) {
				weaponTypeArrayN = 0;
				
				this.setWeaponType(weaponTypeArrayN);
			}
		}
		
		return itemstack;
	}
	
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean bool) {
		if(itemstack.stackTagCompound == null) {
			itemstack.stackTagCompound = new NBTTagCompound();
		}
		
		if(itemstack.stackTagCompound.getString("weaponType") == null && weaponType == null || itemstack.stackTagCompound.getString("weaponType") == "" && weaponType == null) {
			itemstack.stackTagCompound.setString("weaponType", "One-Handed");
		}else if(itemstack.stackTagCompound.getString("weaponType") == null || itemstack.stackTagCompound.getString("weaponType") == "") {
			itemstack.stackTagCompound.setString("weaponType", weaponType);
		}else if(itemstack.stackTagCompound.getInteger("weaponId") == 0 && weaponTypeId == 0) {
			itemstack.stackTagCompound.setInteger("weaponId", weaponTypeId);
		}else if(itemstack.stackTagCompound.getInteger("weaponId") == 0) {
			itemstack.stackTagCompound.setInteger("weaponId", 1);
		}
		
		list.add("Selected Weapon Type: " + "" + weaponType);
	}
}
