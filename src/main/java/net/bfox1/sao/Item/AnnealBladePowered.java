package net.bfox1.sao.Item;

import net.bfox1.sao.help.Reference;
import net.bfox1.sao.lib.SCreativeTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;


/**
 * @author bfox1
 *
 */
public class AnnealBladePowered extends ItemSword {

	public AnnealBladePowered(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("AnnealBladePowered");
		setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(SCreativeTab.SAO);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
	{
        ItemStack sword = new ItemStack(SItem.AnnealBlade);

        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        if(!par2.isRemote)
        {
			sword.setItemDamage(par1.getItemDamage());
            return sword;
        }
        

        return par1;
		
	}

}
