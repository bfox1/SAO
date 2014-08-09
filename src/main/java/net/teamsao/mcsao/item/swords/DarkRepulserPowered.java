package net.teamsao.mcsao.item.swords;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.ItemSAO;

public class DarkRepulserPowered extends ItemSword {

	public DarkRepulserPowered(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("DarkRepulserPowered");
		this.setTextureName(ReferenceHelper.setItemName(SAOItems.DarkRepulser));
		this.setCreativeTab(null);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1, World world, EntityPlayer player)
	{
		ItemStack sword = new ItemStack(SAOItems.DarkRepulser);
        player.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        if(!world.isRemote)
        {
        	if(player.isSneaking())
        	{
        		sword.setItemDamage(par1.getItemDamage());
        		return sword;
        	}
        }
		return par1;
		
	}

}
