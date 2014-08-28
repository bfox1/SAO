package net.teamsao.mcsao.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHangingEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.container.ContainerForgeStation;
import net.teamsao.mcsao.container.ContainerNerveGear;
import net.teamsao.mcsao.inventory.InventoryNerveGear;
import net.teamsao.mcsao.tileentity.TileEntityForgeStation;
//import net.teamsao.mcsao.tileentity.TileEntityNerveGear;

/**
 * Created by bfox1 on 7/12/2014.
 */
public class GuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);

        if(tileEntity instanceof TileEntityForgeStation){
            return new ContainerForgeStation(player.inventory, (TileEntityForgeStation) tileEntity);
        }
        if(ID == SwordArtOnline.GUI_ITEM_INV)
        {
            return new ContainerNerveGear(player, player.inventory, new InventoryNerveGear(player.getHeldItem()));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof TileEntityForgeStation){
            return new GuiForgeStation(player.inventory, (TileEntityForgeStation) tileEntity);
        }
        if(ID == SwordArtOnline.GUI_ITEM_INV)
        {
            return new GuiNerveGear((ContainerNerveGear) new ContainerNerveGear(player, player.inventory, new InventoryNerveGear(player.getHeldItem())));
        }
        return null;

    }
    }

