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

	public static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));


	}
	
	/**
	 * @name this is for special Items/Blocks in which the texture needs to be the same as another.
	 * @return returns the Variable test
	 *  
	 */
	public static String setItemName(Item name)
	{
		String test = Reference.MODID.toLowerCase() + ":" + name.getUnlocalizedName().substring(5);
		return test;

	}
	
	/**
	 * @name this is for special Items/Blocks in which the texture needs to be the same as another.
	 * @return returns the Variable test
	 */
	public static String setBlockName(Block name){
		String test = Reference.MODID.toLowerCase() + ":" + name.getUnlocalizedName().substring(5);
		return test;
	}
}
