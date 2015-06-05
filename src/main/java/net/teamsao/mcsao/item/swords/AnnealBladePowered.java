package net.teamsao.mcsao.item.swords;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.SItemSword;


/**
 * @author bfox1
 *
 */
public class AnnealBladePowered extends SItemSword {

	public AnnealBladePowered(ToolMaterial p_i45356_1_, String typeName) {
		super(p_i45356_1_, typeName);
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
                Item ssword = new AnnealBlades(ToolMaterial.EMERALD, "yes");
                ReferenceHelper.registerItem(ssword);
            return sword;
        	}
        }
        

        return par1;
		
	}

    private class AnnealBlades extends SItemSword {
        public AnnealBlades(ToolMaterial emerald, String yes) {
            super(emerald, yes);
            this.setCreativeTab(SAOTabsManager.saoTools);
        }
    }
}
