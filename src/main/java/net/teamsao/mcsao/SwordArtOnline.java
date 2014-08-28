package net.teamsao.mcsao;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.*; // Init events and fingerprint event
import cpw.mods.fml.common.network.NetworkRegistry;

import net.minecraft.client.Minecraft;

import net.minecraftforge.common.MinecraftForge;

import net.teamsao.mcsao.entity.EntitySAO;
import net.teamsao.mcsao.gui.GuiHandler;
import net.teamsao.mcsao.help.Messages;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.init.*; // SAO initialization
import net.teamsao.mcsao.lib.Recipe;
import net.teamsao.mcsao.material.SToolMaterial;
import net.teamsao.mcsao.overlay.OverlayHealth;
import net.teamsao.mcsao.proxy.SProxy;
import net.teamsao.mcsao.util.LogHelper;

/**
 * Created on 7-1-2014
 * This is the main file for the entire mod. All Init MUST go here.
 * If you need help, Ask bfox1 for information or refer to the Other classes for examples.
 * @author bfox1
 */
@Mod(modid = Reference.MODID, name = Reference.NAME, certificateFingerprint = "Test", version = Reference.VERSION)
public class SwordArtOnline {

    @Instance(Reference.MODID)
    public static SwordArtOnline instance;

    @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.SERVERPROXY)
    public static SProxy proxy;

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

    @EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
        LogHelper.info("PreInitEvent - 0% complete - Registering entities...");
        EntitySAO.registerIds();
        LogHelper.info("PreInitEvent - 16% complete - Registering materials...");
        SToolMaterial.init();
        LogHelper.info("PreInitEvent - 33% complete - Registering items...");
        SAOItems.registerInit();
        LogHelper.info("PreInitEvent - 50% complete - Registering blocks...");
        SAOBlocks.registerInit();
        LogHelper.info("PreInitEvent - 67% complete - Registering dimensions...");
        proxy.registerDimension();
        LogHelper.info("PreInitEvent - 84% complete - Initializing textures...");
        proxy.initRenderingAndTextures();
        LogHelper.info("PreInitEvent - 100% complete - Starting initialization...");
    }

    @EventHandler
    public void init(FMLInitializationEvent evt) {
        LogHelper.info("InitEvent - 0% complete - Registering global entities...");
        proxy.registerGlobalEntity();
        LogHelper.info("InitEvent - 20% complete - Registering tile entities...");
        proxy.registerTileEntities();
        LogHelper.info("InitEvent - 40% complete - Registering crafting recipes...");
        Recipe.init();
        LogHelper.info("InitEvent - 60% complete - Registering GUIs...");
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        LogHelper.info("InitEvent - 60% complete - Registering keybinds...");
        SAOBinds.registerInit();
        LogHelper.info("InitEvent - 100% complete - Starting post-initialization...");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent evt) {
        LogHelper.info("PostInitEvent - 0% complete - Registering overlays...");
        MinecraftForge.EVENT_BUS.register(new OverlayHealth(Minecraft.getMinecraft())); // Register health overlay
        LogHelper.info("PostInitEvent - 100% complete - Starting Minecraft...");
    }

}
