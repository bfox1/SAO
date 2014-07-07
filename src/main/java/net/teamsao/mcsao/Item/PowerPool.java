package net.teamsao.mcsao.Item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PowerPool extends Item {
	
	public Item itemName;
	
	public int PowerPool;
	
	   public PowerPool getItem()
	    {
	        return itemName;
	    }
	
    public void setPowerValue(int p_77964_1_)
    {
        this.getItem().s(this, p_77964_1_);
    }
    
    public void setPower(PowerPool item, int par1)
    {
    item.PowerPool = par1;
    }

}
