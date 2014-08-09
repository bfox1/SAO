package net.teamsao.mcsao.material;

import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author bfox1
 *
 */
public class SArmorMaterial{
	
	public static ArmorMaterial coatOfMidnight = EnumHelper.addArmorMaterial("CoatOfMidnight", 21, new int[]{2, 4, 3, 2}, 25);
	public static ArmorMaterial nerveGear = EnumHelper.addArmorMaterial("NerveGear", -1, new int[]{1}, 1);

    public static ArmorMaterial debugMaterial = EnumHelper.addArmorMaterial("DebugMaterial", 10000, new int[]{5, 8, 6, 5}, 50);

}
