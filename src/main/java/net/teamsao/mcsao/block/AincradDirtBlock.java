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

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if(!world.isRemote) {
            if (world.blockExists(x, y + 1, z)) {

                world.setBlockToAir(x, y, z);
                world.setBlock(x, y, z, SAOBlocks.AincradGrassBlock, 0, 0);
            }
            int i1 = x + rand.nextInt(3) - 1;
            int j1 = y + rand.nextInt(5) - 3;
            int k1 = z + rand.nextInt(3) - 1;
            Block block = world.getBlock(i1, j1 + 1, k1);

            if (world.getBlock(i1, j1, k1) == Blocks.dirt && world.getBlockMetadata(i1, j1, k1) == 0
                    && world.getBlockLightValue(i1, j1 + 1, k1) >= 4
                    && world.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
            {
                world.setBlock(i1, j1, k1, SAOBlocks.AincradGrassBlock);
            }
        }
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
