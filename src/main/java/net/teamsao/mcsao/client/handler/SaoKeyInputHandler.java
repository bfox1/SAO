package net.teamsao.mcsao.client.handler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.client.settings.KeyBindings;
import net.teamsao.mcsao.network.SaoAbstractPacket;
import net.teamsao.mcsao.world.DimensionId;
import net.teamsao.mcsao.world.SAOTeleporter;

import java.io.PrintStream;

/**
 * Created by bfox1 on 8/27/2014.
 */
public class SaoKeyInputHandler {


    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event){


        if(KeyBindings.guiSkill.isPressed())
        {
            if(FMLClientHandler.instance().getClientPlayerEntity() != null)
            {
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

                if(entityPlayer.dimension == DimensionId.SAO_DIMENSION_ID)
                {
                    if(entityPlayer.worldObj.isRemote)
                    {
                        entityPlayer.openGui(SwordArtOnline.instance, SwordArtOnline.GUI_SKILL, entityPlayer.worldObj, (int)entityPlayer.posX, (int)entityPlayer.posY, (int)entityPlayer.posZ);
                    }
                }
            }
        }
    }
}
