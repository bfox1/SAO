package net.teamsao.mcsao.handler;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.teamsao.mcsao.block.BlockSAO;
import net.teamsao.mcsao.help.Reference;


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
        BlockSAO.isBreakable = config.getBoolean("configValue", Configuration.CATEGORY_GENERAL, true, "Is Block Unbreakable.");

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
