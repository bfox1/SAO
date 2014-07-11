package net.teamsao.mcsao;

import net.minecraftforge.common.DimensionManager;
import net.teamsao.mcsao.block.SBlock;
import net.teamsao.mcsao.dimension.SAOWorldProvider;
import net.teamsao.mcsao.dimension.SDimension;
import net.teamsao.mcsao.entity.SEntity;
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

/* Created on 7-1-2014
 * This is the main file for the entire mod. All Registrations MUST go here. 
 * If you need help, Ask bfox1 for information or refer to the Other classes for examples.
 */

/**
 * @author bfox1
 */
@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class SwordArtOnline
{
	
    @Instance(Reference.MODID)
    public static SwordArtOnline instance;
	 @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.SERVERPROXY)
	public static SProxy proxy;
	public static int dimensionId = 14;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        SEntity.registerIds();
    	SCreativeTab.registerCreativeTab();
    	SToolMaterial.init();
    	SItem.registerInit();
    	SBlock.registerInit();
    	proxy.registerEntityLiving();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.registerGlobalEntity();
    	Recipe.init();
    	SDimension.registerInit();
    	//proxy.registerEntityLiving();
    	//proxy.registerGlobalEntity();
    }
    

}
