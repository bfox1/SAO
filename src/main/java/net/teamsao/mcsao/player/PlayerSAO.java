package net.teamsao.mcsao.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.helper.LogHelper;
import net.teamsao.mcsao.network.SyncPlayerSAOPropPacket;
import net.teamsao.mcsao.player.skill.SkillBase;
import net.teamsao.mcsao.proxy.CommonProxy;
import net.teamsao.mcsao.world.SAOTeleporter;
import org.lwjgl.Sys;

/**
 * Created by bfox1 on 8/11/2014.
 */
public class PlayerSAO implements IExtendedEntityProperties {

    public static final String EXT_PROP_NAME = "PlayerSAOProperties";

    //Saved Coordinates
    private int AincradCoordsX,AincradCoordsY ,AincradCoordsZ;
    private int overWorldX, overWorldY, overWorldZ;
    //Currency
    private int col_amount;
    private int col_thousand = 1000;
    //PlayerLevel
    private int playerLevel;
    //PlayerEXP
    private long playerLevelExp;
    private final int BASE_EXP_CAP = 20;
    private long skillCap;
    //Skills the Player has
    private int combat = 0;
    private int blacksmithing = 0;
    private int fishing = 0;
    //Array for Skills
    private int[] skillList = {
            this.combat,
            this.blacksmithing,
            this.fishing
    };
    //SkillEXP
    private long expCombat;
    private long expBlackSmithing;
    //Array for Skill EXP
    private long[] skillExp = {
            this.expCombat,
            this.expBlackSmithing,

    };

    private final EntityPlayer player;

    public PlayerSAO(EntityPlayer player) {
        this.player = player;
        this.playerLevel = 1;
        this.AincradCoordsX = 0;
        this.AincradCoordsY = 40;
        this.AincradCoordsZ = 0;
        this.col_amount = 100;
    }

    //Returns ExtendedPlayer properties for player

    public static final PlayerSAO get(EntityPlayer player)
    {
        return (PlayerSAO) player.getExtendedProperties(EXT_PROP_NAME);
    }

    /** Special Key for Player*/

    public static String getSavedKey(EntityPlayer player)
    {
        String key = player.getGameProfile().getName() + ":" + EXT_PROP_NAME;
        System.out.println(key);
        return key;
    }
    /** A method to not only save playerDATA but to store it from pickup from LoadProxyData*/

    public static void saveProxyData(EntityPlayer player){
        PlayerSAO playerdata = PlayerSAO.get(player);
        NBTTagCompound savedData = new NBTTagCompound();
        playerdata.saveNBTData(savedData);
        CommonProxy.storeEntityData(getSavedKey(player), savedData);

    }

