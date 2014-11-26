package net.teamsao.mcsao.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public abstract class UnsheathingSword extends SItemSword
{
	@SideOnly(Side.CLIENT)
	public IIcon[] icons;
	
	private int animationStage = 0;
	/**
	 * Speed of the fwooshing animation goes down as this goes up. 1/x.
	 */
	private int animationSpeed = 3;
	
	public int getAnimationSpeed()
	{
		return animationSpeed;
	}
	/**
	 * Sets the speed of the animation which shows that the sword unsheaths itself when you equip it in your hotbar.
	 * Higher speeds slow down the rate of animation by 1/x where x is the input variable.
	 * @param animationSpeed
	 */
	public void setAnimationSpeed(int animationSpeed)
	{
		this.animationSpeed = animationSpeed;
	}

	public UnsheathingSword(ToolMaterial p_i45356_1_, String typeName )
	{
		super(p_i45356_1_, typeName);
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
    
    protected void updateAnimation(InventoryPlayer currentInventory, int itemIndex, boolean animating)
    {
    	if(currentInventory != null)
    	{
    		if(currentInventory.currentItem == itemIndex && animationStage < icons.length*animationSpeed)
        	{
        		animationStage++;
        	}
        	else if((animationStage >= icons.length*animationSpeed && !animating) || !animating)
    		{
    			animationStage = 0;
    		}
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

}