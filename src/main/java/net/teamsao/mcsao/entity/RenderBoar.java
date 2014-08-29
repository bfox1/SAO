package net.teamsao.mcsao.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.teamsao.mcsao.helper.Reference;

/**
 * Created by bfox1 on 7/10/2014.
 */
public class RenderBoar extends RenderLiving{


    private static final ResourceLocation boar = new ResourceLocation(Reference.MODID + ":textures/entity/boar.png");
    private int field_77068_a;
    //private static final String __OBFID = "CL_00000980";

    public RenderBoar(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return boar;
    }
}
