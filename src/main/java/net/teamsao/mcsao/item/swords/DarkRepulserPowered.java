package net.teamsao.mcsao.item.swords;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.ItemSAO;
import net.teamsao.mcsao.util.ColorHelper;

import java.util.List;

public class DarkRepulserPowered extends ItemSword {

	public DarkRepulserPowered(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		this.setUnlocalizedName("DarkRepulserPowered");
		this.setTextureName(ReferenceHelper.setItemName(SAOItems.DarkRepulser));
		this.setCreativeTab(null);
	}
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        EntityPlayer player = (EntityPlayer)entity;

        if(!world.isRemote)
        {
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, 2));
        }
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1, World world, EntityPlayer player)
	{
		ItemStack sword = new ItemStack(SAOItems.DarkRepulser);
        player.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        if(!world.isRemote)
        {
        	if(player.isSneaking())
        	{
        		sword.setItemDamage(par1.getItemDamage());
        		return sword;
        	}
        }
		return par1;
		
	}

    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
    {
        list.add(ColorHelper.DARK_BLUE + "" + ColorHelper.ITALIC + "SPEED BOOST 2");
    }

}
