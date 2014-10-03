package net.teamsao.mcsao.block;

import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeavesBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IShearable;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;
import net.teamsao.mcsao.helper.Reference;

/**
 * Used in order to use our mod's localization and undestructible block properties with the leaves class from base
 * minecraft. The choice here is between copying over the class we want from minecraft or copying our properties into
 * a base class. I think copying some custom properties into a base class is easier by a lot.
 * @author Ian
 *
 */
public class LeavesSAO extends BlockLeavesBase
{
	public static boolean isBreakable = false;

	public LeavesSAO(Material blockMaterial, boolean renderInFancy)
	{
		super(blockMaterial, renderInFancy);
		if (isBreakable)
        {
            this.setHardness(5.0F);
            this.setResistance(-1.0F);
        }
        else if (!isBreakable)
        {
            this.setBlockUnbreakable();
            this.setResistance(30000000.0F);
        }
        this.setLightOpacity(0);
        this.setCreativeTab(SAOTabsManager.saoBlocks);
	}
	
	@Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
    
    public void setBreakable(boolean flag)
    {
    	this.isBreakable = flag;
    	if (isBreakable)
        {
            this.setHardness(5.0F);
            this.setResistance(-1.0F);
        }
        else if (!isBreakable)
        {
            this.setBlockUnbreakable();
            this.setResistance(30000000.0F);
        }
    }
	
}
