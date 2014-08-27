package net.teamsao.mcsao.help;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.teamsao.mcsao.block.BlockSAO;

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
