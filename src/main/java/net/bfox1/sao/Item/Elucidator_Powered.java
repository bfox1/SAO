package net.bfox1.sao.Item;

import java.util.List;

import net.bfox1.sao.help.Reference;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.enchantment.*;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author bfox1
 *
 */
public class Elucidator_Powered extends ItemSword {

	public Elucidator_Powered(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setUnlocalizedName("Elucidator_Powered");
		setTextureName(Reference.MODID + ":" + "Elucidator");

	}
	
	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
	par2EntityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 20 * 5, 100));
	return true;
	}
	

	@Override
	public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
		par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));

		ItemStack sword = new ItemStack(SItem.Elucidator);
		if (!par2.isRemote) {
			if (par1.getItemDamage() > 0) {
				sword.setItemDamage(par1.getItemDamage());
				return sword;
			}

		}
		return par1;
	}

	public void addInformation(ItemStack item, EntityPlayer player, List list,
			boolean par4) {

		list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC
				+ "A very rare and Powerful sword.");
		list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC
				+ "Can be Obtained Only by defeating");
		list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC
				+ "a specific Boss.");

	}

}
