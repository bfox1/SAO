package net.teamsao.mcsao.handler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.teamsao.mcsao.helper.ColorHelper;
import net.teamsao.mcsao.helper.LogHelper;
import net.teamsao.mcsao.player.PlayerSAO;
import net.teamsao.mcsao.player.SpecialPlayers;
import net.teamsao.mcsao.player.entityextendedprop.EntityCol;
import net.teamsao.mcsao.player.entityextendedprop.EntityRegistration;
import net.teamsao.mcsao.player.playerextendedprop.PlayerRegistration;
import net.teamsao.mcsao.proxy.CommonProxy;

import java.util.List;

/**
 * Created by bfox1 on 8/27/2014.
 */
public class SaoEventHandler {

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer && PlayerSAO.get((EntityPlayer)event.entity) == null)
        {

            PlayerRegistration.registerPlayerSAO((EntityPlayer) event.entity);
        }
        if(event.entity instanceof EntityLivingBase)
        {

            EntityRegistration.registerEntityCol((EntityLivingBase) event.entity);
        }
    }

    @SubscribeEvent()
    public void onLivingDeathEvent(LivingDeathEvent event)
    {

        if(!event.entity.worldObj.isRemote && (event.entityLiving instanceof EntityMob || event.entityLiving instanceof EntityAnimal
                                           && event.source.getEntity() instanceof EntityPlayer)
           && event.source.getEntity().dimension == 2)
        {
                int value;
                EntityPlayer player = (EntityPlayer) event.source.getEntity();
                PlayerSAO.loadProxyData(player);
                PlayerSAO playerdata = PlayerSAO.get(player);
                NBTTagCompound compound = new NBTTagCompound();
                EntityCol props = EntityCol.get((EntityLivingBase)event.entity);
                props.loadNBTData(compound);

                value = event.entity.worldObj.rand.nextInt(3);

                if (event.entityLiving instanceof EntityMob) {
                    value = event.entity.worldObj.rand.nextInt(10);
                    System.out.println(value);
                }
                if (event.entityLiving instanceof EntityMooshroom) {
                    value = event.entity.worldObj.rand.nextInt(20);
                }
            LogHelper.info(" was given " + value + " Col for killing a " + ((EntityMob) event.entityLiving).getCustomNameTag());
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(ColorHelper.GRAY + "[Debug] " + ColorHelper.GREEN + "You have been awarded " + ColorHelper.YELLOW +
                                                                                    value + ColorHelper.GREEN + " Col for slaying that " + ColorHelper.DARK_GREEN + ((EntityMob)
                                                                                    event.entityLiving).getCustomNameTag() + ColorHelper.GREEN + "!"));
                props.addCol(value);
            System.out.println(value);
                int amt = props.getCol();
                playerdata.addCol(amt);
                LogHelper.debug("[LivingDeathEvent] About to save ProxyData...");
            PlayerSAO.saveProxyData(player);

        }
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            PlayerSAO.saveProxyData((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)event.entity;
            NBTTagCompound playerSaoData = CommonProxy.checkEntityData(PlayerSAO.getSavedKey(player));

            if(playerSaoData != null)
            {
                PlayerSAO.loadProxyData(player);
            }

        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
        public void onRenderExperienceBar(RenderGameOverlayEvent event)
        {

        }

    @SubscribeEvent
    public void onServerChatReceivedEvent(ServerChatEvent event)
    {
        if(event.player != null) {
            EntityPlayer ePlayer = event.player;
                event.setCanceled(true);
                List players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
            for (Object player : players) {
                EntityPlayer target = (EntityPlayer) player;
                for (String beta : SpecialPlayers.betaPlayers) {
                    if (target.getGameProfile().getName().equals(beta)) {
                        String chat = ColorHelper.DARK_BLUE + "[" + ColorHelper.GREEN + "Beta"
                                + ColorHelper.DARK_BLUE + "] " + ColorHelper.WHITE + "<" + ePlayer.getDisplayName() + "> "
                                + ColorHelper.WHITE + event.message;
                        return;
                    }
                }
                for (String alpha : SpecialPlayers.alphaPlayers) {
                    if (target.getGameProfile().getName().equals(alpha)) {
                        String chat = ColorHelper.DARK_RED + "[" + ColorHelper.YELLOW + "Alpha"
                                + ColorHelper.DARK_RED + "] " + ColorHelper.WHITE + "<" + ePlayer.getDisplayName() + "> "
                                + ColorHelper.WHITE + event.message;
                        target.addChatMessage(new ChatComponentTranslation(chat));
                        return;
                    }
                    /* if (target.getGameProfile().getName() != alphaList[f] && f == alphaList.length) {
                        String chattxt = "<" + player.getDisplayName() + ">" + " §f" + event.message;
                        target.addChatMessage(new ChatComponentTranslation(chattxt));
                        // Not necessary, since Minecraft handles default chat names on its own.
                    } */
                }

            }
        }
    }


}
