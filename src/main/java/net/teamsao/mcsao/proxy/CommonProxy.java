package net.teamsao.mcsao.proxy;

import cpw.mods.fml.common.registry.EntityRegistry;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.teamsao.mcsao.handler.SaoEventHandler;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.world.DimensionId;
import net.teamsao.mcsao.world.SAOWorldProvider;
import net.teamsao.mcsao.entity.*;

import java.util.HashMap;
import java.util.Map;

/*
    ANY Methods you add here from the Sproxy class will not be needed in Client/Server proxy Classes.
    Only Override these methods IF you need to make a special change to the Client but no the server.
    Registering the Dimension here will tell the Minecraft that It needs to be applied to Both Client and Server
    If its rendering or a config related registration, it will go in either Client or Server depending on
    What the purpose of the Class is.
 */

public abstract class CommonProxy implements SProxy {

    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

    public void registerTileEntities()
    {}



	public void registerGlobalEntity()
    {
        EntityRegistry.registerGlobalEntityID(EntityKoboldTest.class, "KoboldTest", EntityRegistry.findGlobalUniqueEntityId(), EntitySAO.redColor, EntitySAO.orangeColor);
        EntityRegistry.registerGlobalEntityID(EntityBoar.class, "Boar", EntityRegistry.findGlobalUniqueEntityId(), EntitySAO.redColor, EntitySAO.blueColor);
	}


    public void registerDimension() {
        DimensionManager.registerProviderType(DimensionId.SAO_DIMENSION_ID, SAOWorldProvider.class, false);
        DimensionManager.registerDimension(DimensionId.SAO_DIMENSION_ID, DimensionId.SAO_DIMENSION_ID);
    }


    public void addChestLoot()
    {
        ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST).addItem(new WeightedRandomChestContent(new ItemStack(SAOItems.CDSAO), 1,6, 010));
    }

    public static void storeEntityData(String name, NBTTagCompound storage)
    {
        extendedEntityData.put(name, storage);
    }
    public static NBTTagCompound getEntityData(String name)
    {
        return extendedEntityData.remove(name);
    }
    public static NBTTagCompound checkEntityData(String name)
    {
        return extendedEntityData.get(name);
    }

    @Override
    public void registerEventHandlers(){
        MinecraftForge.EVENT_BUS.register(new SaoEventHandler());
    }

}
