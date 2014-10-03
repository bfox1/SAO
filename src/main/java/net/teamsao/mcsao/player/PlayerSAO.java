package net.teamsao.mcsao.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import net.teamsao.mcsao.player.skill.SkillNBT;
import net.teamsao.mcsao.proxy.CommonProxy;
import net.teamsao.mcsao.world.SAOTeleporter;
import org.lwjgl.Sys;

import java.util.ArrayList;

/**
 * Created by bfox1 on 8/11/2014.
 */
public class PlayerSAO implements IExtendedEntityProperties {

    public static final String EXT_PROP_NAME = "PlayerSAOProperties";

    private ArrayList<SkillNBT> skillNBTData = getSkillData();

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


    private final EntityPlayer player;

    public PlayerSAO(EntityPlayer player) {
        this.player = player;
        this.playerLevel = 1;
        this.AincradCoordsX = 0;
        this.AincradCoordsY = 0;
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
        for(SkillNBT skill : skillNBTData)
        {
                SkillNBT skillNBT = skillNBTData.get(it);
                String skillName = skillNBT.getSkillName();
                int skillLevel = skillNBT.getSkillLevel();
                long skillExp = skillNBT.getSkillExp();
                if(skillLevel != 0)
                {
                   properties.setInteger(skillName, skillLevel);
                }
                if(skillExp != 0)
                {
                    properties.setLong(skillName + "EXP", skillExp);
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
        int it = 0;
        for(SkillNBT skill : skillNBTData)
        {
            SkillNBT skillNBT = skillNBTData.get(it);
            String skillName = skillNBT.getSkillName();
            int skillLevel = skillNBT.getSkillLevel();
            if(properties.hasKey(skillName))
            {
                int value = properties.getInteger(skillName);

                skillNBT.setLvl(value);
            }
            if(properties.hasKey(skillName + "EXP"))
            {
                long exp = properties.getLong(skillName + "EXP");

                skillNBT.setExp(exp);
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
     * @param name The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     * @param setLvl The level you want to set the skill at.
     */
    public void setSkillLvl(String name, int setLvl)
    {
        int it = 0;
        for(SkillNBT skill : skillNBTData)
        {
            if(skill.getSkillName().equals(name))
            {
                SkillNBT skillNBT = skillNBTData.get(it);
                skillNBT.setLvl(setLvl);
            }
            it++;
        }
               // this.skillList[lvl] = setLvl;
    }
    public void addSkillLvl(String name)
    {
        SkillNBT skillData = getSkillInfo(name);
        int level = skillData.getSkillLevel();
        level++;
        skillData.setLvl(level);
    }

    /**
     *
     * @param name The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     * @return returns the Level your Skill is at, if nothing, returns 0
     */
    public int getSkillLvl(String name)
    {
        SkillNBT skillData = getSkillInfo(name);
        return skillData.getSkillLevel();

    }

    public SkillNBT getSkillInfo(String name)
    {
        int it = 0;
        for(SkillNBT skill : skillNBTData)
        {
            if(skill.getSkillName().equals(name))
            {
                SkillNBT skillNBT = skillNBTData.get(it);
                return skillNBT;
            }
        }
        return null;
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
     * @param name The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     */
    public void skillLevelUp(String name)
    {
        addSkillLvl(name);
        SkillNBT skillData = getSkillInfo(name);
        int level = skillData.getSkillLevel();
        player.addChatMessage(new ChatComponentText(LogHelper.chatEvent() +
            "§6Congradulations! Your " + "§4"
                + skillData.getSkillName() + " §6is now Level §4" + level + "§6!"));
    }

    /**
     *  To test if the Skill IS Active or Inactive.
     * @param name The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     * @return true if not equal to 0.
     */
    public boolean isActive(String name)
    {
        SkillNBT skillData = getSkillInfo(name);
        if(skillData.getSkillLevel() != 0)
        {
            return true;
        }
        return false;
    }

    public long getSkillExp(String name)
    {
        SkillNBT skillData = getSkillInfo(name);
        return skillData.getSkillExp();
    }


    /**
     *  To Add EXP to the Players Skills
     * @param name The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     * @param modifierIndex The Index number for the method getModifier in SkillNBT class
     */
    @SideOnly(Side.CLIENT)
    public void addExp(String name, int modifierIndex, int level) {
        SkillNBT skillData = getSkillInfo(name);
        String skillName = skillData.getSkillName();
        int skillLevel = skillData.getSkillLevel();
        long skillExp = skillData.getSkillExp();
        long baseExp = skillData.getBaseExp(skillLevel);
        long amt = SkillNBT.generateExp(level, baseExp, modifierIndex);
        long nextExp = skillData.generateNextExp(skillLevel);
        System.out.println("[ADD EXP]" + skillExp);
        if (isActive(name)) {
            long currentExp = skillExp;
            currentExp = currentExp + amt;
            if (skillLevel != 0) {
                if (currentExp > nextExp) {
                    currentExp = currentExp - nextExp;
                    skillLevelUp(skillName);
                }
                skillData.setExp(currentExp);
                System.out.println("[ADD EXP]" + currentExp + ":" + nextExp + " : " + amt);
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



    private ArrayList<SkillNBT> getSkillData()
    {
        ArrayList<SkillNBT> skillNBT = new ArrayList<SkillNBT>();
        int index = 0;
        for(String name : SkillBase.skills)
        {
            System.out.println("[ARRAYLIST]" + name.toLowerCase());
            SkillNBT skill = new SkillNBT(index,name.toLowerCase(), 0, 0);
            skillNBT.add(skill);
            index++;
        }
        return skillNBT;
    }






}