    /**A method to not Only Load PlayerDATA, but to Sync it with Server/Client */

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





/** This Saves all the data from PlayerSAO*/

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = new NBTTagCompound();
        int it = 0;
        for(int name : this.skillList)
        {
                if (isActive(it)) {
                    System.out.println("[SAVING SKILLS]" + SkillBase.skills[it]);
                    int lvl = this.skillList[it];
                    properties.setInteger(SkillBase.skills[it], lvl);
                }
            it++;
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
/** This Loads all the Saved data from PlayerSAO*/

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
        int it= 0;
        for(String name : SkillBase.skills)
        {
            if(properties.hasKey(name))
            {
                    this.skillList[it] = properties.getInteger(name);
            }
            it++;
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

    /**
     *
     * @return returns the Overworld Coord X
     */
    public int getXCoord()
    {

        return this.overWorldX;
    }

    /**
     *
     * @return returns the Overworld Coord Y
     */
    public int getYCoord()
    {
        return this.overWorldY;
    }

    /**
     *
     * @return returns the Overworld Coord Z
     */
    public int getZCoord()
    {
        return this.overWorldZ;
    }

    /**
     * Sets the Coordinates for the Player before Leaving Overworld.
     * @param x sets the X
     * @param y sets the Y
     * @param z sets the Z
     */
    public void setXYZCoord(int x, int y, int z)
    {
        this.overWorldX = x;
        this.overWorldY = y;
        this.overWorldZ = z;
    }

    /**
     *
     * @return returns the Col Amount
     */
    public int getColAmount()
    {
        return this.col_amount;
    }

    /**
     *
     * @param amount The Amount of Col Set.
     */
    public void setColAmount(int amount)
    {
        this.col_amount = amount;
    }

    /**
     *
     * @param amount
     */
    public void addCol(int amount)
    {
        this.col_amount = this.col_amount + amount;
    }

    /**
     *
     * @return Returns the Thousand Col.
     */
    public int getCol_thousand()
    {
        return this.col_thousand;
    }

    /**
     * Setting the Skill of the Player.
     * @param lvl The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     * @param setLvl The level you want to set the skill at.
     */
    public void setSkillLvl(int lvl, int setLvl)
    {
                this.skillList[lvl] = setLvl;
    }
    public void addSkillLvl(int lvl)
    {
        this.skillList[lvl] =this.skillList[lvl] + 1;
    }

    /**
     *
     * @param lvl The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     * @return returns the Level your Skill is at, if nothing, returns 0
     */
    public int getSkillLvl(int lvl)
    {
        if(this.skillList[lvl] != 0)
        {
            return this.skillList[lvl];
        }
        this.player.addChatMessage(new ChatComponentText(LogHelper.chatEvent() + "§4You do not have this Skill!"));
        return 0;
    }

/** returns the Aincrad Coord X*/
    public int getAincradCoordsX()
    {
        return this.AincradCoordsX;
    }
    /** returns the Aincrad Coord Y*/
    public int getAincradCoordsY()
    {
        return this.AincradCoordsY;
    }
    /** returns the Aincrad Coord Z*/
    public int getAincradCoordsZ()
    {
        return this.AincradCoordsZ;
    }

    /**
     * Sets the Last known Position in Aincrad.
     * @param X Sets the X
     * @param Y Sets the Y
     * @param Z Sets the Z
     */
    public void setAincradCoordsXYZ(int X, int Y, int Z)
    {
        this.AincradCoordsX = X;
        this.AincradCoordsY = Y;
        this.AincradCoordsZ = Z;
    }

    /**
     *  To Level up the Player.(Skill wise)
     * @param lvl The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     */
    public void skillLevelUp(int lvl)
    {
        addSkillLvl(lvl);
        int level = this.skillList[lvl];
        player.addChatMessage(new ChatComponentText(LogHelper.chatEvent() +
            "§6Congradulations! Your " + "§4"
                + SkillBase.skills[lvl] + " §6is now Level §4" + level + "§6!"));
    }

    /**
     *  To test if the Skill IS Active or Inactive.
     * @param lvl The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     * @return true if not equal to 0.
     */
    public boolean isActive(int lvl)
    {
        if(this.skillList[lvl] != 0)
        {
            return true;
        }
        return false;
    }

    public long getSkillExp(int lvl)
    {
        return this.skillExp[lvl];
    }


    /**
     *  To Add EXP to the Players Skills
     * @param lvl The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     * @param amt Amount of EXP being awarded
     */
    public void addExp(int lvl, long amt) {
        if (isActive(lvl)) {
            long level = this.skillExp[lvl];
            level = level + amt;
            long maxExp = SkillBase.maxSkillExp();
            if (this.skillList[lvl] != 0) {
                if (level > maxExp) {
                    level = maxExp - level;
                    skillLevelUp(lvl);
                }
                this.skillExp[lvl] = level;
            }
        }
    }

    public void addLevelEXP(long amt)
    {
        long nAmt = (long)(amt *(30f / 100f));
        this.playerLevelExp = nAmt;
    }

    public int getPlayerLevel()
    {
      return this.playerLevel;
    };

    public void setBaseExp(int lvl)
    {
        if(lvl != 0)
        {
            skillCap = lvl * BASE_EXP_CAP;
        }else{
            skillCap = lvl * skillCap;
        }
    }

}
