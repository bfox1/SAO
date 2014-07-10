package net.teamsao.mcsao.item.swords;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.item.SItem;
import net.teamsao.mcsao.lib.SCreativeTab;

public class DarkRepulser extends ItemSword {

	public DarkRepulser(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("DarkRepulser");
		this.setTextureName(ReferenceHelper.setItemName(this));
		this.setCreativeTab(SCreativeTab.SAO_Tools);
		
	}
	//This is a test line
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1, World world, EntityPlayer player)
	{
		ItemStack sword = new ItemStack(SItem.DarkRepulserPowered);
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
