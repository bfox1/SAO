package net.teamsao.mcsao.proxy;

import java.io.File;

import cpw.mods.fml.common.FMLCommonHandler;
import net.teamsao.mcsao.entity.*;
import net.teamsao.mcsao.client.renderers.SafeAreaBlockRenderer;
import net.teamsao.mcsao.client.settings.KeyBindings;
import net.teamsao.mcsao.client.handler.SaoKeyInputHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.teamsao.mcsao.model.*;

public class ClientProxy extends CommonProxy
{
	public static int safeAreaBlockRenderType;
	public static int renderPass;
	
	@Override
	public void initClientConfig(File configFile)
	{
		
	}

	@Override
	public void initRenderingAndTextures()
	{
        RenderingRegistry.registerEntityRenderingHandler(EntityKoboldTest.class, new RenderEntityKoboldTest(new Illfang_the_Kobold_Lord(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBoar.class, new RenderBoar(new boar(), 0.5F));
        safeAreaBlockRenderType = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new SafeAreaBlockRenderer());
	}

	@Override
	public void registerKeybindings()
	{
        KeyBindings.init();
        FMLCommonHandler.instance().bus().register(new KeyBindings());
        FMLCommonHandler.instance().bus().register(new SaoKeyInputHandler());
	}

    @Override
    public void registerEntityLiving()
    {
    	
    }
}
