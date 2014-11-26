package net.teamsao.mcsao.item.swords;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.SItemSword;
import net.teamsao.mcsao.item.UnsheathingSword;

/**
 * @author 5chris100
 */
public class KagemitsuG4 extends UnsheathingSword
{
	private EntityPlayer currentOwner;
	private InventoryPlayer currentInventory;

    public KagemitsuG4(ToolMaterial material, String typeName )
    {
        super(material, typeName);
        this.setUnlocalizedName("KagemitsuG4");
        this.setTextureName("KagemitsuG4");
        this.setCreativeTab(SAOTabsManager.saoTools);
        setAnimationSpeed(2);
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

    @Override
    public ItemStack onItemRightClick(ItemStack sword, World world, EntityPlayer player)
    {
        player.setItemInUse(sword, this.getMaxItemUseDuration(sword));
        ItemStack swordPowered = new ItemStack(SAOItems.KagemitsuG4Powered);
        swordPowered.addEnchantment(Enchantment.sharpness, 4);
        swordPowered.addEnchantment(Enchantment.power, 3);
        if (!world.isRemote)
        {
            if (player.isSneaking())
            {
                swordPowered.setItemDamage(sword.getItemDamage());
                return swordPowered;
            }
        }
        return sword;
    }
}
