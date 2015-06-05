package net.teamsao.mcsao.player;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.helper.LogHelper;
import net.teamsao.mcsao.network.SyncPlayerSAOPropPacket;
import net.teamsao.mcsao.player.skill.SkillList;
import net.teamsao.mcsao.player.skill.SkillBase;
import net.teamsao.mcsao.player.skill.SkillType;
import net.teamsao.mcsao.proxy.CommonProxy;

import java.util.ArrayList;

/**
 * Created by bfox1 on 8/11/2014.
 */
public class PlayerSAO implements IExtendedEntityProperties {

    public static final String EXT_PROP_NAME = "PlayerSAOProperties";

    private ArrayList<SkillBase> skillNBTData = getSkillData();




    //Saved Coordinates

    private int AincradCoordsX,AincradCoordsY ,AincradCoordsZ;
    private int overWorldX, overWorldY, overWorldZ;

    //Currency

    private int col_amount;
    private int col_thousand = 1000;

    //PlayerLevel

    private int playerLevel;

    private boolean saveInvNBT;


    //PlayerEXP

    private long playerLevelExp;
    private final int BASE_EXP_CAP = 20;
    private long skillCap;


    private final EntityPlayer player;


    /**
     * Pass in the EntityPlayer Class to load PlayerEntityData
     * @param player the EntityPlayer
     */
    public PlayerSAO(EntityPlayer player) {
        this.player = player;
        this.playerLevel = 1;
        this.AincradCoordsX = 0;
        this.AincradCoordsY = 0;
        this.AincradCoordsZ = 0;
        this.col_amount = 100;
        this.saveInvNBT = false;
    }

    /**
     * To get the Info of particular Player
     * @param player EntityPlayer
     * @return the Properties details of the player
     */

    public static PlayerSAO get(EntityPlayer player)
    {
        return (PlayerSAO) player.getExtendedProperties(EXT_PROP_NAME);
    }

    /**
     * retrieves special key from the EntityPlayer.
     * @param player EntityPlayer
     * @return Special Key
     */

    public static String getSavedKey(EntityPlayer player)
    {
        String key = player.getGameProfile().getName() + ":" + EXT_PROP_NAME;
        System.out.println(key);
        return key;
    }

    /**
     * Save the Players data and to Store it for persistance.
     * @param player EntityPlayer
     */

    public static void saveProxyData(EntityPlayer player){
        PlayerSAO playerdata = PlayerSAO.get(player);
        NBTTagCompound savedData = new NBTTagCompound();
        playerdata.saveNBTData(savedData);
        CommonProxy.storeEntityData(getSavedKey(player), savedData);

    }

    /**
     * Loads data by retreiving special key then syncing with server/client
     * @param player
     */

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

        for(SkillBase skill : skillNBTData)
        {
                String skillName = skill.getSkillName();
                int skillLevel = skill.getSkillLevel();
                long skillExp = skill.getSkillExp();
                if(skillLevel != 0)
                {
                   properties.setInteger(skillName, skillLevel);
                }
                if(skillExp != 0)
                {
                    properties.setLong(skillName + "EXP", skillExp);
                }
        }
        /**if(saveInvNBT)
        {
            for (int i = 0; i < this.player.inventory.mainInventory.length; i++) {
                 items = new NBTTagList();
                if (this.player.inventory.mainInventory[i] != null) {
                    //InventoryNBTHelper invData = invNBTData.get(it);
                    //  Item item = invData.getItem();
                    //  int stackAmount = invData.getAmount();
                    //  int index = invData.getIndex();
                    //this.player.inventory.copyInventory(this.player.inventory);
                    properties.setByte("Slot", (byte) i);
                    this.player.inventory.mainInventory[i].writeToNBT(properties);
                    items.appendTag(properties);
                }
            }
        }
         **/
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
        for(SkillBase skill : skillNBTData)
        {

            String skillName = skill.getSkillName();
            if(properties.hasKey(skillName))
            {
                int value = properties.getInteger(skillName);

                skill.setLvl(value);
            }
            if(properties.hasKey(skillName + "EXP"))
            {
                long exp;
                exp = properties.getLong(skillName + "EXP");

                skill.setExp(exp);
            }
        }
        /**
        if(saveInvNBT)
        {

            for (int i = 0; i < tagList.tagCount(); i++)
            {
                NBTTagCompound nbttagcompound = tagList.getCompoundTagAt(i);

                int j = nbttagcompound.getByte("Slot") & 255;

                ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);

                if (itemstack != null)
                {
                    if (j >= 0 && j < inventoryPlayer.mainInventory.length)
                    {

                        inventoryPlayer.mainInventory[j] = itemstack;
                    }
                }
            }
        }
         **/
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
     * Returns the overworld coordinates of X Pos.
     * @return returns the Overworld Coord X
     */
    public int getXCoord()
    {

        return this.overWorldX;
    }

