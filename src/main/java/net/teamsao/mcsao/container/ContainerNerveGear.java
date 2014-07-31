package net.teamsao.mcsao.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


/**
 * Created by bfox1 on 7/24/2014.
 */
public class ContainerNerveGear extends Container {


    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return false;
    }
}
