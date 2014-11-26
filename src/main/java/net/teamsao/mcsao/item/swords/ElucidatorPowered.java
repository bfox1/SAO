package net.teamsao.mcsao.item.swords;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.helper.ColorHelper;
import net.teamsao.mcsao.item.SItemSword;

/**
 * @author bfox1
 *
 */
public class ElucidatorPowered extends SItemSword {
	
	public static int PowerPool = 5000;
	
	public NBTTagCompound test;

	public ElucidatorPowered(ToolMaterial p_i45356_1_, String typeName) {
		super(p_i45356_1_, typeName);
		setUnlocalizedName("ElucidatorPowered");
		setTextureName(Reference.MODID + ":" + "Elucidator");
		this.setCreativeTab(null);

	}
	

	
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving)
	{
	par2EntityLiving.addPotionEffect(new PotionEffect(Potion.harm.id, 10, 2));
	 float entity = par2EntityLiving.getHealth();
    par1ItemStack.damageItem(1, par3EntityLiving);
	return true;
	}

	

	@Override
	public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
		par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));

		ItemStack sword = new ItemStack(SAOItems.Elucidator);
		if (!par2.isRemote) {
			if (par3.isSneaking()) {
				sword.setItemDamage(par1.getItemDamage());
				return sword;
			}

		}
		return par1;
	}
	
	@Override
	public void addInformation(ItemStack item, EntityPlayer player, List list,
			boolean par4) {

		list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC
				+ "A very rare and Powerful sword.");
		list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC
				+ "Can be Obtained Only by defeating");
		list.add(EnumChatFormatting.DARK_BLUE + "" + EnumChatFormatting.ITALIC
				+ "a specific Boss.");
        list.add(EnumChatFormatting.DARK_PURPLE + "" + ColorHelper.ITALIC
                + "Harming 2 Damage");

	}

}
