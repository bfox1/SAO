package net.teamsao.mcsao.client.renderers.items;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.teamsao.mcsao.client.renderers.models.ModelCrystal;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.init.SAOItems;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemRendererCrystal implements IItemRenderer{

    private final ModelCrystal modelCrystal;
    private ResourceLocation resourceLocation;

    public ItemRendererCrystal()
    {
        modelCrystal = new ModelCrystal();
    }

    //The types are just a way to differentiate between them. You can use a better way if you want.
    public ItemRendererCrystal(int type)
    {
        modelCrystal = new ModelCrystal();
        if(type == 0)
        resourceLocation = new ResourceLocation(Reference.MODID, "models/items/TeleportCrystal.png");
        else if(type == 1)
        resourceLocation = new ResourceLocation(Reference.MODID, "models/items/HealingCrystal.png");
        else if(type == 2)
        resourceLocation = new ResourceLocation(Reference.MODID, "models/items/AntidoteCrystal.png");
    }

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType itemRenderType)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data)
    {
        switch (itemRenderType)
        {
            case ENTITY:
            {
                renderCrystal(0, 0, 0, 1);
                return;
            }
            case EQUIPPED:
            {
                renderCrystal(1.925f, 1.4f, 1.3f, 1);
                return;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                renderCrystal(1.925f, 1.4f, 1.3f, 0);
                return;
            }
            case INVENTORY:
            {
                renderCrystal(0, -1.5f, 0, 1);
                return;
            }
            default:
            {
            }
        }
    }

    private void renderCrystal(float x, float y, float z, int rot)
    {
        GL11.glEnable(GL11.GL_ALPHA_TEST);

        GL11.glPushMatrix();

        // Scale, Translate, Rotate
        GL11.glScalef(0.3F, 0.3F, 0.3F);
        GL11.glTranslatef(x, y, z);
        if(rot == 0) {
            GL11.glRotatef(-90, 0, 1F, 0);
        }
        else{
            GL11.glRotatef(90, 0, 1F, 0);
        }

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(resourceLocation);

        // Render
        modelCrystal.render();

        GL11.glPopMatrix();

    }
}
