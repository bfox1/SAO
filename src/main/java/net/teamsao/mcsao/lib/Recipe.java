package net.teamsao.mcsao.lib;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.item.SItem;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipe {
	
	public static void init(){
		
		GameRegistry.addRecipe(new ItemStack(SItem.DarkRepulser), " C ", " C ", " D ", 'C', SItem.CrystalliteIngot, 'D', Items.diamond);
	}

}
