package net.teamsao.mcsao.overlay;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.teamsao.mcsao.helper.ColorHelper;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.player.PlayerSAO;
import org.lwjgl.opengl.GL11;

import java.util.EnumSet;

/**
 * Created by bfox1 on 11/13/2014.
 */
public class OverlayVersionText extends Gui {

    private static Minecraft mc;



    private static FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

    public OverlayVersionText(Minecraft mc)
    {
        super();
        this.mc = mc;
    }

    @SubscribeEvent
    public void renderScreen(RenderGameOverlayEvent event)
    {

        if(event.type.equals(RenderGameOverlayEvent.ElementType.FOOD) && event.isCancelable()) {
            if (mc.thePlayer.worldObj.provider.dimensionId == 2) {
                event.setCanceled(true);
            }
            return;
        }
        if(event.type.equals(RenderGameOverlayEvent.ElementType.FOOD)&& event.isCancelable())
        {
            if(mc.thePlayer.worldObj.provider.dimensionId == 2)
            {
                event.setCanceled(true);
            }
            return;
        }
            String version = Reference.NAME + "-" + Reference.VERSION;

            PlayerSAO data = PlayerSAO.get(this.mc.thePlayer);
            int colAmount = data.getColAmount();

        ScaledResolution res = new ScaledResolution(this.mc,
                this.mc.displayWidth, this.mc.displayHeight);
        int width = res.getScaledWidth();

        int height = res.getScaledHeight();
        mc.entityRenderer.setupOverlayRendering();

            double xColPos = width - 30;
            double yColPos = height - 30;
            int colColor = 0x008000;

            double xPos = width/2 - 70;
            double yPos = 5;
            int color = 0xFFFFFF;
            drawText(version, xPos, yPos, color);
            drawCol(colAmount, xColPos, yColPos, colColor);
    }

    public void drawText(String string, double xPos, double yPos, int color)
    {
        GL11.glPushMatrix();

        fontRenderer.drawStringWithShadow(string, (int) xPos, (int) yPos, color);
        GL11.glPopMatrix();
    }

    public void drawCol(int amount, double xPos, double yPos, int color)
    {
        GL11.glPushMatrix();
        String name = String.valueOf(amount);
        fontRenderer.drawStringWithShadow(name, (int)xPos, (int)yPos, color);
        GL11.glPopMatrix();
    }

}
