package net.teamsao.mcsao.handler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
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
import net.teamsao.mcsao.player.skill.SkillBase;
import net.teamsao.mcsao.player.skill.SkillNBT;
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

            PlayerRegistration.registerPlayerSAO((EntityPlayer)event.entity);
        }
        if(event.entity instanceof EntityLivingBase)
        {

            EntityRegistration.registerEntityCol((EntityLivingBase) event.entity);
        }
    }

    @SubscribeEvent()
    public void onLivingDeathEvent(LivingDeathEvent event)
    {

        if(!event.entity.worldObj.isRemote && event.entityLiving instanceof EntityMob || event.entityLiving instanceof EntityAnimal)
            if(event.source.getEntity() instanceof EntityPlayer && event.source.getEntity().dimension == 2) {
                {
                    int value;
                    int exp;
                    int mobLevel;
                    EntityPlayer player = (EntityPlayer) event.source.getEntity();
                    PlayerSAO.loadProxyData(player);
                    PlayerSAO playerdata = PlayerSAO.get(player);
                    NBTTagCompound compound = new NBTTagCompound();
                    EntityCol props = EntityCol.get((EntityLivingBase) event.entity);
                    props.loadNBTData(compound);
                    value = event.entity.worldObj.rand.nextInt(7);
                    exp = 0;
                    mobLevel = props.randomExpGenerator(1, 5);
                    if (event.entityLiving instanceof EntityMob) {
                        value = event.entity.worldObj.rand.nextInt(15);
                        exp = 1;
                        mobLevel = props.randomExpGenerator(1, 5);
                    }
                    if (event.entityLiving instanceof EntityMooshroom) {
                        mobLevel = props.randomExpGenerator(3, 7);
                        exp = 2;
                    }
                    props.addCol(value);
                    playerdata.addExp("combat",exp, mobLevel);
                    System.out.println(value);
                    int amt = props.getCol();
                    playerdata.addCol(amt);
                    LogHelper.debug("[LivingDeathEvent] About to save ProxyData...");
                    PlayerSAO.saveProxyData(player);

                }
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
        if(event.player != null)
        {
            EntityPlayer player = (EntityPlayer)event.player;

            if(player != null)
            {
                event.setCanceled(true);

                List players = MinecraftServer.getServer().getConfigurationManager().playerEntityList;

                for(int i = 0; i< + players.size(); i++)
                {
                    String[] playerList = SpecialPlayers.alphaPlayers;

                    for(int f=0; f<playerList.length; f++) {
                        EntityPlayer target = (EntityPlayer) players.get(i);
                        if (target.getGameProfile().getName().equals(playerList[f]))
                        {
                            String chattxt = ColorHelper.DARK_RED + "[" + ColorHelper.YELLOW + "beater"
                                    + ColorHelper.DARK_RED +"]" +  "§f<" + player.getDisplayName() + ">" + " §f"
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
