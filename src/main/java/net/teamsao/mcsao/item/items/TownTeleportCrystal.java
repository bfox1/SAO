package net.teamsao.mcsao.item.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.item.ItemSAO;
import net.teamsao.mcsao.world.DimensionId;

/**
 * 
 * @author SirPwn on Aug 12, 2014
 *
 */

public class TownTeleportCrystal extends ItemSAO {
	
	public TownTeleportCrystal()
	{
		this.setMaxStackSize(1);
		this.setUnlocalizedName("TownTeleportCrystal");
        this.setTextureName(ReferenceHelper.setItemName(this));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3Player) {
        if (par2World.isRemote) {
            if (par3Player.isSneaking() && par3Player.dimension == DimensionId.SAO_DIMENSION_ID) {
                System.out.println("Past area");
                par3Player.openGui(SwordArtOnline.instance, SwordArtOnline.GUI_TELEPORT_CRYSTAL, par3Player.worldObj, (int) par3Player.posX,
                        (int) par3Player.posY, (int) par3Player.posZ);
            }
        }
        return par1ItemStack;

    }

    @Override
    public boolean isFull3D() {
        return true;
    }
}
