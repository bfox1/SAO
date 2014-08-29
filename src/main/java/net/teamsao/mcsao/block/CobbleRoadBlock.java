package net.teamsao.mcsao.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.help.ReferenceHelper;

public class CobbleRoadBlock extends BlockSAO
{
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public CobbleRoadBlock()
	{
		super(Material.rock);
		this.setBlockName("cobbleroad");
		this.setBlockTextureName(ReferenceHelper.setBlockName(this));
		this.setStepSound(Block.soundTypeStone);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister register)
	{
		icons = new IIcon[6];
		icons[0] = register.registerIcon(getUnlocalizedName()+"bottom");
		icons[1] = register.registerIcon(getUnlocalizedName()+"top");
		icons[2] = register.registerIcon(getUnlocalizedName()+"front");
		icons[3] = register.registerIcon(getUnlocalizedName()+"back");
		icons[4] = register.registerIcon(getUnlocalizedName()+"left");
		icons[5] = register.registerIcon(getUnlocalizedName()+"right");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int blockSide, int blockMetadataIcon)
	{
		if(blockMetadataIcon != 0)
		{
			System.out.println("Invalid metadata for Cobblestone Road.");
		}
		if(blockSide < 0 || blockSide > 5)
		{
			System.out.println(blockMetadataIcon + " is an invalid side for any kind of block whatsoever. "
					+ "What were you thinking?");
		}
		return icons[blockSide];
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "sao:cobbleroad";
	}
}
