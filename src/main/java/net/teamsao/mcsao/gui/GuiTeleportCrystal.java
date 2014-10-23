package net.teamsao.mcsao.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.teamsao.mcsao.helper.Reference;

/**
 * 
 * @author SirPwn on Aug 14, 2014
 *
 */

public class GuiTeleportCrystal extends GuiScreen {
	
	public static final ResourceLocation texture = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/gui/TeleportCrystal.png");
	
	public final int xSize = 352;
	public final int ySize = 176;
	
	public String text;
	
	public GuiTeleportCrystal(EntityPlayer player) {
		super();
	}
	
	@Override
	public void drawScreen(int x, int y, float f) {
		//drawDefaultBackground();
		
		//GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(texture);
		
		int xPosition = (this.width - xSize) / 2;
		int yPosition = (this.height - ySize) / 2;
		
		GL11.glEnable(GL11.GL_BLEND);
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV(xPosition, yPosition, this.zLevel, 0, 0);
        t.addVertexWithUV(xPosition, yPosition + this.ySize, this.zLevel, 0, 1);
        t.addVertexWithUV(xPosition + this.xSize, yPosition + this.ySize, this.zLevel, 1, 1);
        t.addVertexWithUV(xPosition + this.xSize, yPosition, this.zLevel, 1, 0);
        t.draw();
		
		super.drawScreen(x, y, f);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	protected void keyTyped(char par1, int par2) {
		if(par2 == 1 || par2 == this.mc.gameSettings.keyBindInventory.getKeyCode()) {
			this.mc.thePlayer.closeScreen();
		}
	}
	
	public void actionPerformed(GuiButton button) {
		switch(button.id) {
		case 0: this.mc.displayGuiScreen(new GuiSelectTown(this)); break;
		case 1: this.mc.thePlayer.closeScreen(); break;
		default: break;
		}
	}
	
	public void initGui() {
		this.buttonList.clear();
		
		int posX = (this.width - xSize) / 2;
		int posY = (this.height - ySize) / 2;
		
		this.buttonList.add(new SAOGuiButton(0, this.width / 2 - 32 - 90, this.height / 2, 64, "Navigation", true));
		this.buttonList.add(new SAOGuiButton(1, this.width / 2 - 32 + 98, this.height / 2 + 8, 48, "Cancel", true));
	}
	
	public FontRenderer getFontRenderer() {
		return this.fontRendererObj;
	}
}