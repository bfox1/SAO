package net.teamsao.mcsao.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.teamsao.mcsao.helper.Reference;
import net.minecraft.block.Block;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;

/**
 * @author bfox1
 * All Blocks will now extend This Class.
 *
 */
public class BlockSAO extends Block{

    public static boolean isBreakable = true;
	
    public BlockSAO(Material material) {
        super(Material.rock);
        if(isBreakable) {
            this.setBlockUnbreakable();
            this.setResistance(-1);
        } else {
            this.setHardness(1.0F);
            this.setResistance(5.0F);
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



}
