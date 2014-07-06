package net.bfox1.sao.Item;

import net.bfox1.sao.help.ReferenceHelper;
import net.bfox1.sao.lib.SCreativeTab;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class GuiltyThornPowered extends ItemSword {
	
	public GuiltyThornPowered(ToolMaterial par1){
	super(par1);
	this.setUnlocalizedName("GuiltyThornPowered");
	this.setTextureName(ReferenceHelper.registerItemName(SItem.GuiltyThorn));
	this.setCreativeTab(null);
	}
	
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving)
	{
	par2EntityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 20 * 5, 3));
	return true;
	}


@Override
public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
{
    par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
    ItemStack sword = new ItemStack(SItem.GuiltyThorn);

    if(!par2.isRemote)
    {
    	if(par3.isSneaking())
    	{
			sword.setItemDamage(par1.getItemDamage());
			return sword;
    	}
    }
    
    return par1;

}
}

