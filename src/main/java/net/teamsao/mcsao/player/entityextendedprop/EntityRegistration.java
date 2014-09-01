package net.teamsao.mcsao.player.entityextendedprop;

import net.minecraft.entity.EntityLivingBase;

/**
 * Created by bfox1 on 8/30/2014.
 */
public class EntityRegistration {

    public static void registerEntityCol(EntityLivingBase entity)
    {
        entity.registerExtendedProperties(EntityCol.ENTITY_COL_DROP, new EntityCol(entity));
    }
}
