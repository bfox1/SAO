package net.teamsao.mcsao.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.teamsao.mcsao.helper.Reference;

/**
 * 
 * @author SirPwn on Aug 14, 2014
 *
 */

public class SAOGuiButton extends GuiButton {
	
	public ResourceLocation texture;
	public ResourceLocation textureActive;
	
	public boolean mouseOver = false;
	
	/**
	 * 
	 * @param id
	 * @param x
	 * @param y
	 * @param size
	 * @param buttonFileName - This is specifically the file name, without the path, or the .png extension
	 * @param twoTypes - If you have another file for on mouse over, add _ACTIVE and _INACTIVE to the end of your .png files, see assets for details
	 */
	public SAOGuiButton(int id, int x, int y, int size, String buttonFileName, boolean twoTypes) {
		super(id, x, y, size, size, "");
		
		this.mouseOver = twoTypes;
		
		if(twoTypes) {
			texture = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/gui/button/" + buttonFileName + "_INACTIVE" + ".png");
			textureActive = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/gui/button/" + buttonFileName + "_ACTIVE" + ".png");
		} else {
			texture = new ResourceLocation(Reference.MODID.toLowerCase(), "textures/gui/button/" + buttonFileName + ".png");
		}
		
	}
	
	//Overrides the draw button and tessellates directly instead of using the evil method drawTexturedModalRect
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible)
        {
        	//I'm not quite sure what the obfuscated field is, but I am using it to check if the player is mousing over the button
        	this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.field_146123_n);
            
            if(textureActive != null && k == 2) {
            	mc.renderEngine.bindTexture(textureActive);
            } else {
            	mc.renderEngine.bindTexture(texture);
            }
            GL11.glEnable(GL11.GL_BLEND);
            Tessellator t = Tessellator.instance;
            t.startDrawingQuads();
            t.addVertexWithUV(this.xPosition, this.yPosition, this.zLevel, 0, 0);
            t.addVertexWithUV(this.xPosition, this.yPosition + this.height, this.zLevel, 0, 1);
            t.addVertexWithUV(this.xPosition + this.width, this.yPosition + this.height, this.zLevel, 1, 1);
            t.addVertexWithUV(this.xPosition + this.width, this.yPosition, this.zLevel, 1, 0);
            t.draw();
        }
    }
}
