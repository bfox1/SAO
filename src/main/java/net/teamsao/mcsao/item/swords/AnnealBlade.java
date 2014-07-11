package net.teamsao.mcsao.item.swords;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.item.SItem;
import net.teamsao.mcsao.lib.SAOTabsManager;
import net.teamsao.mcsao.lib.SCreativeTab;

/**
 * @author bfox1
 *
 */
public class AnnealBlade extends ItemSword {

	public AnnealBlade(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("AnnealBlade");
		this.setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(SAOTabsManager.saoTools);
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
        ItemStack sword = new ItemStack(SItem.AnnealBladePowered);
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
