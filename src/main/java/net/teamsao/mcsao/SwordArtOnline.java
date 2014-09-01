package net.teamsao.mcsao;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;


import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.teamsao.mcsao.client.gui.GuiCol;
import net.teamsao.mcsao.entity.EntitySAO;
import net.teamsao.mcsao.event.commands.TpDimension;
import net.teamsao.mcsao.handler.ConfigurationHandler;
import net.teamsao.mcsao.helper.Messages;
import net.teamsao.mcsao.init.SAOBlocks;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.gui.GuiHandler;
import net.teamsao.mcsao.helper.LogHelper;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.recipes.ItemRecipes;
import net.teamsao.mcsao.material.SToolMaterial;
import net.teamsao.mcsao.network.SaoPacketPipeline;
import net.teamsao.mcsao.proxy.SProxy;


/**
 * <p>This is the main file for the entire mod. All Init MUST go here.</p>
 * <p>If you need help, ask bfox1 for information or refer to the Other classes for examples.</p>
 *
 * @author bfox1
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, certificateFingerprint = "Test", version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY)
public class SwordArtOnline {

    @Instance(Reference.MODID)
    public static SwordArtOnline instance;

    public static final SaoPacketPipeline packetPipeline = new SaoPacketPipeline();
    public static int modGuiIndex = 0;
    public static final int GUI_ITEM_INV = modGuiIndex++;

    @EventHandler
    public void invalidFingerprint(FMLFingerprintViolationEvent event) {
        if (Reference.FINGERPRINT.equals("Test")) {
            LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
        } else {
            LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
        }
    }

	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.SERVERPROXY)
	public static SProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
        // LogHelper.info("PreInitEvent - 0% complete - Creating configuration...");
        LogHelper.info("Starting PreInitEvent");
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		EntitySAO.registerIds();
		SToolMaterial.init();
		SAOItems.registerInit();
		SAOBlocks.registerInit();
        proxy.registerDimension();
        // LogHelper.info("PreInitEvent - 84% complete - Initializing textures...");
        proxy.initRenderingAndTextures();
        // LogHelper.info("PreInitEvent - 100% complete - Starting initialization...");
    }

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

        packetPipeline.initialise();
        // LogHelper.info("Starting InitEvent");
        proxy.addChestLoot();
        proxy.registerEventHandlers();
        proxy.registerGlobalEntity();
		proxy.registerTileEntities();
        proxy.registerKeybindings();
        ItemRecipes.removeBlockRecipes();
		ItemRecipes.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        // LogHelper.info("Finished InitEvent");
	}

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.registerRenderers();
        packetPipeline.postInitialise();
    }



    @EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new TpDimension());
    }

}
