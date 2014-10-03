package net.teamsao.mcsao.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.teamsao.mcsao.helper.Reference;

/**
 * 
 * @author SirPwn on Aug 15, 2014
 *
 */
public class GuiTownList extends GuiSlot {
	
	protected Minecraft mc;
	
	protected int slotHeight;
	
	private String[] strings;
	
	private GuiTeleportCrystal gui;
	
	public GuiTownList(GuiTeleportCrystal gui) {
		super(gui.mc, gui.width, gui.height, 32, gui.height, 24);
		this.slotHeight = 24;
		this.mc = gui.mc;
		this.gui = gui;
		
		strings = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven"};
	}
	
	protected int getSize() {
		return this.strings.length;
	}
	
	protected void elementClicked(int index, boolean twice, int par3, int par4) {
		if(twice) {
			/*this.gui.setString(strings[index]);
			this.mc.displayGuiScreen(gui);*/
			
			double x, y, z;
			x = 0;
			y = 0;
			z = 0;
			
			NBTTagCompound tag = this.mc.thePlayer.getEntityData();
			
			NBTBase modeTag = tag.getTag("xPositions");
			if(modeTag != null) {
				int[] array = tag.getIntArray("xPositions");
				x = array[index];
			}
			
			NBTBase modeTag2 = tag.getTag("yPositions");
			if(modeTag2 != null) {
				int[] array = tag.getIntArray("yPositions");
				y = array[index];
			}
			
			NBTBase modeTag3 = tag.getTag("zPositions");
			if(modeTag3 != null) {
				int[] array = tag.getIntArray("zPositions");
				z = array[index];
			}
			
			this.mc.thePlayer.closeScreen();
			this.mc.thePlayer.inventory.getCurrentItem().stackSize = 0;
			this.mc.thePlayer.setPositionAndUpdate(x + 0.5, y, z + 0.5);
		}
	}
	
	protected boolean isSelected(int par1) {
		return false;
	}
	
	protected int getContentHeight() {
		return this.getSize() * 24;
	}
	
	protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator, int par6, int par7) {
		this.gui.getFontRenderer().setBidiFlag(true);
		
		NBTTagCompound tag = this.mc.thePlayer.getEntityData();
		
		NBTBase modeTag = tag.getTag("CanTeleportArray");
		if(modeTag != null) {
			int[] array = tag.getIntArray("CanTeleportArray");
			if(array[par1] == 1)
				this.gui.drawCenteredString(this.gui.getFontRenderer(), strings[par1], this.gui.width / 2, par3 + 1, 16777215);
		} else {
			int[] array = new int[Reference.NUMBER_OF_TOWNS];
			tag.setIntArray("CanTeleportArray", array);
		}
	}
	
	@Override
	protected void drawBackground() {
		this.gui.drawDefaultBackground();
	}
}
