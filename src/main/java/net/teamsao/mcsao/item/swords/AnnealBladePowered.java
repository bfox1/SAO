package net.teamsao.mcsao.item.swords;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;


/**
 * @author bfox1
 *
 */
public class AnnealBladePowered extends ItemSword {

	public AnnealBladePowered(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("AnnealBladePowered");
		this.setTextureName(ReferenceHelper.setItemName(SAOItems.AnnealBlade));
		this.setCreativeTab(null);
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
	{
        ItemStack sword = new ItemStack(SAOItems.AnnealBlade);

        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
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
