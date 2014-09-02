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
import net.teamsao.mcsao.helper.ColorHelper;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
//
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Created by bfox1 on 7/9/2014.
 */
public class ObjectEraser extends SItemSword
{
	@SideOnly(Side.CLIENT)
	public IIcon[] icons;
	
	private int animationStage = 0;
	private EntityPlayer currentOwner;
	private InventoryPlayer currentInventory;
	/**
	 * Speed of the fwooshing animation goes down as this goes up. 1/x.
	 */
	private int animationSpeed = 3;
	
	public ObjectEraser(ToolMaterial p_i45356_1_)
	{
		super(p_i45356_1_);
		this.setUnlocalizedName("ObjectEraser");
		this.setTextureName("ObjectEraser");
		this.setCreativeTab(null);
	}

    @Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        return par1;
    }

    @Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int inventoryIndex, boolean isItemAnimating)
    {
    	if(entity instanceof EntityPlayer && ((currentOwner != null && currentOwner != entity) || currentOwner == null))
    	{
    		currentOwner = (EntityPlayer)entity;
    		currentInventory = currentOwner.inventory;
    	}
		if (!world.isRemote)
		{
			updateAnimation(inventoryIndex, isItemAnimating);
            currentOwner.setAbsorptionAmount(15.5F);
			if (itemstack.isItemEnchanted() == false)
			{
				itemstack.addEnchantment(Enchantment.sharpness, 5);
			}
			if (entity.isBurning())
			{
				entity.extinguish();
			}
			if (currentOwner.isPotionActive(Potion.wither) || currentOwner.isPotionActive(Potion.weakness) 
					|| currentOwner.isPotionActive(Potion.poison) || currentOwner.isPotionActive(Potion.confusion) 
					|| currentOwner.isPotionActive(Potion.blindness))
			{
				currentOwner.clearActivePotions();
			}
			if (currentOwner.getHealth() != currentOwner.getMaxHealth())
			{
				currentOwner.heal(10);
			}
		}
	}
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister register)
    {
    	icons = new IIcon[5];
    	
    	for(int i = 0; i < icons.length; i++)
    	{
    		icons[i] = register.registerIcon("sao:"+this.iconString+""+(i+1));
    	}
    }
    
    private void updateAnimation(int itemIndex, boolean animating)
    {
    	if(currentInventory.currentItem == itemIndex && animationStage < icons.length*animationSpeed)
    	{
    		animationStage++;
    	}
    	else if(animationStage >= icons.length*animationSpeed && !animating)
		{
			animationStage = 0;
		}
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int damage)
    {
    	if(animationStage/animationSpeed >= icons.length)
    	{
    		return icons[icons.length-1];
    	}
    	else if(animationStage/animationSpeed <= 0)
    	{
    		return icons[0];
    	}
    	else
    	{
    		return icons[animationStage/animationSpeed];
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
