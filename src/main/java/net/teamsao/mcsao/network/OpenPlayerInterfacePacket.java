package net.teamsao.mcsao.network;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.helper.Reference;

public class OpenPlayerInterfacePacket extends SaoAbstractPacket {
	
	private byte id;
	
	public OpenPlayerInterfacePacket() {
		
	}
	
	public OpenPlayerInterfacePacket(byte id) {
		this.id = id;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeByte(id);	
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.id = buffer.readByte();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		FMLNetworkHandler.openGui(player, SwordArtOnline.instance, Reference.MOD_GUI_INDEX, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
	}

}
