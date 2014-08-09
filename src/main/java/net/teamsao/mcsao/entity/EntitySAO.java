package net.teamsao.mcsao.entity;

import cpw.mods.fml.common.registry.EntityRegistry;

/**
 * Created by bfox1 on 7/10/2014.
 */
public class EntitySAO {

    public static int idKobold;
    public static int idBoar;

    public static int redColor;
    public static int orangeColor;
    public static int blueColor;


    //EntityIDs
    public static void registerIds() {


         idKobold = EntityRegistry.findGlobalUniqueEntityId();
      //   idBoar = EntityRegistry.findGlobalUniqueEntityId();


    //Colors
         redColor = (255 << 16);
         orangeColor = (255 << 16) + (200 << 8);
         blueColor = 255;
}
}
