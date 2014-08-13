package net.teamsao.mcsao.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.teamsao.mcsao.recipes.ForgeStationRecipes;

/**
 * Created by bfox1 on 7/12/2014.
 * Edited by Earbuds on 8/7/2014.
 */
/*
    Be sure Earbuds to @Override anymethods that have the Up arrow next to it.
    Most methods have an indicator on the left side that says that it has a super method. Not overriding it
    can result in alot of conflicts.
 */
public class TileEntityForgeStation extends TileEntity implements IInventory{
   
	//Where sides items can be put into/taken out of.
	private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    
	//private ItemStack[] inv;
    public ItemStack[] slots = new ItemStack[3];
    
    //public int cookTime;
    //public int power;
    
    public int furnaceSpeed = 400;
    public int burnTime;
    public int currentItemBurnTime;
    public int cookTime;
    
    private String field_145958_o;

    public static final TileEntityForgeStation forgeBase = new TileEntityForgeStation();
    
    public static TileEntityForgeStation incinerating()  {
        return forgeBase;
    }

    private String getInventoryName;

    @Override
    public int getSizeInventory() {
        return this.slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return slots[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {
        if (this.slots[slot] != null) {
            ItemStack itemstack;

            if (this.slots[slot].stackSize <= amt) {
                itemstack = this.slots[slot];
                this.slots[slot] = null;
                return itemstack;
            } else{
                itemstack = this.slots[slot].splitStack(amt);

                if (this.slots[slot].stackSize == 0) {
                    this.slots[slot] = null;
                }

                return itemstack;
            }
        }
        else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (this.slots[slot] != null) {
            ItemStack itemstack = this.slots[slot];
            this.slots[slot] = null;
            return itemstack;
        }
        else {
        	return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.slots[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.field_145958_o : "container.molecularIncinerator";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.field_145958_o != null && this.field_145958_o.length() > 0;
    }
    
    public void func_145951_a(String string) {
        this.field_145958_o = string;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }
    
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int i) {
        return this.cookTime * i / this.furnaceSpeed;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int i) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = this.furnaceSpeed;
        }

        return this.burnTime * i / this.currentItemBurnTime;
    }
    
    public boolean isBurning() {
        return this.burnTime > 0;
    }

    @Override
    public void updateEntity() {
        boolean flag = this.burnTime > 0;
        boolean flag1 = false;

        if (this.burnTime > 0) {
            --this.burnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.burnTime == 0 && this.canSmelt()) {
                //this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.slots[1]); TODO

                if (this.burnTime > 0) {
                    flag1 = true;

                    if (this.slots[1] != null) {
                        --this.slots[1].stackSize;

                        if (this.slots[1].stackSize == 0) {
                            this.slots[1] = slots[1].getItem().getContainerItem(slots[1]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt()) {
                ++this.cookTime;

                if (this.cookTime == this.furnaceSpeed) {
                    this.cookTime = 0;
                    //this.smeltItem(); TODO
                    flag1 = true;
                }
            }else {
                this.cookTime = 0;
            }

            if (flag != this.burnTime > 0) {
                flag1 = true;
            }
        }

        if (flag1) {
            this.markDirty();
        }
    }

    private boolean canSmelt() {
        if (this.slots[0] == null) {
            return false;
        }else {
            ItemStack itemstack = ForgeStationRecipes.forging().getForgingResult(this.slots[0]);
            if (itemstack == null) return false;
            if (this.slots[2] == null) return true;
            if (!this.slots[2].isItemEqual(itemstack)) return false;
            int result = slots[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.slots[2].getMaxStackSize();
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", par1NBTTagCompound.getId());
        this.slots = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound item = (NBTTagCompound)nbttaglist.getCompoundTagAt(i);
            byte b0 = item.getByte("Slot");

            if (b0 >= 0 && b0 < getSizeInventory()) {
                this.slots[b0] = ItemStack.loadItemStackFromNBT(item);
            }
        }



        if (par1NBTTagCompound.hasKey("CustomName")) {
            this.getInventoryName = par1NBTTagCompound.getString("CustomName");
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.slots.length; ++i) {
            if (this.slots[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.slots[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);


    }



}
