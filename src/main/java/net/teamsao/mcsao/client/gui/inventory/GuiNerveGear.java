package net.teamsao.mcsao.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.teamsao.mcsao.container.ContainerNerveGear;
import net.teamsao.mcsao.helper.Reference;

import net.teamsao.mcsao.inventory.InventoryNerveGear;
import org.lwjgl.opengl.GL11;

public class GuiNerveGear extends GuiContainer {

    private float xSize_lo;
    private float ySize_lo;

    private final InventoryNerveGear inventory;



    public static final ResourceLocation texture = new ResourceLocation(Reference.MODID.toLowerCase(), "/textures/gui/NerveGear.png");


    public GuiNerveGear(ContainerNerveGear containerItem)
    {
        super(containerItem);
        this.inventory = containerItem.inventory;
    }

    /*@Override
    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);
        this.xSize_lo = (float)par1;
        this.ySize_lo = (float)par2;
    }
*/


	@Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2){


        fontRendererObj.drawString("Nerve Gear", 8, 6, 4210752);

        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {

        //draw your Gui here, only thing you need to change is the path
        this.mc.renderEngine.getTexture(texture);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(texture);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }

}
