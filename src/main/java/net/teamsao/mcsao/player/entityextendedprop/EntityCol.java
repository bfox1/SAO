package net.teamsao.mcsao.player.entityextendedprop;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;


import java.util.Random;

/**
 * Created by bfox1 on 8/30/2014.
 */
public class EntityCol implements IExtendedEntityProperties {

    public final static String ENTITY_COL_DROP = "EntityColDrop";

    @SuppressWarnings("FieldCanBeLocal")
    private final EntityLivingBase entity;

    private int col;

    public EntityCol(EntityLivingBase entity)
    {
        this.entity = entity;
    }

    public static byte loadEntityID(EntityLivingBase entity)
    {
        return entity.getEntityData().getId();
    }

    public static EntityCol get(EntityLivingBase entity){
        return (EntityCol)entity.getExtendedProperties(ENTITY_COL_DROP);
    }
    @Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setInteger("Col",this.col);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        this.col = compound.getInteger("Col");
    }

    @Override
    public void init(Entity entity, World world) {
    }

    public void addCol(int amt)
    {
        this.col = amt;
    }

    public int getCol()
    {
        return this.col;
    }

    public int randomExpGenerator(int start, int end)
    {
        Random rand = new Random();
        int range = end - start + 1;
        int fraction = (int)(range * rand.nextDouble());
        int randnum = fraction + start;
        return randnum;
    }





}
