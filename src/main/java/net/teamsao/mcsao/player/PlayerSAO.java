package net.teamsao.mcsao.player;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

/**
 * Created by bfox1 on 8/11/2014.
 */
public class PlayerSAO extends EntityPlayer {

    private int AincradCoordsX;
    private int AincradCoordsY;
    private int AincradCoordsZ;


    public PlayerSAO(World p_i45324_1_, GameProfile p_i45324_2_) {
        super(p_i45324_1_, p_i45324_2_);
    }

    public void SaveCoordsAincrad() {}

    public static void TSaveCoordsOverworld() {}

    @Override
    public void addChatMessage(IChatComponent p_145747_1_) {

    }

    @Override
    public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_) {
        return false;
    }

    @Override
    public ChunkCoordinates getPlayerCoordinates() {
        return null;
    }










}
