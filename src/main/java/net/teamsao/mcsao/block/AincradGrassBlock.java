package net.teamsao.mcsao.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.init.SAOBlocks;
import net.teamsao.mcsao.lib.SAOTabsManager;

public class AincradGrassBlock extends BlockSAO
{
	@SideOnly(Side.CLIENT)
	private IIcon topIcon, sideIcon;
	
	public AincradGrassBlock()
	{
		super();
		this.setBlockName("cobbleroad");
		this.setBlockTextureName(ReferenceHelper.setBlockName(this));
		this.setStepSound(Block.soundTypeGrass);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		topIcon = register.registerIcon(getUnlocalizedName()+"_top");
		sideIcon = register.registerIcon(getUnlocalizedName()+"_side");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int blockSide, int blockMetadataIcon)
	{
		if(blockSide == 0)
		{
			return SAOBlocks.AincradDirtBlock.getIcon(0, 0);
		}
		else if(blockSide == 1)
		{
			return topIcon;
		}
		else
		{
			return sideIcon;
		}
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "sao:aincradgrass";
	}
}
