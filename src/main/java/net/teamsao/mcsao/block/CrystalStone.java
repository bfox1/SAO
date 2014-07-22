package net.teamsao.mcsao.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SAOTabsManager;

import java.util.List;

/**
 * Created by bfox1 on 7/20/2014.
 */
public class CrystalStone extends Block{

    public final static String[] subBlocks = {"SmoothStone", "BrickedStone", "ChiseledStone", "AlterStone", "EdgedStone"};


    public CrystalStone() {
        super(Material.anvil);
        this.setCreativeTab(SAOTabsManager.saoBlocks);
        this.setBlockName("CrystalStone");
        this.setBlockUnbreakable();
    }





    @Override
    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        icons = new IIcon[5];

        for(int i = 0; i < icons.length; i++)
        {
            icons[i] = par1IconRegister.registerIcon(ReferenceHelper.setBlockName(this) + i);
            System.out.println(ReferenceHelper.setBlockName(this) + i);
        }
    }
	/*
	 * (non-Javadoc)
	 * @see net.minecraft.block.Block#getIcon(int, int)
	 * For the Texturers, The Case indicated the MetaData Number of the block
	 * This is only useful for texturers so for ex. case0: returns icons[0]; to
	 * translate that to a Name, simple add DungeonBlock0.png and add that to the Blocks folder
	 * in textures.
	 *
	 * For Item Name, @see net.teamsao.mcsao.block.itemBlockDungeonStone
	 */

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int blockSide, int blockMetadataIcon)
    {
        if(blockMetadataIcon >= 0 && blockMetadataIcon < icons.length)
        {
            return icons[blockMetadataIcon];
        }
        else
        {
            System.out.println("Invalid metadata for " + this.getUnlocalizedName());
            return icons[0];
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for(int i = 0; i < 4; i++)
        {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }
}
