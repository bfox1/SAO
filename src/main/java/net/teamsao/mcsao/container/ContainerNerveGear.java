package net.teamsao.mcsao.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.tileentity.TileEntityNerveGear;

/**
 * Created by bfox1 on 7/24/2014.
 */
public class ContainerNerveGear extends Container {

    private TileEntityNerveGear nerveGear;

    public ContainerNerveGear(InventoryPlayer inventoryPlayer, TileEntityNerveGear tileEntityNerveGear)
    {
        this.nerveGear = tileEntityNerveGear;
        this.addSlotToContainer(new Slot(tileEntityNerveGear, 1, 8, 56));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int pa2)
    {
        ItemStack itemStack = null;
        Slot slot = (Slot)this.inventorySlots.get(pa2);

        if(slot != null && slot.getHasStack())
        {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            if(itemStack1.stackSize == 0)
            {
                slot.putStack(null);
            }else{
                slot.onSlotChanged();
            }
            if(itemStack1.stackSize == itemStack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(entityPlayer, itemStack1);
        }
        return itemStack;
    }


    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return this.nerveGear.isUseableByPlayer(p_75145_1_);
    }

}
