package net.teamsao.mcsao.overlay;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.player.playerextendedprop.PlayerCol;
import org.lwjgl.opengl.GL11;

/**
 * @author bfox1
 */
public class OverlayCol extends Gui {

    private Minecraft mc;

    private static final ResourceLocation texturePath = new ResourceLocation(Reference.MODID, "textures/gui/Col.png");

    public OverlayCol(Minecraft mc)
    {
        super();
        this.mc = mc;
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent event)
    {
        if(event.isCancelable() || event.type != ElementType.EXPERIENCE)
        {
            return;
        }

        PlayerCol props = PlayerCol.get(this.mc.thePlayer);



        int xPos = 2;
        int yPos = 2;

        //Setting all color values to 1.0F will render the texture as it looks in your texture file
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);

        //Bind your texture to the render Engine
        this.mc.getTextureManager().bindTexture(texturePath);
        /*
        *The parameters for drawTexturedModalRect is:
        * drawTexturedModalRect(int x, inty, int u, int v, int width, int height);
        * x & y are the on-screen Coords.
        * u & v are the coords of the top upper-left pixel in the Texture File from which to start.
        * width and height are how many pixels to render from the point (u, v)
         */

        this.drawTexturedModalRect(xPos, yPos, 0, 0, 56, 9);

        int colBarWidth = (int)(((float)props.getColAmount()/props.getCol_thousand())*49);

       // System.out.println("GUICOL CurrentCol width" + colBarWidth);
        this.drawTexturedModalRect(xPos + 3, yPos + 3, 0, 9, colBarWidth, 3);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
    }
}
