package net.teamsao.mcsao.helper;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author bfox1
 *
 */
public class ReferenceHelper {
	
	
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block,  block.getUnlocalizedName().substring(5));

	}

	public static void registerItem(Item item) {
        GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
    }
	
	/**
	 * @param name Used for special items in which the texture needs to be the same as another.
	 * @return The display name for the item.
	 */
	public static String setItemName(Item name) {
		return Reference.MODID.toLowerCase() + ":" + name.getUnlocalizedName().substring(5);
	}
	
	/**
	 * @param name Used for special blocks in which the texture needs to be the same as another.
	 * @return The display name for the block.
	 */
	public static String setBlockName(Block name){
		return Reference.MODID.toLowerCase() + ":" + name.getUnlocalizedName().substring(5);
	}
}
