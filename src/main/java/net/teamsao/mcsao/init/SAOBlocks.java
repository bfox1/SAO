package net.teamsao.mcsao.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.teamsao.mcsao.block.*;
import net.teamsao.mcsao.block.inventoryblocks.ForgeStation;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.item.ItemIngot;

/**
 * Created by bfox1 on 7/24/2014.
 */
public class SAOBlocks {

	public static final int ForgingStationID = 0;
	
    public static Block ForgingStation = new ForgeStation(); //Special TileEntity That Allows the Creation of Most High-tier Swords and Items.

    public static Block Log;
    public static Block DungeonStone = new net.teamsao.mcsao.block.dungeonstone.DungeonStone();
    public static Block SAOPortalBlock = new net.teamsao.mcsao.block.SAOPortalBlock();
    public static Block CrystalStone = new net.teamsao.mcsao.block.dungeonstone.CrystalStone();
    public static Block TeleportCrystalBlock = new net.teamsao.mcsao.block.TeleportCrystalBlock();
    public static Block SafeAreaBlock = new net.teamsao.mcsao.block.SafeAreaBlock();
    public static Block CobbleRoadBlock = new net.teamsao.mcsao.block.CobbleRoadBlock();
    public static Block AincradDirtBlock = new net.teamsao.mcsao.block.AincradDirtBlock();
    public static Block AincradGrassBlock = new net.teamsao.mcsao.block.AincradGrassBlock();

    public static void registerInit()
    {
        //	ReferenceHelper.registerBlock(DungeonStone);
        GameRegistry.registerBlock(SAOPortalBlock, Reference.MODID + (SAOPortalBlock.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(DungeonStone, ItemBlockMetaData.class, ReferenceHelper.setBlockName(SAOBlocks.DungeonStone));
        GameRegistry.registerBlock(CrystalStone, ItemBlockMetaData.class, ReferenceHelper.setBlockName(SAOBlocks.CrystalStone));
        //Use the Method below for registering simple Blocks

        ReferenceHelper.registerBlock(ForgingStation);
        ReferenceHelper.registerBlock(TeleportCrystalBlock);
        ReferenceHelper.registerBlock(SafeAreaBlock);
        ReferenceHelper.registerBlock(CobbleRoadBlock);
        ReferenceHelper.registerBlock(AincradDirtBlock);
        ReferenceHelper.registerBlock(AincradGrassBlock);
    }
}
