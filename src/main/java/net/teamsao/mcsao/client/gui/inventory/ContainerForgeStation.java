package net.teamsao.mcsao.client.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.teamsao.mcsao.tileentity.TileEntityForgeStation;

/**
 * Created by bfox1 on 12/31/2014.
 */
public class ContainerForgeStation extends Container {
    public ContainerForgeStation(InventoryPlayer inventoryPlayer, TileEntityForgeStation tileEntity) {
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return false;
    }
}
