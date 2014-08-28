package net.teamsao.mcsao.init;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

/**
 * @author 5chris100
 */
public class SAOBinds {

    public static KeyBinding menu = new KeyBinding("General Menu", Keyboard.KEY_BACKSLASH, "key.categories.Sword_Art_Online");
    public static KeyBinding quests = new KeyBinding ("Quests Menu", Keyboard.KEY_RBRACKET, "key.categories.Sword_Art_Online");

    public static void registerInit() {
        ClientRegistry.registerKeyBinding(menu);
        ClientRegistry.registerKeyBinding(quests);
    }
}
