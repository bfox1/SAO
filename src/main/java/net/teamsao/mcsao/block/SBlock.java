package net.teamsao.mcsao.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.teamsao.mcsao.help.Reference;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.help.ReferenceHelper;

/**
 * @author bfox1
 *
 */
public class SBlock {
	
	public static Block ForgingStation; //Special TileEntity That Allows the Creation of Most High-tier Swords and Items.
	
	public static Block Log;
	public static Block DungeonStone = new DungeonStone();
	public static Block SAOPortalBlock = new SAOPortalBlock();
	

	
	public static void init()
	{
		
	}
	
	public static void registerInit()
	{
	//	ReferenceHelper.registerBlock(DungeonStone);
		GameRegistry.registerBlock(SAOPortalBlock, Reference.MODID + (SAOPortalBlock.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(DungeonStone, ItemBlockDungeonStone.class, ReferenceHelper.setBlockName(SBlock.DungeonStone));
	}

	
}
