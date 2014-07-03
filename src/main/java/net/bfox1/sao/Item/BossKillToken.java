
package net.bfox1.sao.Item;

import java.util.List;

import net.bfox1.sao.help.Reference;
import net.bfox1.sao.lib.SCreativeTab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


/**
 * @author bfox1
 *
 */
public class BossKillToken extends Item {

	public BossKillToken(){
	super();
	this.setUnlocalizedName("BossKillToken");
	this.setMaxStackSize(1);
	this.setHasSubtypes(true);

	this.setCreativeTab(SCreativeTab.SAO);

	}
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IIconRegister parIconRegister)
	{
		icons = new IIcon[2];
		
		for(int i = 0; i < icons.length; i++)
		{
			icons[i] = parIconRegister.registerIcon(Reference.MODID + ":" + (this.getUnlocalizedName().substring(5)) + i);
		}
	}
	
//This is the Number of Meta Items using BossKillTokens.
	//For Creating textures do it as follows: BossKillToken:(#) ex: BossKillToken:0.png, BossKillToken:1.png
	public static final String[] names = new String[]
			{
				"0",
				"1"
			};
	
	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
		return super.getUnlocalizedName() + ":" + names[i]; //The item name in game will not have the Extra strings after getUnlocalizedName.
	}
	
	@Override
	public IIcon getIconFromDamage(int par1)
	{
		return icons[par1];
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for(int x = 0; x < 2; x++)
		{
			par3List.add(new ItemStack(this, 1, x));
		}
	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        System.out.println();
        if(par1.getItemDamage() == 0)
        {
        	
        
        	if(!par2.isRemote)
        	{
        		System.out.println(par1);
        		System.out.println("This is 1");
        		return new ItemStack(SItem.HeartOfKobold);
        	}
        }
        if(par1.getItemDamage() > 0)
        {
        	if(!par2.isRemote)
        	{
        		System.out.println(par1);
        		System.out.println("This is 2");
        	}

        }
        return par1;
    }
}
