package net.teamsao.mcsao.player.playerextendedprop;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.helper.ColorHelper;
import net.teamsao.mcsao.helper.LogHelper;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.network.SyncPlayerPropPacket;
import net.teamsao.mcsao.player.PlayerSAO;
import net.teamsao.mcsao.proxy.CommonProxy;

/**
 * Created by bfox1 on 8/30/2014.
 */
public class PlayerCol implements IExtendedEntityProperties {

    public static final String COL = "Col";

    private double col_thousand = 1000;

    private int col_million = 1000000;

    private double col_amount;

    private final EntityPlayer player;

    public PlayerCol(EntityPlayer player)
    {
        this.player = player;
    }



    public static final PlayerCol get(EntityPlayer player)
    {
        return (PlayerCol) player.getExtendedProperties(COL);
    }
    public static String getSavedKey(EntityPlayer player)
    {
        String key = player.getGameProfile().getName() + "-" + player.dimension + ":" + COL;
        System.out.println(key);
        return key;
    }
    public static void saveProxyData(EntityPlayer player){
        PlayerCol playerdata = PlayerCol.get(player);
        NBTTagCompound savedData = new NBTTagCompound();
        playerdata.saveNBTData(savedData);
        CommonProxy.storeEntityData(getSavedKey(player), savedData);
        System.out.println("Proxy Data Saved");
    }
    public static void loadProxyData(EntityPlayer player)
    {
        PlayerCol playerdata = PlayerCol.get(player);
        NBTTagCompound savedData = CommonProxy.getEntityData(getSavedKey(player));
        if(savedData != null)
        {
            playerdata.loadNBTData(savedData);
            System.out.println("Proxy Data Loaded");
        }
        //This needs cahning
        SwordArtOnline.packetPipeline.sendTo(new SyncPlayerPropPacket(player), (EntityPlayerMP) player);
        System.out.println("Proxy Data Loaded 2");
    }
    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = new NBTTagCompound();
        properties.setDouble("colAmount", this.col_amount);
        compound.setTag(COL, properties);
        System.out.println("saved NBT data");
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(COL);
        this.col_amount = properties.getDouble("colAmount");
        System.out.println("loaded NBT data");

    }

    @Override
    public void init(Entity entity, World world) {

    }

    public double getColAmount()
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

    public double getCol_thousand()
    {
        return this.col_thousand;
    }
    public int getCol_million()
    {
        return this.col_million;
    }

}
