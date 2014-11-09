package net.teamsao.mcsao.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.teamsao.mcsao.block.BlockSAO;
import net.teamsao.mcsao.helper.Reference;


import java.io.File;

/**
 * Created by bfox1 on 8/25/2014.
 */
public class ConfigurationHandler {

    public static Configuration config;


    public static void init(File configFile)
    {
        if(config == null)
        {
            config = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private static void loadConfiguration()
    {
        BlockSAO.isBreakable = config.getBoolean("IsBlockBreakable", Configuration.CATEGORY_GENERAL, false, "Determine if Aincrad Blocks are breakable");

        if (config.hasChanged())
        {
            config.save();
        }
    }


    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.modID.equalsIgnoreCase(Reference.MODID))
        {
            loadConfiguration();
        }
    }
}
