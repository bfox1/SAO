package net.teamsao.mcsao.client.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.teamsao.mcsao.client.settings.KeyBindings;

import java.io.PrintStream;

/**
 * Created by bfox1 on 8/27/2014.
 */
public class SaoKeyInputHandler {

   EntityPlayer player = Minecraft.getMinecraft().thePlayer;
    World world = FMLClientHandler.instance().getClient().theWorld;
    PrintStream test = System.out;

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event){


        if(KeyBindings.guiSAO.isPressed())
        {
//                player.openGui(Reference.MODID, SwordArtOnline.GUI_ITEM_INV, world, (int) player.posX, (int) player.posY, (int) player.posZ);

            test.println("This works");
        }
    }
}
