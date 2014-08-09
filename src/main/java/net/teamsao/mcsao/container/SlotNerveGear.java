package net.teamsao.mcsao.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.init.SAOItems;

/**
 * Created by bfox1 on 8/8/2014.
 */
public class SlotNerveGear extends Slot {

    public SlotNerveGear(IInventory inventory1, int par2, int par3, int par4) {
        super(inventory1, par2, par3, par4);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() == SAOItems.CDSAO;
    }

    @Override
    public int getSlotStackLimit()
    {
        return 1;
    }
}
