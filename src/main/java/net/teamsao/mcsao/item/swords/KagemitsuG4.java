package net.teamsao.mcsao.item.swords;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.item.UnsheathingSword;

public class KagemitsuG4 extends UnsheathingSword
{
	private EntityPlayer currentOwner;
	private InventoryPlayer currentInventory;
	
	public KagemitsuG4(ToolMaterial material)
	{
		super(material);
		this.setUnlocalizedName("KagemitsuG4");
		this.setTextureName("KagemitsuG4");
		this.setCreativeTab(null);
		setAnimationSpeed(2);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		return itemstack;
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
			updateAnimation(currentInventory, inventoryIndex, isItemAnimating);
		}
    }

}
