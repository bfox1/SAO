package net.teamsao.mcsao.item;

import net.minecraft.item.Item;
import net.teamsao.mcsao.block.ItemBlockDungeonStone;
import net.teamsao.mcsao.block.SBlock;
import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.item.armor.COM_Body;
import net.teamsao.mcsao.item.armor.COM_Boots;
import net.teamsao.mcsao.item.armor.COM_Leggings;
import net.teamsao.mcsao.item.swords.*;
import net.teamsao.mcsao.material.SArmorMaterial;
import net.teamsao.mcsao.material.SToolMaterial;

/**
 * @author bfox1
 * 
 * Be sure after Initializing the Item, To Register It down below :)
 *
 */
public class SItem {

	//Swords


		public static Item Elucidator = new Elucidator(SToolMaterial.Elucidator);
		public static Item Elucidator_Powered = new Elucidator_Powered(SToolMaterial.Elucidator);
		
		public static Item AnnealBlade = new AnnealBlade(SToolMaterial.AnnealBlade);
		public static Item AnnealBladePowered = new AnnealBladePowered(SToolMaterial.AnnealBlade);
		
		public static Item DarkRepulser = new DarkRepulser(SToolMaterial.DarkRepulser);
		public static Item DarkRepulserPowered = new DarkRepulserPowered(SToolMaterial.DarkRepulser);
		
		public static Item EbonDagger = new EbonDagger(SToolMaterial.EbonDagger);
		public static Item EbonDaggerPowered = new EbonDaggerPowered(SToolMaterial.EbonDagger);
		
		public static Item GuiltyThorn = new GuiltyThorn(SToolMaterial.GuiltyThorn);
		public static Item GuiltyThornPowered = new GuiltyThornPowered(SToolMaterial.GuiltyThorn);
		
		public static Item Karakurenai = new Karakurenai(SToolMaterial.Karakurenai);
		public static Item KarakurenaiPowered = new KarakurenaiPowered(SToolMaterial.Karakurenai);
		
		public static Item LambentLight = new LambentLight(SToolMaterial.LambentLight);
		public static Item LambentLightPowered = new LambentLightPowered(SToolMaterial.LambentLight);
		
		public static Item LiberatorShield;
		public static Item LiberatorShieldPowered;
		
		public static Item LiberatorSword;
		public static Item LiberatorSwordPowered;
		
		public static Item MateChopper = new MateChopper(SToolMaterial.MateChopper);
		public static Item MateChopperPowered = new MateChopperPowered(SToolMaterial.MateChopper);
		
		public static Item ObjectEraser = new ObjectEraser(SToolMaterial.ObjectEraser);
		
		public static Item ShadowDagger = new ShadowDagger(SToolMaterial.ShadowDagger);
        public static Item ShadowDaggerPowered = new ShadowDaggerPowered(SToolMaterial.ShadowDagger);
		
		public static Item TyrantDragon;
        public static Item TyrantDragonPowered;

    //ItemBlocks
		public static Item DungeonStoneItem = new ItemBlockDungeonStone(SBlock.DungeonStone);


	//Items
		public static Item BossKillToken = new BossKillToken();
		public static Item HeartOfKobold = new HeartOfKobold();
		public static Item CrystalliteIngot = new CrystalliteIngot();
		public static Item NerveGear = new NerveGear(SArmorMaterial.nerveGear, 0, "NerveGear");
		
		public static Item TeleportCrystal = new TeleportCrystal();
		public static Item AntidoteCrystal = new AntidoteCrystal();1

	//Armor
        public static Item CoatOfMidnightBody = new COM_Body(SArmorMaterial.coatOfMidnight, 1, "CoatOfMidnightBody");
        public static Item CoatOfMidnightLeggings = new COM_Leggings(SArmorMaterial.coatOfMidnight, 2, "CoatOfMidnightLeggings");
        public static Item CoatOfMidnightBoots = new COM_Boots(SArmorMaterial.coatOfMidnight, 3, "CoatOfMidnightBoots");

    //Food
        public static Item ragoutRabbitMeat = new RabbitMeat(10, 15.0F, false, "RagoutRabbitMeat");
        public static SItemFood ScavengerToadMeat = new ScavengerToadMeat(3, 4.0F, false, "ScavengerToadMeat");


		

		public static void registerInit()
		{
  
			/*
			 * Drad, is there a way to make this simpler? To perhaps put all these in an Array? 
			 * 
			 * Chris: Done and done.
			 */

	
			Item[] items = { BossKillToken, HeartOfKobold, CrystalliteIngot, NerveGear,AntidoteCrystal,
                            TeleportCrystal, CoatOfMidnightBody, CoatOfMidnightLeggings, CoatOfMidnightBoots,
                            ObjectEraser};

            Item[] foods = {ragoutRabbitMeat, ScavengerToadMeat};

            Item[] swords = {Elucidator, Elucidator_Powered, AnnealBlade, AnnealBladePowered, DarkRepulser, DarkRepulserPowered,
                                GuiltyThorn, GuiltyThornPowered,LambentLight, LambentLightPowered,
                                EbonDagger, EbonDaggerPowered, MateChopper, MateChopperPowered, Karakurenai, KarakurenaiPowered,
                                ShadowDagger, ShadowDaggerPowered};
			
			for (int i = 0; i <items.length; i++) {
				ReferenceHelper.registerItem(items[i]);
			}
            for(int i = 0; i < foods.length; i++){
                ReferenceHelper.registerItem(foods[i]);
            }

            for(int i = 0; i < swords.length; i++){
                ReferenceHelper.registerItem(swords[i]);
            }
			


		}
		
		
		
		


}