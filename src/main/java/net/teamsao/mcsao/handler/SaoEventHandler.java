package net.teamsao.mcsao.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.teamsao.mcsao.player.PlayerSAO;
import net.teamsao.mcsao.proxy.CommonProxy;

import javax.swing.text.html.parser.Entity;

/**
 * Created by bfox1 on 8/27/2014.
 */
public class SaoEventHandler {

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer && PlayerSAO.get((EntityPlayer)event.entity) == null)
        {
            PlayerSAO.register((EntityPlayer)event.entity);
        }
    }

    @SubscribeEvent
    public void onLivingDeadEvent(LivingDeathEvent event)
    {
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
            NBTTagCompound playerdata = CommonProxy.getEntityData(((EntityPlayer)event.entity).getGameProfile().getName());

            if(playerdata != null)
            {
                ((PlayerSAO)(event.entity.getExtendedProperties(PlayerSAO.EXT_PROP_NAME))).loadNBTData(playerdata);
            }
        }
    }
}
