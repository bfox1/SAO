package net.teamsao.mcsao.item;

import net.teamsao.mcsao.creativetabs.SAOTabsManager;

// Created by Skymmer on 8/8/2014.

public class WeaponType extends ItemSAO {
	
	// Weapon Types
	protected static String[] weaponTypes = {"Slash Weapon", "Thrust Weapon", "Blunt Weapon"};
	
	// Current Weapon Type
	protected static String weaponType;
	
	// Weapon type in weaponTypes array
	protected static int weaponTypeId;
	
	public WeaponType() {
		super();
		this.setCreativeTab(SAOTabsManager.SAO);
	}
	
	// Sets weapon type
	public static String setWeaponType(int type) {
		weaponType = weaponTypes[type];
		weaponTypeId = type + 1;
		return weaponTypes[type];
	}
	
	// Gets weapon type
	public static String getWeaponType() {
		return weaponType;
	}
	
	// Gets weapon type by id
	public static int getWeaponTypeById() {
		return weaponTypeId;
	}
	
	// Gets the weapon types array
	public static String[] getWeaponTypes() {
		return weaponTypes;
	}
	
	// Gets size of weapon types array
	public static int getWeaponTypesArraySize() {
		return weaponTypes.length;
	}
}
