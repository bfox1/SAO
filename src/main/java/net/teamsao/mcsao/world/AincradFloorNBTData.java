package net.teamsao.mcsao.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

/**
 * Created by bfox1 on 10/2/2014.
 */
public class AincradFloorNBTData
{
    private int floorAmount = 100;
    private int floorNumber;
    private boolean isUnlocked;
    private String floorname;


    public AincradFloorNBTData(int floorNumber, boolean isUnlocked, String name)
    {
        this.floorNumber = floorNumber;
        this.isUnlocked = isUnlocked;
        this.floorname = name + floorNumber;
    }

    public int getFloorAmount()
    {
        return floorAmount;
    }

    public String getFloorName()
    {
        return this.floorname;
    }

    public int getFloorNumber()
    {
        return floorNumber;
    }

    public boolean isUnlocked()
    {
        return isUnlocked;
    }

    public void setFloorNumber(int floorNumber)
    {
        this.floorNumber = floorNumber;
    }
    public void setUnlocked(boolean ifUnlocked)
    {
        this.isUnlocked = ifUnlocked;
    }


}
