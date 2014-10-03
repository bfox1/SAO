package net.teamsao.mcsao.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.teamsao.mcsao.init.SAOBlocks;

public class AincradCobbleSlab extends BlockSlab
{
	private Block material;
	private boolean isDoubleSlab;

	public AincradCobbleSlab(Block block, boolean isDoubleSlab)
	{
		super(isDoubleSlab, block.getMaterial());
		this.setLightOpacity(0);
		this.material = block;
		this.isDoubleSlab = isDoubleSlab;
		this.setBlockUnbreakable();
        this.setResistance(30000000.0F);
	}
    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }

	@Override
	public String func_150002_b(int damageValue)
	{
		return getUnlocalizedName();
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		boolean superSideSolid = super.shouldSideBeRendered(world, x, y, z, side);
		if(isDoubleSlab)
		{
			return superSideSolid;
		}
		else if(side != 0 && side != 1 && !superSideSolid)
		{
			return false;
		}
		else
		{
			int modX = x + Facing.offsetsXForSide[Facing.oppositeSide[side]];
            int modY = y + Facing.offsetsYForSide[Facing.oppositeSide[side]];
            int modZ = z + Facing.offsetsZForSide[Facing.oppositeSide[side]];
            Block block = world.getBlock(modX, modY, modZ);
            int metadata = world.getBlockMetadata(modX, modY, modZ);
            boolean oppositeTop = (metadata & 8) == 8;
            boolean oppositeBottom = (metadata & 8) == 0;
            boolean isSlab = block instanceof BlockSlab;
            boolean vertSide = side > -1 && side < 6;
			return vertSide && ((side == 0 && oppositeBottom) || (side == 1 && oppositeTop)) && isSlab ? true : superSideSolid;
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Item getItem(World world, int x, int y, int z)
	{
		return isDoubleSlab ? Item.getItemFromBlock(SAOBlocks.AincradCobbleDoubleSlab) : Item.getItemFromBlock(SAOBlocks.AincradCobbleHalfSlab);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int blockSide, int blockMetadataIcon)
	{
		return material.getIcon(1, 0);
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "sao:"+(isDoubleSlab ? "cobbleroaddoubleslab" : "cobbleroadhalfslab");
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) 
	{
		Block block = world.getBlock(x, y, z);
		int metadata = world.getBlockMetadata(x, y, z);
		System.out.println(block.getLocalizedName()+" | metadata: "+metadata);
	}
}
