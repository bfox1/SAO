package net.bfox1.sao.material;

import net.bfox1.sao.help.ReferenceHelper;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author bfox1
 * 
 */
public class SToolMaterial {

	// ToolMaterial
	public static ToolMaterial Elucidator;
	public static ToolMaterial AnnealBlade;
	public static ToolMaterial DarkRepulser;

	//Registration.
	public static void init() {
		Elucidator = EnumHelper.addToolMaterial("Elucidator", 3, 8800, 6.0F,10.0F, 3);
		AnnealBlade = EnumHelper.addToolMaterial("Anneal", 4, 1500, 3.0F, 5.0F,6);
		DarkRepulser = EnumHelper.addToolMaterial("Anneal", 4, 1500, 3.0F, 5.0F, 6);
	}
}