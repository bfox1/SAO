package net.bfox1.sao.proxy;

import java.io.File;

import net.bfox1.sao.entity.EntityKoboldTest;
import net.bfox1.sao.entity.RenderEntityKoboldTest;
import net.bfox1.sao.help.Reference;
import net.bfox1.sao.model.KoboldTest;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ClientProxy extends CommonProxy  {

	@Override
	public void initClientConfig(File configFile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerTileEntities() {
		// TODO Auto-generated method stub
		
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
	}

	@Override
	public void registerGlobalEntity() {
		int idKobold = 0;
		int redColor = (255 << 16);
		int orangeColor = (255 << 16) + (200 << 8);
		
		EntityRegistry.registerModEntity(EntityKoboldTest.class, "KoboldTest", idKobold, Reference.MODID, 80, 3, true);
		EntityRegistry.registerGlobalEntityID(EntityKoboldTest.class, "KoboldTest", idKobold, redColor, orangeColor);
		LanguageRegistry.instance().addStringLocalization("entity.KoboldTest.name", "en_US", "Kobold Test");
	}

}
