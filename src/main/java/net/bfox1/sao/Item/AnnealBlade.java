package net.bfox1.sao.Item;

import net.bfox1.sao.help.Reference;
import net.bfox1.sao.lib.SCreativeTab;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class AnnealBlade extends ItemSword {

	public AnnealBlade(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("AnnealBlade");
		setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(SCreativeTab.SAO);

	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        ItemStack sword = new ItemStack(SItem.Elucidator_Powered).setStackDisplayName(EnumChatFormatting.DARK_GREEN + "Elucidator");

        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        if(!par2.isRemote)
        {
            sword.addEnchantment(Enchantment.fireAspect, 2);
            System.out.println(this.getMaxDamage());
            return par1;
        }
        

        return par1;
    }

}
