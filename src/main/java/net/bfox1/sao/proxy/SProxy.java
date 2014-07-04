package net.bfox1.sao.proxy;

import java.io.File;

public interface SProxy {
	
	public abstract void initClientConfig(File configFile);

	public abstract void registerTileEntities();
	
	public abstract void initRenderingAndTextures();
	
	public abstract void registerEventHandlers();
	
	public abstract void registerKeybindings();
	
	public abstract void registerEntityLiving();
	
	public abstract void registerGlobalEntity();
}
