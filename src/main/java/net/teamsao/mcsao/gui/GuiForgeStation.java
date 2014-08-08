package net.teamsao.mcsao.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
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
 * Edited by Skymmer on 8/7/2014.
 */
public class GuiForgeStation extends GuiContainer  {
	
    private static final ResourceLocation GuiForgeStation = new ResourceLocation(Reference.MODID + ":gui/forgestation.png");
    
    public TileEntityForgeStation forgeStation;

    public GuiForgeStation(InventoryPlayer inventoryPlayer, TileEntityForgeStation tileEntity) {
        super(new ContainerForgeStation(inventoryPlayer, tileEntity));
        
        this.forgeStation = tileEntity;
        
        //Defines size of gui - Skymmer
        this.xSize = 176;
        this.ySize = 166;
    }

    public void drawGuiContainerForegroundLayer(int par1, int par2){
    	String s = this.forgeStation.hasCustomInventoryName() ? this.forgeStation.getInventoryName() : I18n.format(this.forgeStation.getInventoryName(), new Object[0]); // Checks if forge station is localized, if not return default inventory name - Skymmer

    	this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752); // Draws the name roughly in the same spot as in the furnace - Skymmer
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

        //draw your Gui here, only thing you need to change is the path
        //this.mc.renderEngine.getTexture(GuiForgeStation);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        Minecraft.getMinecraft().getTextureManager().bindTexture(GuiForgeStation);
        
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
