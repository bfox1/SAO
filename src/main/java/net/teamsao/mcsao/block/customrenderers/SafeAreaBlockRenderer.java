package net.teamsao.mcsao.block.customrenderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.teamsao.mcsao.proxy.ClientProxy;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class SafeAreaBlockRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId,
			RenderBlocks renderer)
	{
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer)
	{
		if(ClientProxy.renderPass == 1)
		{
			drawCube(block, x, y, z);
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
		return false;
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
	public void drawCube(Block block, int x, int y, int z)
	{
		Tessellator t = Tessellator.instance;
		//t.startDrawingQuads();
		//t.setTranslation(x, y, z);
		
		IIcon icon = block.getIcon(0, 0);
		double uMin = icon.getMinU();
		double uMax = icon.getMaxU();
		double vMin = icon.getMinV();
		double vMax = icon.getMaxV();
		
		drawBothSides(t, (double)x, (double)y, (double)z, uMin, uMax, vMin, vMax);
		
		//t.draw();
	}
	
	private void drawBothSides(Tessellator t, double x, double y, double z, double uMin, double uMax, double vMin, double vMax)
	{
		//North-facing face.
		t.addVertexWithUV(x+1, y, z, uMax, vMax);
		t.addVertexWithUV(x+1, y+1, z, uMax, vMin);
		t.addVertexWithUV(x, y+1, z, uMin, vMin);
		t.addVertexWithUV(x, y, z, uMin, vMax);
		
		t.addVertexWithUV(x, y, z, uMin, vMax);
		t.addVertexWithUV(x, y+1, z, uMin, vMin);
		t.addVertexWithUV(x+1, y+1, z, uMax, vMin);
		t.addVertexWithUV(x+1, y, z, uMax, vMax);
		
		//East-facing face
		t.addVertexWithUV(x+1, y, z, uMax, vMax);
		t.addVertexWithUV(x+1, y+1, z, uMax, vMin);
		t.addVertexWithUV(x+1, y+1, z+1, uMin, vMin);
		t.addVertexWithUV(x+1, y, z+1, uMin, vMax);
		
		t.addVertexWithUV(x+1, y, z+1, uMin, vMax);
		t.addVertexWithUV(x+1, y+1, z+1, uMin, vMin);
		t.addVertexWithUV(x+1, y+1, z, uMax, vMin);
		t.addVertexWithUV(x+1, y, z, uMax, vMax);
		
		//South-facing face
		t.addVertexWithUV(x+1, y, z+1, uMax, vMax);
		t.addVertexWithUV(x+1, y+1, z+1, uMax, vMin);
		t.addVertexWithUV(x, y+1, z+1, uMin, vMin);
		t.addVertexWithUV(x, y, z+1, uMin, vMax);
		
		t.addVertexWithUV(x, y, z+1, uMin, vMax);
		t.addVertexWithUV(x, y+1, z+1, uMin, vMin);
		t.addVertexWithUV(x+1, y+1, z+1, uMax, vMin);
		t.addVertexWithUV(x+1, y, z+1, uMax, vMax);
		
		//West-facing face
		t.addVertexWithUV(x, y, z+1, uMax, vMax);
		t.addVertexWithUV(x, y+1, z+1, uMax, vMin);
		t.addVertexWithUV(x, y+1, z, uMin, vMin);
		t.addVertexWithUV(x, y, z, uMin, vMax);
		
		t.addVertexWithUV(x, y, z, uMin, vMax);
		t.addVertexWithUV(x, y+1, z, uMin, vMin);
		t.addVertexWithUV(x, y+1, z+1, uMax, vMin);
		t.addVertexWithUV(x, y, z+1, uMax, vMax);
		
		//Bottom face
		t.addVertexWithUV(x, y, z+1, uMax, vMax);
		t.addVertexWithUV(x, y, z, uMax, vMin);
		t.addVertexWithUV(x+1, y, z, uMin, vMin);
		t.addVertexWithUV(x+1, y, z+1, uMin, vMax);
		
		t.addVertexWithUV(x+1, y, z+1, uMin, vMax);
		t.addVertexWithUV(x+1, y, z, uMin, vMin);
		t.addVertexWithUV(x, y, z, uMax, vMin);
		t.addVertexWithUV(x, y, z+1, uMax, vMax);
		
		//Top face
		t.addVertexWithUV(x, y+1, z, uMax, vMax);
		t.addVertexWithUV(x, y+1, z+1, uMax, vMin);
		t.addVertexWithUV(x+1, y+1, z+1, uMin, vMin);
		t.addVertexWithUV(x+1, y+1, z, uMin, vMax);
		
		t.addVertexWithUV(x+1, y+1, z, uMin, vMax);
		t.addVertexWithUV(x+1, y+1, z+1, uMin, vMin);
		t.addVertexWithUV(x, y+1, z+1, uMax, vMin);
		t.addVertexWithUV(x, y+1, z, uMax, vMax);
	}

}
