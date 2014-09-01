package net.teamsao.mcsao.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import net.teamsao.mcsao.client.gui.GuiCol;

import java.io.File;

/*
    Do NOT change anything in here unless you know what your DOING!
 */

public interface SProxy {
	
	public abstract void initClientConfig(File configFile);

	public abstract void registerTileEntities();
	
	public abstract void initRenderingAndTextures();
	
	public abstract void registerEventHandlers();
	
	public abstract void registerKeybindings();
	
	public abstract void registerEntityLiving();
	
	public abstract void registerGlobalEntity();

    public abstract void registerDimension();

    public abstract void addChestLoot();

    public abstract void registerRenderers();



}
