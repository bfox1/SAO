package net.teamsao.mcsao.client.settings;


import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;

import net.teamsao.mcsao.helper.Reference;
import org.lwjgl.input.Keyboard;

/**
 * Created by bfox1 on 8/27/2014.
 */
public class KeyBindings {

        /*
            Define the guiSAO binding, with (unlocalized) name "key.ping" and
            the category with (unlocalized) name "key.categories.mod" and
            key code 24 ("O", LWJGL constant: Keyboard.KEY_O)
         */
        public static KeyBinding guiSkill = new KeyBinding("key.guiskill", Keyboard.KEY_V, "key.categories.sao");
}
