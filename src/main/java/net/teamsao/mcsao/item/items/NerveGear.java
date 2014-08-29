package net.teamsao.mcsao.item.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.container.ContainerNerveGear;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.inventory.InventoryNerveGear;
import net.teamsao.mcsao.item.ItemSAO;
import net.teamsao.mcsao.lib.SAOTabsManager;
import net.teamsao.mcsao.player.PlayerSAO;
import net.teamsao.mcsao.portal.SAOTeleporter;
import net.teamsao.mcsao.proxy.CommonProxy;

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

    ItemStack stack = new ItemStack(SAOItems.CDSAO);
	
	public NerveGear(ArmorMaterial material, int armorType, String name){
		super(material, 0, armorType);
		setUnlocalizedName(name);
        this.setTextureName(ReferenceHelper.setItemName(this));
		this.setCreativeTab(SAOTabsManager.SAO);
        this.setMaxStackSize(1);

	}

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 1; // return any value greater than zero
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
                par3Player.openGui(SwordArtOnline.instance, SwordArtOnline.GUI_ITEM_INV, par3Player.worldObj, (int) par3Player.posX,
                        (int) par3Player.posY, (int)par3Player.posZ);
            }
		}
		return par1ItemStack;
		
	}


    public void onArmorTick(World world, EntityPlayer players, ItemStack itemStack)
    {
        ItemStack gear = new InventoryNerveGear(itemStack).getStackInSlot(0);
        ItemStack cart = new ItemStack(SAOItems.CDSAO);
        if(gear != null && cart != null) {
            String gear2 = gear.getUnlocalizedName();
            String cart2 = cart.getUnlocalizedName();
            if (!world.isRemote) {
                if (players.getCurrentArmor(3) == itemStack && players.isPlayerSleeping() && players.dimension == 0) {
                    if (gear2.equals(cart2)) {
                        PlayerSAO prop = PlayerSAO.get(players);
                        prop.setXYZCoord((int)players.posX,(int) players.posY, (int)players.posZ);
                        PlayerSAO.saveProxyData(players);
                        EntityPlayerMP player = ((EntityPlayerMP) players);
                        SAOTeleporter.tranferPlayerToDimension(player, 2);
                    }


                }

            }
        }


        }








}
