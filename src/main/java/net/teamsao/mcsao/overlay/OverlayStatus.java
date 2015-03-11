package net.teamsao.mcsao.overlay;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
//import scala.tools.ant.sabbus.Make;

import java.util.Iterator;

/**
 * <p>OverlayStatus implements the status effects from Sword Art Online.</p>
 * @see <a href="http://www.minecraftforge.net/wiki/Gui_Overlay#GuiBuffBar_Details">Gui Overlay # GuiBuffBar Details - MCF Wiki</a>
 * @author 5chris100
 */
public class OverlayStatus extends Gui {
    private static Minecraft mc;

    public OverlayStatus(Minecraft mc) {
        super();

        // We need this to invoke the render engine.
        OverlayStatus.mc = mc;
    }

    private static final int STAT_ICON_SIZE = 18;
    private static final int STAT_ICON_SPACING = STAT_ICON_SIZE + 2;
    private static final int STAT_ICON_BASE_U_OFFSET = 0;
    private static final int STAT_ICON_BASE_V_OFFSET = 198;
    private static final int STAT_ICONS_PER_ROW = 25;
    
    private static final ResourceLocation originalIcons = new ResourceLocation("textures/gui/icons.png");

    @SubscribeEvent
    public void onRenderHealthBar(RenderGameOverlayEvent evt) {
        if (evt.isCancelable() || evt.type != RenderGameOverlayEvent.ElementType.HEALTH) {
            return;
        }
        int xPos = 2; // Horizontal position of the overlay relative to the top-left corner.
        int yPos = 2; // Vertical position of the overlay relative to the top-left corner.
        float health = mc.thePlayer.getHealth();
        if (health > 0) {
            // The player is alive! Let's draw the status bar!
            /* TODO: Make the statuses show
             * TODO: Make the statuses show in their correct spot
             */
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            mc.renderEngine.bindTexture(new ResourceLocation("sao", "/assets/sao/textures/overlay/health/healthbase.png"));
            int index;
            for (Iterator iterator = mc.thePlayer.getActivePotionEffects().iterator(); iterator.hasNext(); xPos += STAT_ICON_SPACING) {
                PotionEffect effect = (PotionEffect) iterator.next();
                Potion potion = Potion.potionTypes[effect.getPotionID()];
                if (potion.hasStatusIcon()) {
                    // It has an icon! TODO: Let's change it to our custom image!
                    index = potion.getStatusIconIndex();
                    this.drawTexturedModalRect(xPos, yPos, STAT_ICON_BASE_U_OFFSET + index % STAT_ICONS_PER_ROW * STAT_ICON_SIZE,
                            STAT_ICON_BASE_V_OFFSET + index / STAT_ICONS_PER_ROW * STAT_ICON_SIZE,
                            STAT_ICON_SIZE, STAT_ICON_SIZE);
                }
            }
            
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.renderEngine.bindTexture(originalIcons);

        }
    }

}