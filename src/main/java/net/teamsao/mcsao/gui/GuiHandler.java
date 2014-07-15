package net.teamsao.mcsao.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.teamsao.mcsao.container.ContainerForgeStation;
import net.teamsao.mcsao.tileentity.TileEntityForgeStation;

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
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof TileEntityForgeStation){
            return new GuiForgeStation(player.inventory, (TileEntityForgeStation) tileEntity);
        }
        return null;

    }
    }

