package net.bfox1.sao.lib;

import net.bfox1.sao.Item.SItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipe {
	
	public static void init(){
		
		GameRegistry.addRecipe(new ItemStack(SItem.DarkRepulser), " C ", " C ", " D ", 'C', SItem.CrystalliteIngot, 'D', Items.diamond);
	}

}