    /**
     * Returns the overworld coordinates of Y Pos.
     * @return returns the Overworld Coord Y
     */
    public int getYCoord()
    {
        return this.overWorldY;
    }

    /**
     * Returns the overworld coordinates of Z Pos.
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
     * @param name The name for the Skill ex.(Combat , BlackSmithing )
     * @param setLvl The level you want to set the skill at.
     */
    public void setSkillLvl(String name, int setLvl)
    {
        int it = 0;

        for(SkillBase skill : skillNBTData)
        {

            if(skill.getSkillName().equals(name))
            {

                SkillBase skillBase = skillNBTData.get(it);
                skillBase.setLvl(setLvl);
            }

            it++;
        }
    }

    /**
     * Adds the a level to the Skill
     * @param name The skill Name
     */
    private void addSkillLvl(String name)
    {
        SkillBase skillData = getSkillInfo(name);

        int level = skillData.getSkillLevel();

        level++;

        skillData.setLvl(level);
    }

    /**
     *
     * @param name The name  for the Skill ex.(Combat , BlackSmithing )
     * @return returns the Level your Skill is at, if nothing, returns 0
     */
    public int getSkillLvl(String name)
    {
        SkillBase skillData = getSkillInfo(name);
        if(skillData != null)
        {
            return skillData.getSkillLevel();
        }
        return 0;

    }

    /**
     * To get Any Info out of the Skill you are looking for and call it.
     * @param name The name of the SKill
     * @return
     */
    public SkillBase getSkillInfo(String name)
    {
        int it = 0;

        for(SkillBase skill : skillNBTData)
        {
            if(skill.getSkillName().equals(name)) {
                return skillNBTData.get(it);
            }
            it++;
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

        SkillBase skillData = getSkillInfo(name);

        int level = skillData.getSkillLevel();

        player.addChatMessage(new ChatComponentText(LogHelper.chatEvent() +
            "§6Congradulations! Your " + "§4"
                + skillData.getSkillName() + " §6is now Level §4" + level + "§6!"));
    }

    /**
     *  To test if the Skill IS Active or Inactive.
     * @param name The name of Skill
     * @return true if not equal to 0.
     */
    public boolean isActive(String name)
    {
        SkillBase skillData = getSkillInfo(name);

        return skillData != null && skillData.getSkillLevel() != 0;

    }

    public long getSkillExp(String name)
    {
        SkillBase skillData = getSkillInfo(name);

        return skillData.getSkillExp();
    }





    /**
     *  To Add EXP to the Players Skills
     * @param name The Array Number for the Skill ex.(Combat = 0, BlackSmithing = 1)
     * @param exp The Index number for the method getModifier in SkillNBT class
     */
    @SideOnly(Side.CLIENT)
    public void addExp(String name, int exp, int level, int skillLevel)
    {
        long total;
        if(isActive(name))
        {

            SkillBase skillData = getSkillInfo(name);

            int baseExp = level * level * level;
            int expGain = (int) Math.sqrt(baseExp);

            long cAmount = skillData.getSkillExp();

            total = expGain + cAmount;

            if(skillData.getBaseSkillExp(skillLevel) <= total)
            {
                 total = total - skillData.getBaseSkillExp(skillLevel);

                skillLevelUp(name);
            }

            skillData.setExp(total);
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
    }




    private ArrayList<SkillBase> getSkillData()
    {
        ArrayList<SkillBase> skillBase = new ArrayList<SkillBase>();

        int index = 0;

       /* for(String name : SkillList.COMBAT)
        {
            SkillBase skill = new SkillBase(index,name.toLowerCase(), 0, 0);
            skillBase.add(skill);
            index++;
        }

        for(String name : SkillList.COOKING)
        {
            SkillBase skill = new SkillBase(index,name.toLowerCase(), 0, 0);
            skillBase.add(skill);
            index++;
        }
        for(String name : SkillList.WEAPON)
        {
            SkillBase skill = new SkillBase(index, name.toLowerCase(), 0, 0);
            skillBase.add(skill);
            index++;
        }*/

        for(SkillType type : SkillType.values())
        {
            SkillBase skill = new SkillBase(index, type.getClassName(), type.getSkillLevel(), type.getExp());
            skillBase.add(skill);
        }
        return skillBase;
    }






}
