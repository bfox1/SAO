package net.teamsao.mcsao.overlay;

import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.player.PlayerSAO;

import org.lwjgl.opengl.GL11;

import java.io.PipedOutputStream;
import java.util.Iterator;
import java.awt.*;

/**
 * <p>OverlayHealth implements the health bar from Sword Art Online.</p>
 * @see <a href="http://www.minecraftforge.net/wiki/Gui_Overlay">Gui Overlay - MCF Wiki</a>
 * @author 5chris100
 * @author SirPwn
 */

public class OverlayHealth extends Gui {

    private static Minecraft mc;
    
    public static ResourceLocation textureBase = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/overlay/health/healthBarBase.png");
    public static ResourceLocation textureBar = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/overlay/health/healthBar.png");
    public static final ResourceLocation originalIcons = new ResourceLocation("textures/gui/icons.png");

    public static double frameSizeLen;

    public OverlayHealth(Minecraft mc) 
    {
        super();

        // We need this to invoke the render engine.
        OverlayHealth.mc = mc;
    }



    @SubscribeEvent()
    public void onRenderHealthBar(RenderGameOverlayEvent.Pre evt)
    {
        if(evt.type.equals(RenderGameOverlayEvent.ElementType.FOOD) && evt.isCancelable())
        {
            if(mc.thePlayer.worldObj.provider.dimensionId == 2)
            {
                evt.setCanceled(true);
            }
        }
    	if (evt.type.equals(RenderGameOverlayEvent.ElementType.HEALTH) && evt.isCancelable()) {
            if(mc.thePlayer.worldObj.provider.dimensionId == 2) {
                evt.setCanceled(true);
            }




            double xPos = 5; // Horizontal position of the overlay relative to the top-left corner.
            double yPos = 5; // Vertical position of the overlay relative to the top-left corner.
            double frameXSize = 150;
            double frameYSize = 20;
            double barXSize = 88.5;
            double barYSize = 6;
            this.frameSizeLen = frameXSize;
            float health = mc.thePlayer.getHealth();
            float food = mc.thePlayer.getFoodStats().getFoodLevel();

            if (health > 0 && mc.thePlayer.worldObj.provider.dimensionId == 2) {
                // The player is alive! TODO: Let's draw the health bar!

                //int precent = playerProp.getHealth()/playerProp.getMaxHealth();
                double percent = health/ mc.thePlayer.getMaxHealth();
                drawFrame(xPos, yPos, frameXSize, frameYSize);
                drawBar(percent, xPos + 51.2, yPos + 4.25, barXSize, barYSize);
            }
            if (food > 0 && mc.thePlayer.worldObj.provider.dimensionId == 2)
            {
                double percent = food / 20;
                drawFoodBar(percent, xPos + 34, yPos + 11, 15, 3);
            }

        }
    }
    
    public void drawBar(double percent, double xPos, double yPos, double xSize, double ySize) 
    {
    	this.mc.renderEngine.bindTexture(textureBar);
    	
    	GL11.glPushMatrix();
        GL11.glColor4f(1.5F - (float) percent, (float) percent, 0.1F, 0.75F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        
        Tessellator t = Tessellator.instance;
        
        t.startDrawingQuads();
        t.addVertexWithUV(xPos, yPos, this.zLevel, 0, 0);
        t.addVertexWithUV(xPos, yPos + ySize, this.zLevel, 0, 1);
        t.addVertexWithUV(xPos + (xSize * percent), yPos + ySize, this.zLevel, 1*percent, 1);
        t.addVertexWithUV(xPos + (xSize * percent), yPos, this.zLevel, 1*percent, 0);
        t.draw();
        
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(originalIcons);
    }

    public void drawFoodBar(double percent, double xPos, double yPos, double xSize, double ySize)
    {
        this.mc.renderEngine.bindTexture(textureBar);

        GL11.glPushMatrix();
        GL11.glColor4f(1.5F - (float)percent, (float) percent, 0.1F, 0.75F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);

        Tessellator t = Tessellator.instance;

        t.startDrawingQuads();
        t.addVertexWithUV(xPos, yPos, this.zLevel, 0, 0);
        t.addVertexWithUV(xPos, yPos + ySize, this.zLevel, 0, 1);
        t.addVertexWithUV(xPos + (xSize * percent), yPos + ySize, this.zLevel, 1 * percent, 1);
        t.addVertexWithUV(xPos + (xSize * percent), yPos, this.zLevel, 1*percent, 0);
        t.draw();

        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(originalIcons);
    }
    
    public void drawFrame(double xPos, double yPos, double xSize, double ySize) 
    {
    	this.mc.renderEngine.bindTexture(textureBase);
    	
    	GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        Tessellator t = Tessellator.instance;
        
        t.startDrawingQuads();
        t.addVertexWithUV(xPos, yPos, this.zLevel, 0, 0);
        t.addVertexWithUV(xPos, yPos + ySize, this.zLevel, 0, 1);
        t.addVertexWithUV(xPos + xSize, yPos + ySize, this.zLevel, 1, 1);
        t.addVertexWithUV(xPos + xSize, yPos, this.zLevel, 1, 0);
        t.draw();
        
        GL11.glPopMatrix();
    }
}
