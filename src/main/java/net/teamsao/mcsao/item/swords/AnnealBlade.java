package net.teamsao.mcsao.item.swords;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;
import net.teamsao.mcsao.item.SItemSword;

/**
 * @author bfox1
 *
 */
public class AnnealBlade extends SItemSword {



	public AnnealBlade(ToolMaterial p_i45356_1_, String typeName ) {
		super(p_i45356_1_, typeName);
		this.setUnlocalizedName("AnnealBlade");

		this.setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(SAOTabsManager.saoTools);

	}

	
	@Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
        ItemStack sword = new ItemStack(SAOItems.AnnealBladePowered);
        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        if(!par2.isRemote && par3.isSneaking()) {
			sword.setItemDamage(par1.getItemDamage());
			sword.addEnchantment(Enchantment.unbreaking, 2);
			sword.addEnchantment(Enchantment.looting, 2);
            return sword;
        }

        return par1;
    }
	
	
	

	
}
