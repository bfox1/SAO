package net.teamsao.mcsao.item.swords;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.item.SItemSword;
import net.teamsao.mcsao.item.UnsheathingSword;
import net.teamsao.mcsao.helper.ColorHelper;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
//
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Created by bfox1 on 7/9/2014.
 */
public class ObjectEraser extends UnsheathingSword
{
	private EntityPlayer currentOwner;
	private InventoryPlayer currentInventory;
	
	public ObjectEraser(ToolMaterial material, String typeName )
	{
		super(material, typeName);
		this.setUnlocalizedName("ObjectEraser");
		this.setTextureName("ObjectEraser");
		this.setCreativeTab(null);
		setAnimationSpeed(3);
	}

    @Override
    public ItemStack onItemRightClick(ItemStack sword, World world, EntityPlayer player)
    {
        player.setItemInUse(sword, this.getMaxItemUseDuration(sword));
        return sword;
    }

    @Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int inventoryIndex, boolean isItemAnimating)
    {
    	if(entity instanceof EntityPlayer && ((currentOwner != null && currentOwner != entity) || currentOwner == null))
    	{
    		currentOwner = (EntityPlayer)entity;
    		currentInventory = currentOwner.inventory;
    	}
		if(!world.isRemote)
		{
			updateAnimation(currentInventory, inventoryIndex, isItemAnimating);
            currentOwner.setAbsorptionAmount(15.5F);
			if(!itemstack.isItemEnchanted())
			{
				itemstack.addEnchantment(Enchantment.sharpness, 5);
			}
			if(entity.isBurning())
			{
				entity.extinguish();
			}
			if(currentOwner.isPotionActive(Potion.wither) || currentOwner.isPotionActive(Potion.weakness) 
					|| currentOwner.isPotionActive(Potion.poison) || currentOwner.isPotionActive(Potion.confusion) 
					|| currentOwner.isPotionActive(Potion.blindness))
			{
				currentOwner.clearActivePotions();
			}
			if(currentOwner.getHealth() != currentOwner.getMaxHealth())
			{
				currentOwner.heal(10);
			}
		}
	}

    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
    {
        list.add(ColorHelper.DARK_RED + "" + ColorHelper.ITALIC + "A Legendary Sword");
        list.add(ColorHelper.DARK_RED + "" + ColorHelper.ITALIC + "Said to hold Infinite");
        list.add(ColorHelper.DARK_RED + "" + ColorHelper.ITALIC + "Power unlike any other.");
    }
    
}
