package net.teamsao.mcsao.player;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.packet.SyncPlayerPropPacket;
import net.teamsao.mcsao.proxy.CommonProxy;
import net.teamsao.mcsao.util.NBTHelper;

import java.util.UUID;

/**
 * Created by bfox1 on 8/11/2014.
 */
public class PlayerSAO implements IExtendedEntityProperties {

    public static final String EXT_PROP_NAME = "overCoords";

    private int AincradCoordsX;
    private int AincradCoordsY;
    private int AincradCoordsZ;

    private int overWorldX, overWorldY, overWorldZ;


    private int world;

    private final EntityPlayer player;

    public PlayerSAO(EntityPlayer player) {
        this.player = player;
        this.overWorldX = 0;
        this.overWorldY = 0;
        this.overWorldZ = 0;
    }

    //Used to register these extended properties for the player during EntityConstruction event
    public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(PlayerSAO.EXT_PROP_NAME, new PlayerSAO(player));
    }

    //Returns ExtendedPlayer properties for player
    public static final PlayerSAO get(EntityPlayer player)
    {
        return (PlayerSAO) player.getExtendedProperties(EXT_PROP_NAME);
    }

    public static String getSavedKey(EntityPlayer player)
    {
        return player.getGameProfile().getName() + ":" + EXT_PROP_NAME;
    }

    public static void saveProxyData(EntityPlayer player){
        PlayerSAO playerdata = PlayerSAO.get(player);
        NBTTagCompound savedData = new NBTTagCompound();

        playerdata.saveNBTData(savedData);
        CommonProxy.storeEntityData(getSavedKey(player), savedData);
    }

    public static void loadProxyData(EntityPlayer player)
    {
        PlayerSAO playerdata = PlayerSAO.get(player);
        NBTTagCompound savedData = CommonProxy.getEntityData(getSavedKey(player));

        if(savedData != null)
        {
            playerdata.loadNBTData(savedData);
        }

        SwordArtOnline.packetPipeline.sendTo(new SyncPlayerPropPacket(player), (EntityPlayerMP) player);
    }



    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = new NBTTagCompound();
        getOverPlayerCoords();
        properties.setInteger("bedCoordX", overWorldX);
        properties.setInteger("bedCoordY", overWorldY);
        properties.setInteger("bedCoordZ", overWorldZ);
        compound.setTag(EXT_PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
        this.overWorldX = properties.getInteger("bedCoordX");
        this.overWorldY = properties.getInteger("bedCoordY");
        this.overWorldZ = properties.getInteger("bedCoordZ");
        player.addChatMessage(new ChatComponentText("Now At:" + overWorldX + "" + overWorldY + "" + overWorldZ));

    }

    @Override
    public void init(Entity entity, World world) {

    }

    public int getXCoord()
    {
        return this.overWorldX;
    }

    public int getYCoord()
    {
        return this.overWorldY;
    }

    public int getZCoord()
    {
        return this.overWorldZ;
    }

    private  PlayerSAO getOverPlayerCoords(){

        if(player.dimension == 0)
        {

                this.overWorldY = player.getBedLocation(0).posX;
                this.overWorldY = player.getBedLocation(0).posY;
                this.overWorldZ = player.getBedLocation(0).posZ;

            this.world = 0;
        }
        return null;
    }

}
