package net.teamsao.mcsao.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.teamsao.mcsao.block.*;
import net.teamsao.mcsao.block.dungeonstone.*;
import net.teamsao.mcsao.block.inventoryblocks.ForgeStation;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.block.dungeonstone.*;
import net.teamsao.mcsao.item.items.ItemCobbleSlab;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;

/**
 * @author bfox1
 */
public class SAOBlocks {

	public static final int ForgingStationID = 0;
	
    public static Block ForgingStation = new ForgeStation(); //Special TileEntity That Allows the Creation of Most High-tier Swords and Items.

    public static Block Log;

    public static Block DungeonStone = new DungeonStone();
    public static Block SAOPortalBlock = new SAOPortalBlock();
    public static Block CrystalStone = new CrystalStone();
    public static Block TeleportCrystalBlock = new TeleportCrystalBlock();
    public static Block SafeAreaBlock = new SafeAreaBlock();
    public static Block CobbleRoadBlock = new CobbleRoadBlock();
    public static Block AincradDirtBlock = new AincradDirtBlock();
    public static Block AincradGrassBlock = new AincradGrassBlock();
    public static Block AincradCobbleStairs = new AincradCobbleStairs(CobbleRoadBlock);
    public static Block AincradCobbleHalfSlab = new AincradCobbleSlab(CobbleRoadBlock, false).setCreativeTab(SAOTabsManager.saoBlocks);
    public static Block AincradCobbleDoubleSlab = new AincradCobbleSlab(CobbleRoadBlock, true).setCreativeTab(SAOTabsManager.saoBlocks);
    public static Block AincradStone = new AincradStone();
    public static Block AincradCobble = new AincradCobble();
    public static Block AincradStoneBrick = new AincradStoneBrick();
    public static Block LuminescenceLight = new LuminescenceLight();
    public static Block CrystalliteOre = new CrystalliteOre();
    public static Block AincradSCFloorBlock = new net.teamsao.mcsao.block.AincradSCFloorBlock();
    public static Block AincradSCTowerBlock = new net.teamsao.mcsao.block.AincradSCTowerBlock();
    public static Block AincradLeafBlock = new net.teamsao.mcsao.block.AincradLeafBlock();

    public static void registerInit()
    {
        GameRegistry.registerBlock(SAOPortalBlock, Reference.MODID + (SAOPortalBlock.getUnlocalizedName().substring(5)));
        GameRegistry.registerBlock(DungeonStone, DungeonBlockMetaData.class, DungeonStone.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(CrystalStone, DungeonBlockMetaData.class, CrystalStone.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(AincradLeafBlock, LeafBlockMetaData.class, AincradLeafBlock.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(AincradCobbleHalfSlab, ItemCobbleSlab.class, AincradCobbleHalfSlab.getUnlocalizedName());
        GameRegistry.registerBlock(AincradCobbleDoubleSlab, ItemCobbleSlab.class, AincradCobbleDoubleSlab.getUnlocalizedName());

        //Use the Method below for registering simple Blocks

        ReferenceHelper.registerBlock(ForgingStation);
        ReferenceHelper.registerBlock(TeleportCrystalBlock);
        ReferenceHelper.registerBlock(SafeAreaBlock);
        ReferenceHelper.registerBlock(CobbleRoadBlock);
        ReferenceHelper.registerBlock(AincradDirtBlock);
        ReferenceHelper.registerBlock(AincradGrassBlock);
        ReferenceHelper.registerBlock(AincradCobbleStairs);
        ReferenceHelper.registerBlock(AincradStone);
        ReferenceHelper.registerBlock(AincradCobble);
        ReferenceHelper.registerBlock(AincradStoneBrick);
        ReferenceHelper.registerBlock(LuminescenceLight);
        ReferenceHelper.registerBlock(CrystalliteOre);
        ReferenceHelper.registerBlock(AincradSCTowerBlock);
        ReferenceHelper.registerBlock(AincradSCFloorBlock);
    }
}
