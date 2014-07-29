package net.teamsao.mcsao.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.teamsao.mcsao.container.ContainerNerveGear;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.tileentity.TileEntityNerveGear;
import org.lwjgl.opengl.GL11;

public class GuiNerveGear extends GuiContainer {

    public static final ResourceLocation texture = new ResourceLocation(Reference.MODID.toLowerCase(), "/textures/gui/NerveGear.png");

    public TileEntityNerveGear nerveGear;

	public GuiNerveGear(InventoryPlayer inventoryPlayer, TileEntityNerveGear tileEntityNerveGear) {
		super(new ContainerNerveGear(inventoryPlayer, tileEntityNerveGear));

        this.nerveGear = tileEntityNerveGear;


	}

	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2){


        fontRendererObj.drawString("NerveGear", 8, 6, 4210752);

        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

        //draw your Gui here, only thing you need to change is the path
        //this.mc.renderEngine.getTexture(GuiForgeStation);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

}
