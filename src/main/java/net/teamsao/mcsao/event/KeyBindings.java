package net.teamsao.mcsao.event;


import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

import net.teamsao.mcsao.help.Reference;
import org.lwjgl.input.Keyboard;

/**
 * Created by bfox1 on 8/27/2014.
 */
public class KeyBindings {

    //Declare two KeyBindings
    public static KeyBinding guiSAO;

    public static void init(){
        /*
            Define the guiSAO binding, with (unlocalized) name "key.ping" and
            the category with (unlocalized) name "key.categories.mod" and
            key code 24 ("O", LWJGL constant: Keyboard.KEY_O)
         */
        guiSAO = new KeyBinding("key.guiSAO", Keyboard.KEY_SUBTRACT, "key.categories." + Reference.MODID);
        ClientRegistry.registerKeyBinding(guiSAO);
    }
}
