package net.teamsao.mcsao.item.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.ItemSAO;
import net.teamsao.mcsao.lib.SAOTabsManager;
import net.teamsao.mcsao.portal.SAOTeleporter;

/**
 * This file was forked from HeartOfKobold (thanks, bfox! :D).
 * TODO:
 * [ ] Finish class
 * [ ] Make texture
 * [ ] Make undamageable
 * [ ] Create GUI in onItemRightClick
 * 
 *  @author 5chris100, edited by Bfox1
 */
public class NerveGear extends ItemArmor {

    int tickCounter;
	
	public NerveGear(ArmorMaterial material, int armorType, String name){
		super(material, 0, armorType);
		setUnlocalizedName(name);
        this.setTextureName(ReferenceHelper.setItemName(this));
		this.setCreativeTab(SAOTabsManager.SAO);

	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if(stack.getItem() == SAOItems.NerveGear)
		{
			return Reference.MODID + ":models/armor/NerveGear.png";
		}
		else
		{
			System.out.println("Invalid Item Armor");
		}
		return null;
		
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3Player)
	{
		if(!par2World.isRemote)
		{
			if(par3Player.isSneaking())
			{
				//Opens Gui for insertion of Cartridge. 
			}
		}
		return par1ItemStack;
		
	}


    public void onArmorTick(World world, EntityPlayer players, ItemStack itemStack)
    {
        EntityPlayer entityPlayer;


        if(!world.isRemote) {
                if(players.getCurrentArmor(3) == itemStack && players.isPlayerSleeping()) {

                    EntityPlayerMP player = ((EntityPlayerMP)players);

                    SAOTeleporter.tranferPlayerToDimension(player, 2);




                }

            }


        }








}
