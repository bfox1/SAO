package net.teamsao.mcsao.item.swords;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.ItemSAO;

/**
 * @author bfox1
 *
 */
public class ElucidatorPowered extends ItemSword {
	
	public static int PowerPool = 5000;
	
	public NBTTagCompound test;

	public ElucidatorPowered(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setUnlocalizedName("ElucidatorPowered");
		setTextureName(Reference.MODID + ":" + "Elucidator");
		this.setCreativeTab(null);

	}
	

	
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving)
	{
	par2EntityLiving.addPotionEffect(new PotionEffect(Potion.harm.id, 2, 1));
	 float entity = par2EntityLiving.getHealth();
	 System.out.println(entity);
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

	}

}
