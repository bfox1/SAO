package net.teamsao.mcsao.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

//Created by SirPwn on 8/6/14
public class SafeAreaBlock extends BlockSAO {
	
	public SafeAreaBlock() {
		super(Material.rock);
		this.setBlockName("BlockSafeArea");
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public boolean func_149686_d()
	{
	  return false;
	}
	
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
	{
	  if (((par7Entity instanceof EntityPlayer)) && 
	    (!par7Entity.isSneaking())) {
	    return;
	  }

	  super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
	{
	  return AxisAlignedBB.getBoundingBox(par2, par3, par4, par2 + 1, par3 + 1, par4 + 1);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
	  return par1IBlockAccess.getBlock(par2, par3, par4) != this;
	}
	
	public int damageDropped(int meta)
	{
	  return meta;
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
		return AxisAlignedBB.getBoundingBox(x, y, z, x+1, y+1, z+1);
    }

	public boolean isBlockSolid(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	  {
	    return true;
	  }
}
