package net.bfox1.sao.Item;

import java.util.List;

import net.bfox1.sao.help.Reference;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.enchantment.*;
import net.minecraftforge.common.util.EnumHelper;

public class Elucidator_Powered extends ItemSword {

	public Elucidator_Powered(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setUnlocalizedName("Elucidator_Powered");
		setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		
	}
	
	@Override
    public EnumRarity getRarity(ItemStack par1)
    {
        return EnumRarity.epic;
    }
	





	@Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));

        if(!par2.isRemote)
        {

        }
        return new ItemStack(SItem.Elucidator);
    }
	
	public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
	{
		list.add(EnumChatFormatting.GOLD + "The Powerful Sword");

	}
	

	


}
