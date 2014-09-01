package net.teamsao.mcsao.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.network.SyncPlayerPropPacket;
import net.teamsao.mcsao.proxy.CommonProxy;

/**
 * Created by bfox1 on 8/11/2014.
 */
public class PlayerSAO implements IExtendedEntityProperties {

    public static final String EXT_PROP_NAME = "overCoords";

    public static final String AINCRAD_NAME = "aincradCoords";

    private int AincradCoordsX,AincradCoordsY ,AincradCoordsZ;

    private int overWorldX, overWorldY, overWorldZ;



    private int world;

    private final EntityPlayer player;

    public PlayerSAO(EntityPlayer player) {
        this.player = player;
        /*this.overWorldX = 10;
        this.overWorldY = 60;
        this.overWorldZ = 10;*/
    }

    //Used to register these extended properties for the player during EntityConstruction event
    /*public static final void register(EntityPlayer player)
    {
        player.registerExtendedProperties(PlayerSAO.EXT_PROP_NAME, new PlayerSAO(player));
    }*/
    //Returns ExtendedPlayer properties for player
    public static final PlayerSAO get(EntityPlayer player)
    {
        return (PlayerSAO) player.getExtendedProperties(EXT_PROP_NAME);
    }
    public static String getSavedKey(EntityPlayer player)
    {
        String key = player.getGameProfile().getName() + "-" + player.dimension + ":" + EXT_PROP_NAME;
        System.out.println(key);
        return key;
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
        properties.setInteger("bedCoordX", this.overWorldX);
        properties.setInteger("bedCoordY", this.overWorldY);
        properties.setInteger("bedCoordZ", this.overWorldZ);
        compound.setTag(EXT_PROP_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
        this.overWorldX = properties.getInteger("bedCoordX");
        this.overWorldY = properties.getInteger("bedCoordY");
        this.overWorldZ = properties.getInteger("bedCoordZ");
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

    public void setXYZCoord(int x, int y, int z)
    {
        this.overWorldX = x;
        this.overWorldY = y;
        this.overWorldZ = z;
    }



}
