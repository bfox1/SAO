package net.teamsao.mcsao.Item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;

public class DarkRepulserPowered extends ItemSword {

	public DarkRepulserPowered(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("DarkRepulserPowered");
		this.setTextureName(ReferenceHelper.registerItemName(SItem.DarkRepulser));
		this.setCreativeTab(null);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1, World world, EntityPlayer player)
	{
		ItemStack sword = new ItemStack(SItem.DarkRepulser);
        player.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        if(!world.isRemote)
        {
        	sword.setItemDamage(par1.getItemDamage());
        	return sword;
        }
		return par1;
		
	}

}
