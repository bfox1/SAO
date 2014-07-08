package net.teamsao.mcsao.material;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.teamsao.mcsao.help.ReferenceHelper;

/**
 * @author bfox1
 * 
 */
public class SToolMaterial {

	// ToolMaterial
	public static ToolMaterial Elucidator;
	public static ToolMaterial AnnealBlade;
	public static ToolMaterial DarkRepulser;
	public static ToolMaterial GuiltyThorn;
	public static ToolMaterial LambentLight;
	public static ToolMaterial EbonDagger;
	public static ToolMaterial Karakurenai;
	public static ToolMaterial MateChopper;
    public static ToolMaterial ShadowDagger;

	//Registration.
	public static void init() {
        /**
         *  1, String name of Tool
         *  2, Harvest Level
         *  3, Durability Level
         *  4, Effeciency
         *  5, Damage vs. Entity
         *  6, Enchantability
         */
		Elucidator = EnumHelper.addToolMaterial("Elucidator", 10, 8800, 7.0F,13.0F, 3);
		AnnealBlade = EnumHelper.addToolMaterial("Anneal", 4, 1500, 3.0F, 5.0F,6);
		DarkRepulser = EnumHelper.addToolMaterial("DarkRepulser", 9, 8000, 5.0F, 9.0F, 6);
		GuiltyThorn = EnumHelper.addToolMaterial("GuiltyThorn", 5, 3500, 4.0F, 5.0F, 15);
		LambentLight = EnumHelper.addToolMaterial("LambentLight", 5, 5500, 9.0F, 12.0F, 14);
		EbonDagger = EnumHelper.addToolMaterial("EbonDagger", 5, 4600, 6.0F, 5.0F, 30);
		Karakurenai = EnumHelper.addToolMaterial("Karakurenai", 6, 4500, 6.0F, 7.0F, 15);
		MateChopper = EnumHelper.addToolMaterial("MateChopper", 12, 9900, 9.0F, 15.0F, 2);
        ShadowDagger = EnumHelper.addToolMaterial("ShadowDagger", 2, 5700, 5.0F, 4.0F, 15);
	}
}