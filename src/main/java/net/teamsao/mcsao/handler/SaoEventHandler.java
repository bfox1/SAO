package net.teamsao.mcsao.handler;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import ibxm.Player;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.teamsao.mcsao.helper.ColorHelper;
import net.teamsao.mcsao.player.PlayerSAO;
import net.teamsao.mcsao.player.PreReleasePlayers;
import net.teamsao.mcsao.player.entityextendedprop.EntityCol;
import net.teamsao.mcsao.player.entityextendedprop.EntityRegistration;
import net.teamsao.mcsao.player.playerextendedprop.PlayerCol;
import net.teamsao.mcsao.player.playerextendedprop.PlayerRegistration;
import net.teamsao.mcsao.proxy.CommonProxy;
import org.lwjgl.Sys;

import javax.swing.text.html.parser.Entity;
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

            PlayerRegistration.registerPlayerSAO((EntityPlayer)event.entity);
        }
        if(event.entity instanceof EntityPlayer && PlayerCol.get((EntityPlayer)event.entity)==null)
        {

            PlayerRegistration.registerPlayerCol((EntityPlayer)event.entity);
        }
        if(event.entity instanceof EntityLivingBase)
        {

            EntityRegistration.registerEntityCol((EntityLivingBase) event.entity);
        }
    }

    @SubscribeEvent()
    public void onLivingDeathEvent(LivingDeathEvent event)
    {

        if(!event.entity.worldObj.isRemote && event.entityLiving instanceof EntityMob
                || event.entityLiving instanceof EntityAnimal && event.source.getEntity() instanceof EntityPlayer
                && event.source.getEntity().dimension == 2)
        {
                int value;
                EntityPlayer player = (EntityPlayer) event.source.getEntity();
                PlayerCol.loadProxyData(player);
                PlayerCol playerdata = PlayerCol.get(player);
                NBTTagCompound compound = new NBTTagCompound();
                EntityCol props = EntityCol.get((EntityLivingBase)event.entity);
                props.loadNBTData(compound);

                value = event.entity.worldObj.rand.nextInt(3);
                if(event.entityLiving instanceof EntityMob)
                {
                    value = event.entity.worldObj.rand.nextInt(10);
                    System.out.println(value);
                }
                if(event.entityLiving instanceof EntityMooshroom)
                {
                    value = event.entity.worldObj.rand.nextInt(20);
                    System.out.println(value);
                }

                props.addCol(value);
            System.out.println(value);
                int amt = props.getCol();
                playerdata.addCol(amt);
                System.out.println("[LIVINGDEATH] about to save ProxyDATA");
                PlayerCol.saveProxyData(player);


        }
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            PlayerSAO.saveProxyData((EntityPlayer)event.entity);
            PlayerCol.saveProxyData((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event)
    {
        if(!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
        {
            NBTTagCompound playerdata = CommonProxy.getEntityData(PlayerCol.getSavedKey((EntityPlayer)event.entity));

            if(playerdata != null)
            {
                System.out.println("[ENTITYJOIN WORLD] about to load PROXY DATA");

                PlayerSAO.loadProxyData((EntityPlayer)event.entity);
                PlayerCol.loadProxyData((EntityPlayer)event.entity);
            }

        }
      /*  if(event.entity instanceof EntityLivingBase)
        {
            NBTTagCompound compound = new NBTTagCompound();
            EntityCol props = EntityCol.get((EntityLivingBase)event.entity);
            props.addCol(16);
            props.saveNBTData(compound);
        }*/
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
        public void onRenderExperienceBar(RenderGameOverlayEvent event)
        {

        }

    @SubscribeEvent
    public void onServerChatReceivedEvent(ServerChatEvent event)
    {
        if(event.player != null)
        {
            EntityPlayer player = (EntityPlayer)event.player;

            if(player != null)
            {
                event.setCanceled(true);

                List players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;

                for(int i = 0; i< + players.size(); i++)
                {
                    String[] playerList = PreReleasePlayers.players;

                    for(int f=0; f<playerList.length; f++) {
                        EntityPlayer target = (EntityPlayer) players.get(i);
                        if (target.getGameProfile().getName().equals(playerList[f]))
                        {
                            String chattxt = ColorHelper.DARK_RED + "[" + ColorHelper.YELLOW + "Alphy"
                                    + ColorHelper.DARK_RED +"] §" +  "<" + player.getDisplayName() + ">" + " §f"
                                    + event.message;
                            target.addChatMessage(new ChatComponentTranslation(chattxt));
                            break;
                        }
                        if(target.getGameProfile().getName() != playerList[f] && f == playerList.length)
                        {
                            String chattxt = "<" + player.getDisplayName() + ">" + " §f" + event.message;
                            target.addChatMessage(new ChatComponentTranslation(chattxt));
                        }
                    }
                }
            }
        }
    }


}
