package net.teamsao.mcsao.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.teamsao.mcsao.tileentity.TileEntityForgeStation;

/**
 * Created by bfox1 on 12/20/2014.
 */
public class ForgeStation extends BlockContainer {

    protected ForgeStation() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int metaIn) {

        return new TileEntityForgeStation();
    }
}
