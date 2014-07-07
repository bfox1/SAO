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

	//Registration.
	public static void init() {
		Elucidator = EnumHelper.addToolMaterial("Elucidator", 10, 8800, 6.0F,10.0F, 3);
		AnnealBlade = EnumHelper.addToolMaterial("Anneal", 4, 1500, 3.0F, 5.0F,6);
		DarkRepulser = EnumHelper.addToolMaterial("DarkRepulser", 9, 8000, 5.0F, 9.0F, 6);
		GuiltyThorn = EnumHelper.addToolMaterial("GuiltyThorn", 5, 3500, 4.0F, 5.0F, 15);
		LambentLight = EnumHelper.addToolMaterial("LambentLight", 5, 5500, 9.0F, 12.0F, 14);
	}
}