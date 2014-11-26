package net.teamsao.mcsao.client.renderers.models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.helper.Reference;

@SideOnly(Side.CLIENT)
public class ModelCrystal {
    private IModelCustom modelCrystal;
    private ResourceLocation modelLocation;

    public ModelCrystal()
    {
        modelLocation = new ResourceLocation(Reference.MODID, "models/items/SAOCrystal.obj");
        modelCrystal = AdvancedModelLoader.loadModel(modelLocation);
    }

    public void render()
    {
        modelCrystal.renderAll();
    }
}
