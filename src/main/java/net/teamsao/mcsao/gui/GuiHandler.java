package net.teamsao.mcsao.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.client.gui.inventory.GuiForgeStation;
import net.teamsao.mcsao.client.gui.inventory.GuiNerveGear;
import net.teamsao.mcsao.container.ContainerForgeStation;
import net.teamsao.mcsao.container.ContainerNerveGear;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.inventory.InventoryNerveGear;
import net.teamsao.mcsao.tileentity.TileEntityForgeStation;
//import net.teamsao.mcsao.tileentity.TileEntityNerveGear;
import net.teamsao.mcsao.tileentity.TileEntityTeleportCrystalBlock;
import cpw.mods.fml.common.network.IGuiHandler;

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
        if(ID == Reference.GUI_ITEM_INV)
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
        if(ID == Reference.GUI_ITEM_INV)
        {
            return new GuiNerveGear((ContainerNerveGear) new ContainerNerveGear(player, player.inventory, new InventoryNerveGear(player.getHeldItem())));
        }
        if(ID == Reference.GUI_TELEPORT_CRYSTAL)
        {
        	return new GuiTeleportCrystal(player);
        }
        if(ID == Reference.GUI_TELEPORT_CRYSTAL_BLOCK)
        {
            return new GuiTeleportCrystalBlock((TileEntityTeleportCrystalBlock) tileEntity);
        }
        if(ID == Reference.GUI_SKILL)
        {
            return new GuiSkillMenu(Minecraft.getMinecraft());
        }

        return null;

    }
    }

