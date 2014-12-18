package net.teamsao.mcsao.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.player.PlayerSAO;

/**
 * Created by bfox1 on 11/14/2014.
 */
public class GuiSkillMenu extends GuiScreen {

    private static Minecraft mc;
    private static ScaledResolution res;

    public static ResourceLocation textureButton = new ResourceLocation(Reference.MODID.toLowerCase(),
            "textures/gui/button/menu.png");

    public GuiSkillMenu(Minecraft mc)
    {
        super();

        // We need this to invoke the render engine.
        GuiSkillMenu.mc = mc;
    }

    @Override
    public void initGui()
    {
        this.buttonList.clear();

            byte b0 = -16;
            boolean flag = true;
             this.res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);


            int width = this.res.getScaledWidth();
            int height = this.res.getScaledHeight();
            mc.entityRenderer.setupOverlayRendering();
            //this.buttonList.add(new SAOGuiButton(1, width / 2 - 200, height / 4 + 120 + b0, 32, "menu", true));
            this.buttonSlide(1, width / 2 - 250, height / 4 + 89 + b0, 32, "menu", true, "return to game");//return to game


            if (!this.mc.isIntegratedServerRunning()) {
                ((SAOGuiButton) this.buttonList.get(0)).displayString = I18n.format("menu", new Object[0]);
            }
            this.buttonList.add(new SAOGuiButton(4, width / 2 - 250, height / 4 + 24 + b0, 32, "menu", true));//Weapon
            this.buttonList.add(new SAOGuiButton(12, width / 2 - 250, height / 4 + 56 + b0, 32, "menu", true));//Support

    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        switch(button.id)
        {

        }
    }

    public void buttonSlide(int id, int x, int y, int size, String buttonFileName, boolean twoTypes, String buttonName)
    {
        this.buttonList.add(new SAOGuiButton(id, x, y, size, buttonFileName, twoTypes));
    }



    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        super.updateScreen();
    }

    /**
     * Draws the screen and all the components in it.
     */

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {

            this.drawDefaultBackground();
            this.drawCenteredString(this.fontRendererObj, "Skill Menu", this.width / 2, 40, 16777215);
           // this.drawString(this.fontRendererObj, "ReturnToMenu", ((this.width / 2) - 200), height / 4 + 120, 0xFFFFFF);
            super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

    }
}
