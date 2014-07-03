package net.bfox1.sao.help;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author bfox1
 *
 */
public class ReferenceHelper {
	
	
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block,  Reference.MODID + "_" + block.getUnlocalizedName().substring(5));

	}

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item,  Reference.MODID + "_" + item.getUnlocalizedName().substring(5));

	}
	
	public static void registerEnumToolMaterial(String string, ToolMaterial material, int miningLevel, int damageLevel)
	{
		
	}
}
