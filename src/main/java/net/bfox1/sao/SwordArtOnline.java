package net.bfox1.sao;

import net.bfox1.sao.Item.SItem;
import net.bfox1.sao.help.Reference;
import net.bfox1.sao.lib.SCreativeTab;
import net.bfox1.sao.material.SToolMaterial;
import net.bfox1.sao.proxy.SProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @author bfox1
 * created on 7-1-2014
 * This is the Main Mod File for the entire mod. All Registrations MUST go here. 
 * If you need help, Ask bfox1 for information or refer to the Other classes for examples.
 */
@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class SwordArtOnline
{
	 @SidedProxy(clientSide = Reference.CLIENTPROXY, serverSide = Reference.SERVERPROXY)
	public static SProxy proxy;

    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	SCreativeTab.registerCreativeTab();
    	SToolMaterial.init();
    	SItem.init();
    	SItem.registerInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.registerGlobalEntity();
    	proxy.registerEntityLiving();
    	//proxy.registerGlobalEntity();
    }
    

}
