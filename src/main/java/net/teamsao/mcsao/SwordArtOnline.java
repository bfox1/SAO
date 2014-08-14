package net.teamsao.mcsao;

import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.teamsao.mcsao.entity.EntitySAO;
import net.teamsao.mcsao.gui.GuiSaoInGameMenu;
import net.teamsao.mcsao.init.SAOBlocks;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.gui.GuiHandler;
import net.teamsao.mcsao.help.Messages;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.lib.Recipe;
import net.teamsao.mcsao.material.SToolMaterial;
import net.teamsao.mcsao.proxy.SProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.teamsao.mcsao.util.LogHelper;

/* Created on 7-1-2014
 * This is the main file for the entire mod. All Init MUST go here.
 * If you need help, Ask bfox1 for information or refer to the Other classes for examples.
 */

/**
 * @author bfox1
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, certificateFingerprint = "Test", version = Reference.VERSION)
public class SwordArtOnline {

	@Instance(Reference.MODID)
	public static SwordArtOnline instance;

    public static int modGuiIndex = 0;

    public static final int GUI_ITEM_INV = modGuiIndex++;
	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.SERVERPROXY)
	public static SProxy proxy;


	@EventHandler
	public void invalidFingerprint(FMLFingerprintViolationEvent event)
	{
		if (Reference.FINGERPRINT.equals("Test")) {
			LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
		} else {
			LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
		}
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
        LogHelper.info("Starting PreInitEvent");
		EntitySAO.registerIds();
		SToolMaterial.init();
		SAOItems.registerInit();
		SAOBlocks.registerInit();
        proxy.registerDimension();
		proxy.initRenderingAndTextures();
        LogHelper.info("Finished PreInitEvent");


	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
        LogHelper.info("Starting InitEvent");
        proxy.registerGlobalEntity();
		proxy.registerTileEntities();
		Recipe.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        LogHelper.info("Finished InitEvent");
	}


}
