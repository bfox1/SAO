package net.teamsao.mcsao.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

/**
 * 
 * @author SirPwn on Aug 14, 2014
 *
 */

public class GuiSelectTown extends GuiScreen {
	
	protected GuiTeleportCrystal parentGui;
	
	protected GuiTownList list;
	
	public GuiSelectTown(GuiTeleportCrystal parent) {
		this.parentGui = parent;
		this.list = new GuiTownList(parent);
	}
	
	public void initGui() {
				
		this.buttonList.add(new GuiButton(0, 25, this.height - 38, 120, 20, "Done"));
	}
	
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) {
			switch (par1GuiButton.id) {
			case 0: this.mc.displayGuiScreen(this.parentGui);
			}
		}
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		this.list.drawScreen(par1, par2, par3);
		super.drawScreen(par1, par2, par3);
	}
	
}
