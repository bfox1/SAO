package net.teamsao.mcsao.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.teamsao.mcsao.proxy.ClientProxy;

/**
 * Created by bfox1 on 8/25/2014.
 */
public class LuminescenceLight extends BlockSAO {
    public LuminescenceLight() {
        super(Material.redstoneLight);
        this.setLightLevel(1.0F);
        this.setHardness(1.0F);
        this.setLightOpacity(15);
        this.setBlockName("LuminescenceLight");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean canRenderInPass(int pass)
    {
        ClientProxy.renderPass = pass;
        if(pass == 0)
        {
            return false;
        }
        return true;
    }

    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }

}
