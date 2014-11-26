package net.teamsao.mcsao.item.swords;

import net.teamsao.mcsao.init.SAOItems;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;
import net.teamsao.mcsao.item.ItemSAO;
import net.teamsao.mcsao.item.SItemSword;
//

/**
 * @author bfox1
 *
 */
public class Elucidator extends SItemSword {
	
	public int PowerPool = ElucidatorPowered.PowerPool;

	public Elucidator(ToolMaterial par1, String typeName) {
		super(par1, typeName);
		setUnlocalizedName("Elucidator");
		setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(SAOTabsManager.saoTools);

	}
	
	
	@Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        ItemStack sword = new ItemStack(SAOItems.ElucidatorPowered);
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
