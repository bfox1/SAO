package net.teamsao.mcsao.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.network.SyncPlayerSAOPropPacket;
import net.teamsao.mcsao.player.skill.SkillBase;
import net.teamsao.mcsao.proxy.CommonProxy;
import net.teamsao.mcsao.world.SAOTeleporter;

/**
 * Created by bfox1 on 8/11/2014.
 */
public class PlayerSAO implements IExtendedEntityProperties {

    public static final String EXT_PROP_NAME = "PlayerSAOProperties";

    private int AincradCoordsX,AincradCoordsY ,AincradCoordsZ;

    private int overWorldX, overWorldY, overWorldZ;

    private int col_amount;
    private int col_thousand = 1000;

    private int combat = 1;

    private int blacksmithing = 0;


    private int world;

    private final EntityPlayer player;

    public PlayerSAO(EntityPlayer player) {
        this.player = player;
        this.AincradCoordsX = 0;
        this.AincradCoordsY = 40;
        this.AincradCoordsZ = 0;
        this.col_amount = 100;
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
        String key = player.getGameProfile().getName() + ":" + EXT_PROP_NAME;
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
        System.out.println(playerdata + " " + savedData);
        if(savedData != null)
        {
            playerdata.loadNBTData(savedData);
        }
        SwordArtOnline.packetPipeline.sendTo(new SyncPlayerSAOPropPacket(player), (EntityPlayerMP) player);
    }






    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = new NBTTagCompound();

        for(String name : SkillBase.skills)
        {
            SkillBase base = new SkillBase();
            if(base.checkSkillData(name) != 0)
            {
                int lvl = base.getSkillData(name);
                properties.setInteger(name, lvl);
            }
            else {
                properties.setInteger(name, 0);
            }
        }
        properties.setInteger("bedCoordX", this.overWorldX);
        properties.setInteger("bedCoordY", this.overWorldY);
        properties.setInteger("bedCoordZ", this.overWorldZ);
        properties.setInteger("ainCoordX", this.AincradCoordsX);
        properties.setInteger("ainCoordY", this.AincradCoordsY);
        properties.setInteger("ainCoordZ", this.AincradCoordsZ);
        properties.setInteger("colAmount", this.col_amount);
        compound.setTag(EXT_PROP_NAME, properties);
        System.out.println("Saved NBT data - PlayerSAO" + properties);

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
        for(String name : SkillBase.skills)
        {
            SkillBase base = new SkillBase();
            if(base.checkSkillData(name) != 0)
            {
                int lvl = base.getSkillData(name);
                this.combat = properties.getInteger(name);
            }
        }
        this.overWorldX = properties.getInteger("bedCoordX");
        this.overWorldY = properties.getInteger("bedCoordY");
        this.overWorldZ = properties.getInteger("bedCoordZ");
        this.col_amount = properties.getInteger("colAmount");
        this.AincradCoordsX = properties.getInteger("ainCoordX");
        this.AincradCoordsY = properties.getInteger("ainCoordY");
        this.AincradCoordsZ = properties.getInteger("ainCoordZ");
        System.out.println("loaded NBT data - PLAYERSAO" + properties);
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
    public int getColAmount()
    {
        return this.col_amount;
    }

    public void setColAmount(int amount)
    {
        this.col_amount = amount;
    }

    public void addCol(int amount)
    {
        this.col_amount = this.col_amount + amount;
    }

    public int getCol_thousand()
    {
        return this.col_thousand;
    }

    public int getAincradCoordsX()
    {
        return this.AincradCoordsX;
    }

    public int getAincradCoordsY()
    {
        return this.AincradCoordsY;
    }
    public int getAincradCoordsZ()
    {
        return this.AincradCoordsZ;
    }

    public void setAincradCoordsXYZ(int X, int Y, int Z)
    {
        this.AincradCoordsX = X;
        this.AincradCoordsY = Y;
        this.AincradCoordsZ = Z;
    }







}
