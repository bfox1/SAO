package net.teamsao.mcsao;

import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.NetworkManager;
import net.minecraftforge.common.DimensionManager;
import net.teamsao.mcsao.block.ForgeStation;
import net.teamsao.mcsao.block.SBlock;
import net.teamsao.mcsao.dimension.SAOWorldProvider;
import net.teamsao.mcsao.dimension.SDimension;
import net.teamsao.mcsao.entity.SEntity;
import net.teamsao.mcsao.entity.SEntityPlayer;
import net.teamsao.mcsao.gui.GuiHandler;
import net.teamsao.mcsao.help.Messages;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.item.SItem;
import net.teamsao.mcsao.lib.Recipe;
import net.teamsao.mcsao.lib.SCreativeTab;
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
 * This is the main file for the entire mod. All Registrations MUST go here. 
 * If you need help, Ask bfox1 for information or refer to the Other classes for examples.
 */

/**
 * @author bfox1
 */
@Mod(modid = Reference.MODID, certificateFingerprint = "Test", version = Reference.VERSION)
public class SwordArtOnline {

	@Instance(Reference.MODID)
	public static SwordArtOnline instance;
	@SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.SERVERPROXY)
	public static SProxy proxy;
	public static int dimensionId = 2;

    SEntityPlayer player;

	@EventHandler
	public void invalidFingerprint(FMLFingerprintViolationEvent event)
	{
		if(Reference.FINGERPRINT.equals("Test"))
		{
			LogHelper.info(Messages.NO_FINGERPRINT_MESSAGE);
		}
		else

		{
			LogHelper.warn(Messages.INVALID_FINGERPRINT_MESSAGE);
		}
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		SEntity.registerIds();
		SToolMaterial.init();
		SItem.registerInit();
		SBlock.registerInit();
		SDimension.registerInit();
		proxy.registerEntityLiving();


	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerGlobalEntity();
		proxy.registerTileEntities();
		Recipe.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}


}
