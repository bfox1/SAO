package net.teamsao.mcsao.item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SCreativeTab;

public class EbonDaggerPowered extends ItemSword {

	public EbonDaggerPowered(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("EbonDaggerPowered");
		this.setTextureName(ReferenceHelper.setItemName(SItem.EbonDagger));
		this.setCreativeTab(null);
	}
	
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving)
	{
	par3EntityLiving.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 10, 1));
	return true;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
	{
        player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        ItemStack sword = new ItemStack(SItem.EbonDagger);
		if(!world.isRemote)
		{
			if(player.isSneaking())
			{
    			sword.setItemDamage(par1ItemStack.getItemDamage());
				return sword;
			}
		}
		return par1ItemStack;
	}

}
