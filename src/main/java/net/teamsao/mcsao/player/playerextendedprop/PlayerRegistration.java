package net.teamsao.mcsao.player.playerextendedprop;

import net.minecraft.entity.player.EntityPlayer;
import net.teamsao.mcsao.player.PlayerSAO;

/**
 * Created by bfox1 on 8/30/2014.
 */
public class PlayerRegistration {



    public static final void registerPlayerSAO(EntityPlayer player)
    {
        player.registerExtendedProperties(PlayerSAO.EXT_PROP_NAME, new PlayerSAO(player));
    }



}
