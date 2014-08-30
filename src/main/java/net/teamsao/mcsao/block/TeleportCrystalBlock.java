package net.teamsao.mcsao.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;

/**
 * Created by bfox1 on 7/20/2014.
 */
public class TeleportCrystalBlock extends Block {
    public TeleportCrystalBlock( ) {
        super(Material.anvil);
        this.setBlockName("BlockCrystalLocator");
        this.setCreativeTab(SAOTabsManager.saoBlocks);
    }



}
