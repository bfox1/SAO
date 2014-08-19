package net.teamsao.mcsao.overlay;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

/**
 * OverlayHealth implements the health bar from Sword Art Online.
 * @author 5chris100
 * @see "http://www.minecraftforge.net/wiki/Gui_Overlay"
 */
public class OverlayHealth extends Gui {
    private static Minecraft mc;

    public OverlayHealth(Minecraft mc) {
        super();

        // We need this to invoke the render engine.
        OverlayHealth.mc = mc;
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderHealthBar(RenderGameOverlayEvent evt) {
        if (evt.isCancelable() || evt.type != RenderGameOverlayEvent.ElementType.HEALTH) {
            return;
        }
        int xPos = 2; // Horizontal position of the overlay relative to the top-left corner.
        int yPos = 2; // Vertical position of the overlay relative to the top-left corner.
        float health = OverlayHealth.mc.thePlayer.getHealth();
        if (health > 1) {

        }
    }

}
