package net.teamsao.mcsao.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import java.util.ArrayList;

/**
 * Created by bfox1 on 10/2/2014.
 */
public class AincradFloorSavedData extends WorldSavedData {

    public static final String IDENTIFIER = "floors";
    private ArrayList<AincradFloorNBTData> worldNBTData = getWorldData();

    public static AincradFloorSavedData forWorld(World world)
    {
        MapStorage storage = world.perWorldStorage;
        AincradFloorSavedData result = (AincradFloorSavedData)storage.loadData(AincradFloorSavedData.class, IDENTIFIER);
        if(result == null)
        {
            result = new AincradFloorSavedData();
            storage.setData(IDENTIFIER, result);
        }
        return result;
    }

    public AincradFloorSavedData() {
        super(IDENTIFIER);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(IDENTIFIER);
        for(int i =0; i<100;i++) {
            AincradFloorNBTData data = worldNBTData.get(i);
            String floorName = data.getFloorName();
            if(properties.hasKey(floorName))
            {
                boolean unlocked = properties.getBoolean(floorName);
                data.setUnlocked(unlocked);
            }
        }

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        NBTTagCompound properties = new NBTTagCompound();
        for(int i =0; i<100;i++) {
            AincradFloorNBTData data = worldNBTData.get(i);
            String floorName = data.getFloorName();
            boolean unlocked = data.isUnlocked();
            if(unlocked)
            {
                properties.setBoolean(floorName, unlocked);
            }
        }
        compound.setTag(IDENTIFIER, properties);

    }

    public void floorBossDefeat(int floorNumber)
    {
        AincradFloorNBTData data = worldNBTData.get(floorNumber);
        data.setUnlocked(true);
    }

    public boolean getUnlock()
    {
        for(int i =0; i< 100; i++) {
            AincradFloorNBTData data = worldNBTData.get(i);
            if(data.isUnlocked())
            {
                System.out.println("Floor " + i + "is unlocked.");
                return true;
            }
        }
        return false;
    }

    private ArrayList<AincradFloorNBTData> getWorldData()
    {
        ArrayList<AincradFloorNBTData> worldNBT = new ArrayList<AincradFloorNBTData>();
        boolean unlocked = false;
        for(int i = 1; i < 100; i++)
        {
            if(i == 1 )
            {
                unlocked = true;
            }
            AincradFloorNBTData worldData = new AincradFloorNBTData(i, unlocked, "floor");
            worldNBT.add(worldData);
        }
        return worldNBT;
    }
}
