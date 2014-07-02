package net.bfox1.sao;

import net.bfox1.sao.Item.SItem;
import net.bfox1.sao.help.Reference;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class SwordArtOnline
{

    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	SItem.init();
    	SItem.registerInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}
