package net.teamsao.mcsao.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.event.KeyBindings;
import net.teamsao.mcsao.help.Reference;

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
