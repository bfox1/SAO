package net.teamsao.mcsao.client.gui;

import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.teamsao.mcsao.handler.ConfigurationHandler;
import net.teamsao.mcsao.helper.Reference;

/**
 * Created by bfox1 on 8/25/2014.
 */
public class SaoConfigGui extends GuiConfig {
    public SaoConfigGui(GuiScreen parentScreen) {
        super(parentScreen,
                new ConfigElement(ConfigurationHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(),
                Reference.MODID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
    }
}
