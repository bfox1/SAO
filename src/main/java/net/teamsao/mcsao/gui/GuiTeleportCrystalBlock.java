package net.teamsao.mcsao.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.teamsao.mcsao.tileentity.TileEntityTeleportCrystalBlock;

public class GuiTeleportCrystalBlock extends GuiScreen {
	
	private int id = 2;
	
	private TileEntityTeleportCrystalBlock tileEntity;
	
	public GuiTeleportCrystalBlock(TileEntityTeleportCrystalBlock te) {
		this.tileEntity = te;
	}
	
	public void initGui() {
		buttonList.clear();
		buttonList.add(new SAOGuiButton(0, 10, 10, 48, "Cancel", true));
		buttonList.add(new SAOGuiButton(1, 10, 75, 48, "Enter", true));
	}
	
	public void updateScreen() {
		
	}
	
	public void actionPerformed(GuiButton button) {
		switch(button.id) {
		case 0: this.mc.thePlayer.closeScreen(); break;
		case 1: this.mc.thePlayer.closeScreen(); break;
		default: break;
		}
	}
	
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public void drawScreen(int i, int j, float f) {
		super.drawScreen(i, j, f);
	}	
}
