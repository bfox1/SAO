package net.teamsao.mcsao.lib;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.item.SItem;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipe {

    public static void swordRecipe(Item newItem, int quantity, Item itemRecipe1, Item itemRecipe2, Item itemRecipe3 )
    {
        GameRegistry.addShapedRecipe(new ItemStack(newItem, quantity), new Object[]
        {
            "S", "O", "E", 'S', itemRecipe1, 'O', itemRecipe2, 'E', itemRecipe3
        });

    }
    public static void swordRecipe(Item newItem, int quantity, Item itemRecipe1, Item itemRecipe2, Block itemRecipe3 )
    {
        GameRegistry.addShapedRecipe(new ItemStack(newItem, quantity), new Object[]
                {
                        "S", "O", "E", 'S', itemRecipe1, 'O', itemRecipe2, 'E', itemRecipe3
                });

    }
	
	public static void init(){
		/*
		Temporary Recipes for Item Creation EXCEPT Object Eraser ;)
		 */
		GameRegistry.addRecipe(new ItemStack(SItem.DarkRepulser), " C ", " C ", " D ", 'C', SItem.CrystalliteIngot, 'D', Items.diamond);

        GameRegistry.addShapedRecipe(new ItemStack(SItem.TeleportCrystal, 1), new Object[]
                {
                        "S", "A", 'A', SItem.CrystalliteIngot, 'S', Items.iron_ingot
                });

        GameRegistry.addShapedRecipe(new ItemStack(SItem.Elucidator, 1),new Object[]
                {"S", "O", "T", 'S', Blocks.obsidian, 'O', SItem.CrystalliteIngot, 'T', Items.diamond});


        swordRecipe(SItem.AnnealBlade, 1, Items.iron_ingot, Items.iron_ingot, Blocks.obsidian);
        swordRecipe(SItem.EbonDagger, 1, Items.iron_ingot, Items.wooden_sword, SItem.CrystalliteIngot);
	}

}
