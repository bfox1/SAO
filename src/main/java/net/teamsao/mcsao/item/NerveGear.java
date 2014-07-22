package net.teamsao.mcsao.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SAOTabsManager;
import net.teamsao.mcsao.lib.SCreativeTab;

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
		if(stack.getItem() == SItem.NerveGear)
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

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if(tickCounter == 10)
        {
           EntityPlayer player = ((EntityPlayer)par3Entity);
          //  if(player.inventory.armorInventory == new ItemStack(SItem.NerveGear)) {
                ChunkCoordinates name = player.getBedLocation(0);

                int x = name.posX;
                int y = name.posY;
                int z = name.posZ;

                player.addChatMessage(new ChatComponentText(x + ":" + y + ":" + z));

                this.tickCounter = 0;
          //  }


        }
        tickCounter++;
    }


}
