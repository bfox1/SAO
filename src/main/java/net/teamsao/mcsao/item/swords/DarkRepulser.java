package net.teamsao.mcsao.item.swords;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;
import net.teamsao.mcsao.item.SItemSword;
//

public class DarkRepulser extends SItemSword {

	public DarkRepulser(ToolMaterial p_i45356_1_, String typeName) {
		super(p_i45356_1_);
		this.setUnlocalizedName("DarkRepulser");
		this.setTextureName(ReferenceHelper.setItemName(this));
		this.setCreativeTab(SAOTabsManager.saoTools);
		
	}
	//This is a test line
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1, World world, EntityPlayer player)
	{
		ItemStack sword = new ItemStack(SAOItems.DarkRepulserPowered);
        player.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        if(!world.isRemote)
        {
        	if(player.isSneaking())
        	{
        	sword.setItemDamage(par1.getItemDamage());
        	sword.addEnchantment(Enchantment.unbreaking, 1);
        	sword.addEnchantment(Enchantment.sharpness, 3);
        	return sword;
        	}
        }
		return par1;
		
	}

}
