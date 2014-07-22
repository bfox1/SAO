package net.teamsao.mcsao.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.teamsao.mcsao.help.Reference;
import net.minecraft.block.Block;
import net.teamsao.mcsao.help.ReferenceHelper;

/**
 * @author bfox1
 *
 */
public class SBlock {
	
	public static Block ForgingStation = new ForgeStation(); //Special TileEntity That Allows the Creation of Most High-tier Swords and Items.
	
	public static Block Log;
	public static Block DungeonStone = new DungeonStone();
	public static Block SAOPortalBlock = new SAOPortalBlock();
    public static Block CrystalStone = new CrystalStone();
    public static Block TeleportCrystalBlock = new TeleportCrystalBlock();


    public static void init()
	{
		
	}
	
	public static void registerInit()
	{
	//	ReferenceHelper.registerBlock(DungeonStone);
		GameRegistry.registerBlock(SAOPortalBlock, Reference.MODID + (SAOPortalBlock.getUnlocalizedName().substring(5)));
		GameRegistry.registerBlock(DungeonStone, ItemBlockMetaData.class, ReferenceHelper.setBlockName(SBlock.DungeonStone));
        GameRegistry.registerBlock(CrystalStone, ItemBlockMetaData.class, ReferenceHelper.setBlockName(SBlock.CrystalStone));
        //Use the Method below for registering simple Blocks

        ReferenceHelper.registerBlock(ForgingStation);
        ReferenceHelper.registerBlock(TeleportCrystalBlock);
	}


}
