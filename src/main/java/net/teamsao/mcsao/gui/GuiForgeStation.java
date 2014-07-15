package net.teamsao.mcsao.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.teamsao.mcsao.container.ContainerForgeStation;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.tileentity.TileEntityForgeStation;
import org.lwjgl.opengl.GL11;

/**
 * Created by bfox1 on 7/12/2014.
 */
public class GuiForgeStation extends GuiContainer  {
    private static final ResourceLocation GuiForgeStation = new ResourceLocation(Reference.MODID + ":gui/forgestation.png");

    public GuiForgeStation(InventoryPlayer inventoryPlayer,
                           TileEntityForgeStation tileEntity) {
        super(new ContainerForgeStation(inventoryPlayer, tileEntity));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2){


        fontRendererObj.drawString("ForgeStation", 8, 6, 4210752);

        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

        //draw your Gui here, only thing you need to change is the path
        //this.mc.renderEngine.getTexture(GuiForgeStation);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(GuiForgeStation);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
