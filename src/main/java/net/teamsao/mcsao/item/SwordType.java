package net.teamsao.mcsao.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.teamsao.mcsao.lib.SAOTabsManager;

// Created by Skymmer on 8/9/2014.

public class SwordType extends ItemSAO {
	
	// Weapon Types
	protected static String[] swordTypes = {"Katana", "Blade Throwing", "Two-Handed Battle Axe", 
											"Two-Handed Straight Sword", "Claws", "One-Handed War Hammer", 
											"One-Handed Assault Spear", "One-Handed Rapiers", "One-Handed Dagger", 
											"One-Handed Curved Sword", "One-Handed Straight Sword"};
	
	// Current Weapon Type
	protected static String swordType;
	
	// Weapon type in weaponTypes array
	protected static int swordTypeId;
	
	public SwordType() {
		super();
		this.setCreativeTab(SAOTabsManager.SAO);
	}
	
	// Sets weapon type
	public static String setSwordType(int type) {
		swordType = swordTypes[type];
		swordTypeId = type + 1;
		return swordTypes[type];
	}
	
	// Gets weapon type
	public static String getSwordType() {
		return swordType;
	}
	
	// Gets weapon type by id
	public static int getSwordTypeById() {
		return swordTypeId;
	}
	
	// Gets the weapon types array
	public static String[] getSwordTypes() {
		return swordTypes;
	}
	
	// Gets size of weapon types array
	public static int getSwordTypesArraySize() {
		return swordTypes.length;
	}
}
