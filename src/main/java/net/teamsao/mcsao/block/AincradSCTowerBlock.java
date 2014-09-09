package net.teamsao.mcsao.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.teamsao.mcsao.block.BlockSAO;
import net.teamsao.mcsao.helper.ReferenceHelper;

public class AincradSCTowerBlock extends BlockSAO
{
	@SideOnly(Side.CLIENT)
	private IIcon icon;

	public AincradSCTowerBlock()
	{
		super(Material.ground);
		this.setBlockName("aincradsctowerblock");
		this.setBlockTextureName(ReferenceHelper.setBlockName(this));
		this.setStepSound(Block.soundTypeStone);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		icon = register.registerIcon(getUnlocalizedName());
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "sao:aincradsctowerblock";
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int blockSide, int blockMetadataIcon)
	{
		return icon;
	}
	
}
