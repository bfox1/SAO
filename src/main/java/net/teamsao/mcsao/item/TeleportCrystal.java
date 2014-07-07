package net.teamsao.mcsao.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SCreativeTab;


/**
 * @author bfox1
 *
 */
public class TeleportCrystal extends Item {

	public TeleportCrystal(){
		super();
		this.setUnlocalizedName("TeleportCrystal");
		this.setTextureName(ReferenceHelper.setItemName(this));
		this.setCreativeTab(SCreativeTab.SAO);
	}
	//Opens a GUI to go to teleport Points. IF ALLOWED.
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3Player)
	{
		if(!par2World.isRemote)
		{
			if(par3Player.isSneaking())
			{
				//Opens a GUI to go to teleport Points. IF ALLOWED.
			}
		}
		return par1ItemStack;
		
	}
}
