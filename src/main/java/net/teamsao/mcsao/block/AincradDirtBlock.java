package net.teamsao.mcsao.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOBlocks;

import java.util.Random;

public class AincradDirtBlock extends BlockSAO
{
	@SideOnly(Side.CLIENT)
	private IIcon icon;
	
	public AincradDirtBlock()
	{
		super(Material.ground);
		this.setBlockName("aincraddirt");
		this.setBlockTextureName(ReferenceHelper.setBlockName(this));
		this.setStepSound(Block.soundTypeGravel);
        this.setTickRandomly(true);
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
		return "sao:aincraddirt";
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int blockSide, int blockMetadataIcon)
	{
		return icon;
	}

	
}
