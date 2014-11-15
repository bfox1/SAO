package net.teamsao.mcsao.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOBlocks;

import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.UP;

public class AincradGrassBlock extends BlockSAO {


    @SideOnly(Side.CLIENT)
    private IIcon topIcon, sideIcon;

    public AincradGrassBlock() {
        super(Material.grass);
        this.setBlockName("cobbleroad");
        this.setBlockTextureName(ReferenceHelper.setBlockName(this));
        this.setStepSound(Block.soundTypeGrass);
        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        if(!world.isRemote) {
            if (!world.isAirBlock(x, y + 1, z)) {

                world.setBlockToAir(x, y, z);
                world.setBlock(x, y, z, SAOBlocks.AincradDirtBlock, 0, 0);
            }

        }
    }



    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) {
        topIcon = register.registerIcon(getUnlocalizedName() + "_top");
        sideIcon = register.registerIcon(getUnlocalizedName() + "_side");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int blockSide, int blockMetadataIcon) {
        if (blockSide == 0) {
            return SAOBlocks.AincradDirtBlock.getIcon(0, 0);
        } else if (blockSide == 1) {
            return topIcon;
        } else {
            return sideIcon;
        }
    }

    @Override
    public String getUnlocalizedName() {
        return "sao:aincradgrass";
    }


}
