package net.teamsao.mcsao.block.customrenderers;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.teamsao.mcsao.proxy.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class SafeAreaBlockRenderer implements ISimpleBlockRenderingHandler
{

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer)
	{
		if(ClientProxy.renderPass == 1)
		{
			boolean[] renderFaces = new boolean[]
					{
					block.shouldSideBeRendered(world, x, y-1, z, 0),
					block.shouldSideBeRendered(world, x, y+1, z, 1),
					block.shouldSideBeRendered(world, x, y, z-1, 2),
					block.shouldSideBeRendered(world, x, y, z+1, 3),
					block.shouldSideBeRendered(world, x-1, y, z, 4),
					block.shouldSideBeRendered(world, x+1, y, z, 5)
					};
			if(!renderFaces[0] && !renderFaces[1] && !renderFaces[2] 
					&& !renderFaces[3] && !renderFaces[4] && !renderFaces[5])
			{
				return false;
			}
			Tessellator tessellator = Tessellator.instance;

			int color = block.colorMultiplier(world, x, y, z);
			float red = (color >> 16 & 255) / 255.0F;
			float green = (color >> 8 & 255) / 255.0F;
			float blue = (color & 255) / 255.0F;

			tessellator.setBrightness(block.getMixedBrightnessForBlock(world, x, y, z));
			tessellator.setColorOpaque_F(red, green, blue);
			drawCube(tessellator, block, x, y, z, renderFaces);
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return ClientProxy.safeAreaBlockRenderType;
	}

	/**
	 * Draw all sides of the cube twice. Once inside and once outside. See if that, combined with the detection of whether to render a side,
	 * actually does what we want.
	 * @param block
	 * @param x
	 * @param y
	 * @param z
	 */
	public void drawCube(Tessellator tessellator, Block block, int x, int y, int z, boolean[] renderFaces)
	{
		//t.startDrawingQuads();
		//t.setTranslation(x, y, z);

		IIcon icon = block.getIcon(0, 0);
		double uMin = icon.getMinU();
		double uMax = icon.getMaxU();
		double vMin = icon.getMinV();
		double vMax = icon.getMaxV();

		double offset = 0.000625D;

		if(renderFaces[0])
		{
			drawBottom(tessellator, (double)x, (double)y, (double)z, uMin, uMax, vMin, vMax, offset);
		}
		if(renderFaces[1])
		{
			drawTop(tessellator, (double)x, (double)y, (double)z, uMin, uMax, vMin, vMax, offset);
		}
		if(renderFaces[2])
		{
			drawNorth(tessellator, (double)x, (double)y, (double)z, uMin, uMax, vMin, vMax, offset);
		}
		if(renderFaces[3])
		{
			drawSouth(tessellator, (double)x, (double)y, (double)z, uMin, uMax, vMin, vMax, offset);
		}
		if(renderFaces[4])
		{
			drawWest(tessellator, (double)x, (double)y, (double)z, uMin, uMax, vMin, vMax, offset);
		}
		if(renderFaces[5])
		{
			drawEast(tessellator, (double)x, (double)y, (double)z, uMin, uMax, vMin, vMax, offset);
		}

		//t.draw();
	}

	private void drawNorth(Tessellator t, double x, double y, double z, double uMin, double uMax, double vMin, double vMax, double offset)
	{
		//Outside-North face.
		t.addVertexWithUV(x+1, y, z, uMax, vMax);
		t.addVertexWithUV(x+1, y+1, z, uMax, vMin);
		t.addVertexWithUV(x, y+1, z, uMin, vMin);
		t.addVertexWithUV(x, y, z, uMin, vMax);
		//Inside-North face
		t.addVertexWithUV(x, y, z+offset, uMin, vMax);
		t.addVertexWithUV(x, y+1, z+offset, uMin, vMin);
		t.addVertexWithUV(x+1, y+1, z+offset, uMax, vMin);
		t.addVertexWithUV(x+1, y, z+offset, uMax, vMax);
	}

	private void drawSouth(Tessellator t, double x, double y, double z, double uMin, double uMax, double vMin, double vMax, double offset)
	{
		//Outside-South face
		t.addVertexWithUV(x+1, y, z+1, uMax, vMax);
		t.addVertexWithUV(x+1, y+1, z+1, uMax, vMin);
		t.addVertexWithUV(x, y+1, z+1, uMin, vMin);
		t.addVertexWithUV(x, y, z+1, uMin, vMax);
		//Inside-South face
		t.addVertexWithUV(x, y, z+1-offset, uMin, vMax);
		t.addVertexWithUV(x, y+1, z+1-offset, uMin, vMin);
		t.addVertexWithUV(x+1, y+1, z+1-offset, uMax, vMin);
		t.addVertexWithUV(x+1, y, z+1-offset, uMax, vMax);
	}

	private void drawEast(Tessellator t, double x, double y, double z, double uMin, double uMax, double vMin, double vMax, double offset)
	{
		//Outside-East face
		t.addVertexWithUV(x+1, y, z, uMax, vMax);
		t.addVertexWithUV(x+1, y+1, z, uMax, vMin);
		t.addVertexWithUV(x+1, y+1, z+1, uMin, vMin);
		t.addVertexWithUV(x+1, y, z+1, uMin, vMax);
		//Inside-East face
		t.addVertexWithUV(x+1-offset, y, z+1, uMin, vMax);
		t.addVertexWithUV(x+1-offset, y+1, z+1, uMin, vMin);
		t.addVertexWithUV(x+1-offset, y+1, z, uMax, vMin);
		t.addVertexWithUV(x+1-offset, y, z, uMax, vMax);
	}

	private void drawWest(Tessellator t, double x, double y, double z, double uMin, double uMax, double vMin, double vMax, double offset)
	{
		//Outside-West face
		t.addVertexWithUV(x, y, z+1, uMax, vMax);
		t.addVertexWithUV(x, y+1, z+1, uMax, vMin);
		t.addVertexWithUV(x, y+1, z, uMin, vMin);
		t.addVertexWithUV(x, y, z, uMin, vMax);
		//Inside-West face
		t.addVertexWithUV(x+offset, y, z, uMin, vMax);
		t.addVertexWithUV(x+offset, y+1, z, uMin, vMin);
		t.addVertexWithUV(x+offset, y+1, z+1, uMax, vMin);
		t.addVertexWithUV(x+offset, y, z+1, uMax, vMax);
	}

	private void drawTop(Tessellator t, double x, double y, double z, double uMin, double uMax, double vMin, double vMax, double offset)
	{
		//Outside-Top face
		t.addVertexWithUV(x, y+1, z, uMax, vMax);
		t.addVertexWithUV(x, y+1, z+1, uMax, vMin);
		t.addVertexWithUV(x+1, y+1, z+1, uMin, vMin);
		t.addVertexWithUV(x+1, y+1, z, uMin, vMax);
		//Inside-Top face
		t.addVertexWithUV(x+1, y+1-offset, z, uMin, vMax);
		t.addVertexWithUV(x+1, y+1-offset, z+1, uMin, vMin);
		t.addVertexWithUV(x, y+1-offset, z+1, uMax, vMin);
		t.addVertexWithUV(x, y+1-offset, z, uMax, vMax);
	}

	private void drawBottom(Tessellator t, double x, double y, double z, double uMin, double uMax, double vMin, double vMax, double offset)
	{
		//Outside-Bottom face
		t.addVertexWithUV(x, y, z+1, uMax, vMax);
		t.addVertexWithUV(x, y, z, uMax, vMin);
		t.addVertexWithUV(x+1, y, z, uMin, vMin);
		t.addVertexWithUV(x+1, y, z+1, uMin, vMax);
		//Inside-Bottom face
		t.addVertexWithUV(x+1, y+offset, z+1, uMin, vMax);
		t.addVertexWithUV(x+1, y+offset, z, uMin, vMin);
		t.addVertexWithUV(x, y+offset, z, uMax, vMin);
		t.addVertexWithUV(x, y+offset, z+1, uMax, vMax);
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderblocks)
	{
		Tessellator tessellator = Tessellator.instance;
		block.setBlockBoundsForItemRender();
        renderblocks.setRenderBoundsFromBlock(block);
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 0, metadata));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 1, metadata));
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 2, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 3, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 4, metadata));
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 5, metadata));
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

}
