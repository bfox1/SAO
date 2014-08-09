package net.teamsao.mcsao.proxy;

import java.io.File;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.DimensionManager;
import net.teamsao.mcsao.dimension.SAOWorldProvider;
import net.teamsao.mcsao.entity.*;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.lib.DimensionId;
import net.teamsao.mcsao.model.Illfang_the_Kobold_Lord;
import net.teamsao.mcsao.model.KoboldTest;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.teamsao.mcsao.model.boar;
import net.teamsao.mcsao.tileentity.TileEntityForgeStation;

public class ClientProxy extends CommonProxy  {

	@Override
	public void initClientConfig(File configFile) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void initRenderingAndTextures() {
        RenderingRegistry.registerEntityRenderingHandler(EntityKoboldTest.class, new RenderEntityKoboldTest(new Illfang_the_Kobold_Lord(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBoar.class, new RenderBoar(new boar(), 0.5F));
		
	}

	@Override
	public void registerEventHandlers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerKeybindings() {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void registerEntityLiving() {

    }




}
