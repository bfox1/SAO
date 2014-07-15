package net.teamsao.mcsao.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.tileentity.TileEntityForgeStation;

/**
 * Created by bfox1 on 7/12/2014.
 */
public class ContainerForgeStation extends Container {

    private TileEntityForgeStation forgeStation;


    public ContainerForgeStation(InventoryPlayer par1InventoryPlayer, TileEntityForgeStation te)
    {
        forgeStation = te;

        //the Slot constructor takes the IInventory and the slot number in that it binds to
        //and the x-y coordinates it resides on-screen
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addSlotToContainer(new Slot(forgeStation, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }

        //commonly used vanilla code that adds the player's inventory
        bindPlayerInventory(par1InventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
       // return forgeStation.isUseableByPlayer(player);
        return true;
    }





    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int par2) {
        ItemStack itemStack = null;
        Slot slot = (Slot) this.inventorySlots.get(par2);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack1 = slot.getStack();
            itemStack = itemStack1.copy();

            //merges the item into player inventory since its in the tileEntity
            if (par2 < 9) {
                if (!this.mergeItemStack(itemStack1, 0, 35, true)) {
                    return null;
                }
                slot.onSlotChange(itemStack1, itemStack);
            }
            //places it into the tileEntity is possible since its in the player inventory
            else if (!this.mergeItemStack(itemStack1, 0, 9, false)) {
                return null;
            }

            if (itemStack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }

            if (itemStack1.stackSize == itemStack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, itemStack1);
        }
     //   System.out.println("WTF" + stack.stackSize);
        return itemStack;
    }
}
