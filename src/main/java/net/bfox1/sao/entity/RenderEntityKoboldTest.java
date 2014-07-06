package net.bfox1.sao.entity;

import net.bfox1.sao.help.Reference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntityKoboldTest extends RenderLiving {
	
    private static final ResourceLocation KoboldTest = new ResourceLocation(Reference.MODID + ":textures/entity/koboldtest.png");
    private int field_77068_a;
    //private static final String __OBFID = "CL_00000980";

    public RenderEntityKoboldTest(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return KoboldTest;
    }



}
