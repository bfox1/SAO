package net.teamsao.mcsao.item.swords;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;
//

public class EbonDagger extends ItemSword {

	public EbonDagger(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("EbonDagger");
		this.setTextureName(ReferenceHelper.setItemName(this));
		this.setCreativeTab(SAOTabsManager.saoTools);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
	{
        player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        ItemStack sword = new ItemStack(SAOItems.EbonDaggerPowered);
		if(!world.isRemote)
		{
			if(player.isSneaking())
			{
    			sword.setItemDamage(par1ItemStack.getItemDamage());
				sword.addEnchantment(Enchantment.unbreaking, 1);
				return sword;
			}
		}
		return par1ItemStack;
	}

}
