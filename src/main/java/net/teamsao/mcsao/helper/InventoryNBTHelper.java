package net.teamsao.mcsao.helper;

import net.minecraft.item.Item;

/**
 * Created by bfox1 on 11/18/2014.
 */
public class InventoryNBTHelper {


    private int index;

    private Item item;

    private int amount;

    private int dimensionID;

    public InventoryNBTHelper(int index, Item item, int amount, int dimensionID)
    {
        this.index = index;

        this.item = item;

        this.amount = amount;

        this.dimensionID = dimensionID;
    }

    public int getIndex()
    {
        return index;
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item1)
    {
        this.item = item1;
    }

    public int getAmount()
    {
        return this.amount;
    }


    public int getDimensionID() {
        return dimensionID;
    }

    public void setDimensionID(int dimensionID) {
        this.dimensionID = dimensionID;
    }
}
