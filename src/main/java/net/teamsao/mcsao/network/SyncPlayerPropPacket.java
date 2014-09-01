package net.teamsao.mcsao.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.teamsao.mcsao.player.PlayerSAO;
import net.teamsao.mcsao.player.playerextendedprop.PlayerCol;

/**
 * Created by bfox1 on 8/27/2014.
 */
public class SyncPlayerPropPacket extends SaoAbstractPacket {

    private NBTTagCompound data;

    public SyncPlayerPropPacket(){}

    public SyncPlayerPropPacket(EntityPlayer player)
    {
        data = new NBTTagCompound();

        PlayerSAO.get(player).saveNBTData(data);
        PlayerCol.get(player).saveNBTData(data);
    }
    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        ByteBufUtils.writeTag(buffer, data);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {

        data = ByteBufUtils.readTag(buffer);
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        PlayerSAO.get(player ).loadNBTData(data);
        PlayerCol.get(player).loadNBTData(data);

    }

    @Override
    public void handleServerSide(EntityPlayer player) {

    }
}
