package net.teamsao.mcsao.Item;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SCreativeTab;

public class GuiltyThorn extends ItemSword {

	public GuiltyThorn(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("GuiltyThorn");
		this.setTextureName(ReferenceHelper.registerItemName(this));
		this.setCreativeTab(SCreativeTab.SAO);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        ItemStack sword = new ItemStack(SItem.GuiltyThornPowered);
        sword.addEnchantment(Enchantment.sharpness, 5);

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
