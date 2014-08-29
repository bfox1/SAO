package net.teamsao.mcsao.datawatcher;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.DataWatcher;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class MainDataWatcher {

    @SubscribeEvent
    public void onKill(LivingAttackEvent evt) {
        if (evt.entity.isDead) {
            DataWatcher dw = evt.entity.getDataWatcher();

        }
    }

}
