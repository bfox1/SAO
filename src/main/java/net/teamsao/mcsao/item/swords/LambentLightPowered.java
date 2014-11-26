package net.teamsao.mcsao.item.swords;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.SItemSword;

public class LambentLightPowered extends SItemSword {

	public LambentLightPowered(ToolMaterial p_i45356_1_, String typeName ) {
		super(p_i45356_1_, typeName);
		this.setUnlocalizedName("LambentLightPowered");
		this.setTextureName(ReferenceHelper.setItemName(SAOItems.LambentLight));
		this.setCreativeTab(null);
	}
	
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving)
	{
    par1ItemStack.damageItem(1, par3EntityLiving);
	par2EntityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 500, 3));
	return true;
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        ItemStack sword = new ItemStack(SAOItems.LambentLight);

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
