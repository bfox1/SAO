package net.teamsao.mcsao.overlay;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.player.PlayerSAO;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;

/**
 * <p>OverlayHealth implements the health bar from Sword Art Online.</p>
 * @see <a href="http://www.minecraftforge.net/wiki/Gui_Overlay">Gui Overlay - MCF Wiki</a>
 * @author 5chris100
 */
public class OverlayHealth extends Gui {
    private static Minecraft mc;

    public OverlayHealth(Minecraft mc) {
        super();

        // We need this to invoke the render engine.
        OverlayHealth.mc = mc;
    }

    private static final ResourceLocation texturePath = new ResourceLocation(Reference.MODID, "textures/gui/Col.png");

    @SubscribeEvent()
    public void onRenderHealthBar(RenderGameOverlayEvent evt) {
        if (evt.isCancelable() || evt.type != RenderGameOverlayEvent.ElementType.HEALTH) {
            return;
        }
        int xPos = 2; // Horizontal position of the overlay relative to the top-left corner.
        int yPos = 15; // Vertical position of the overlay relative to the top-left corner.
        int hpxPos = 12;
        int hpyPos = 20;
        int hpLength = 80;
        float health = mc.thePlayer.getHealth();
        if (health > 0) {
            // The player is alive! TODO: Let's draw the health bar!
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            mc.renderEngine.bindTexture(new ResourceLocation("sao", "/assets/sao/textures/overlay/health/healthbase.png"));
            Tessellator tess = Tessellator.instance;

            this.drawTexturedModalRect(xPos, yPos, 0, 0, 100, 20);

            PlayerSAO playerProp = PlayerSAO.get(mc.thePlayer);

            GL11.glDisable(GL11.GL_TEXTURE_2D);

            //int precent = playerProp.getHealth()/playerProp.getMaxHealth();
            float precent = health/ mc.thePlayer.getMaxHealth();
            tess.startDrawingQuads();
            if(precent*100 < 33){
                tess.setColorRGBA(255, 0, 0, 255);
            }else if(precent*100 < 67){
                tess.setColorRGBA(250,250, 0, 255);
            }else{
                tess.setColorRGBA(0, 255, 0, 255);
            }
            tess.addVertex(hpxPos+0, hpyPos+10, this.zLevel);
            tess.addVertex(hpxPos+(hpLength*precent), hpyPos+10, this.zLevel);
            tess.addVertex(hpxPos+(hpLength*precent), hpyPos+0, this.zLevel);
            tess.addVertex(hpxPos+0, hpyPos+0, this.zLevel);
            tess.draw();

            GL11.glEnable(GL11.GL_TEXTURE_2D);


        }
    }

}
