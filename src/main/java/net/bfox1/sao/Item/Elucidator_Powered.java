package net.bfox1.sao.Item;

import java.util.List;

import net.bfox1.sao.help.Reference;
import net.minecraft.command.ICommand;
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
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * @author bfox1
 *
 */
public class Elucidator_Powered extends ItemSword {
	
	public static int PowerPool = 5000;
	
	public NBTTagCompound test;

	public Elucidator_Powered(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setUnlocalizedName("Elucidator_Powered");
		setTextureName(Reference.MODID + ":" + "Elucidator");
		this.setCreativeTab(null);

	}
	
	/*@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
	{
		if(this.PowerPool > 0)
		{
			this.PowerPool = this.PowerPool - 2;
			System.out.println(this.PowerPool);
			
		}
	}*/
	
	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving)
	{
	par2EntityLiving.addPotionEffect(new PotionEffect(Potion.harm.id, 2, 1));
	 float entity = par2EntityLiving.getHealth();
	 System.out.println(entity);
	return true;
	}
	
	public ItemStack powerToolCheck(ItemStack item, World par2, EntityPlayer par3){
		
		if(!par2.isRemote){
			if(this.PowerPool <= 0)
			{
				System.out.println("This went through");
				return new ItemStack(SItem.Elucidator);
			}
		}
		return item;
		
	}
	

	@Override
	public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
		par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));

		ItemStack sword = new ItemStack(SItem.Elucidator);
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
