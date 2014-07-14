package net.teamsao.mcsao.proxy;

import java.io.File;

import cpw.mods.fml.common.registry.GameRegistry;
import net.teamsao.mcsao.entity.*;
import net.teamsao.mcsao.help.Reference;
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
	public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityForgeStation.class, Reference.MODID);
		
	}

	@Override
	public void initRenderingAndTextures() {
		// TODO Auto-generated method stub
		
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
		RenderingRegistry.registerEntityRenderingHandler(EntityKoboldTest.class, new RenderEntityKoboldTest(new KoboldTest(), 0.5F));
       RenderingRegistry.registerEntityRenderingHandler(EntityBoar.class, new RenderBoar(new boar(), 0.5F));
	}

	@Override
	public void registerGlobalEntity() {
		
		EntityRegistry.registerGlobalEntityID(EntityKoboldTest.class, "KoboldTest", SEntity.idKobold, SEntity.redColor, SEntity.orangeColor);
        EntityRegistry.registerGlobalEntityID(EntityBoar.class, "Boar", 32, SEntity.redColor, SEntity.blueColor);
	}

}
