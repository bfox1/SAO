package net.teamsao.mcsao.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.block.ItemBlockDungeonStone;
import net.teamsao.mcsao.block.SBlock;
import net.teamsao.mcsao.help.ReferenceHelper;
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
		
		public static Item Karakurenai;
		public static Item KarakurenaiPowered;
		
		public static Item LambentLight = new LambentLight(SToolMaterial.LambentLight);
		public static Item LambentLightPowered = new LambentLightPowered(SToolMaterial.LambentLight);
		
		public static Item LiberatorShield;
		public static Item LiberatorShieldPowered;
		
		public static Item LiberatorSword;
		public static Item LiberatorSwordPowered;
		
		public static Item MateChopper = new MateChopper(SToolMaterial.MateChopper);
		public static Item MateChopperPowered = new MateChopperPowered(SToolMaterial.MateChopper);
		
		public static Item RingofAngelsWhisper;
		
		public static Item ShadowDagger;
		
		public static Item TyrantDragon;
		
		public static Item DungeonStoneItem = new ItemBlockDungeonStone(SBlock.DungeonStone);
		
		
	//Items
		public static Item BossKillToken = new BossKillToken();
		public static Item HeartOfKobold = new HeartOfKobold();
		public static Item CrystalliteIngot = new CrystalliteIngot();
		public static Item NerveGear = new NerveGear(SArmorMaterial.nerveGear, 0, "NerveGear");
		
		public static Item TeleportCrystal = new TeleportCrystal();
		public static Item AntidoteCrystal;

		

		

		
		
		public static void init()
		{
		//	Elucidator = new Elucidator(SToolMaterial.Elucidator);
		//	Elucidator_Powered = new Elucidator_Powered(SToolMaterial.Elucidator);
			
		//	AnnealBlade = new AnnealBlade(SToolMaterial.AnnealBlade);
		//	AnnealBladePowered = new AnnealBladePowered(SToolMaterial.AnnealBlade);
			
		//	DarkRepulser = new DarkRepulser(SToolMaterial.DarkRepulser);
			
		//	BossKillToken = new BossKillToken();
		//	HeartOfKobold = new HeartOfKobold();

		}
		

		public static void registerInit()
		{

			/*
			 * Drad, is there a way to make this simpler? To perhaps put all these in an Array? 
			 * 
			 * Chris: Done and done.
			 */

	
			Item[] items = {Elucidator, Elucidator_Powered, AnnealBlade, AnnealBladePowered, DarkRepulser, DarkRepulserPowered, GuiltyThorn,
							GuiltyThornPowered, BossKillToken, HeartOfKobold, CrystalliteIngot, NerveGear, LambentLight, LambentLightPowered,
							EbonDagger, EbonDaggerPowered, MateChopper, MateChopperPowered};
			
			for (int i = 0; i < items.length; i++) {
				ReferenceHelper.registerItem(items[i]);
			}
			


		}
		
		
		
		


}