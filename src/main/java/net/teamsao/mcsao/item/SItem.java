package net.teamsao.mcsao.item;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.teamsao.mcsao.block.ItemBlockDungeonStone;
import net.teamsao.mcsao.block.SBlock;
import net.teamsao.mcsao.help.ReferenceHelper;
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
		
		public static Item EbonDagger;
		public static Item EbonDaggerPowered;
		
		public static Item GuiltyThorn = new GuiltyThorn(SToolMaterial.GuiltyThorn);
		public static Item GuiltyThornPowered = new GuiltyThornPowered(SToolMaterial.GuiltyThorn);
		
		public static Item Karakurenai;
		public static Item KarakurenaiPowered;
		
		public static Item LambentLight = new LambentLight(SToolMaterial.LambentLight);
		public static Item LambentLightPowered = new LambentLightPowered(SToolMaterial.LambentLight);
		
		public static Item Liberator;
		public static Item LiberatorPowered;
		
		public static Item MateChopper;
		public static Item MateChopperPowered;
		
		public static Item RingofAngelsWhisper;
		
		public static Item ShadowDagger;
		
		public static Item TyrantDragon;
		
		public static Item DungeonStoneItem = new ItemBlockDungeonStone(SBlock.DungeonStone);
		
		
	//Items
		public static Item BossKillToken = new BossKillToken();
		public static Item HeartOfKobold = new HeartOfKobold();
		public static Item CrystalliteIngot = new CrystalliteIngot();
		public static Item NerveGear = new NerveGear();
		
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
<<<<<<< HEAD
=======
			/**
			 * Drad, is there a way to make this simpler? To purhaps put all these in an Array? 
			 */
>>>>>>> b0f51881fee919206e2efae932155ddf7a3fe4bf
	
			ReferenceHelper.registerItem(Elucidator);
			ReferenceHelper.registerItem(Elucidator_Powered);
			ReferenceHelper.registerItem(AnnealBlade);
			ReferenceHelper.registerItem(AnnealBladePowered);
			ReferenceHelper.registerItem(DarkRepulser);
			ReferenceHelper.registerItem(DarkRepulserPowered);
			ReferenceHelper.registerItem(GuiltyThorn);
			ReferenceHelper.registerItem(GuiltyThornPowered);
			
			ReferenceHelper.registerItem(BossKillToken);
			ReferenceHelper.registerItem(HeartOfKobold);
			ReferenceHelper.registerItem(CrystalliteIngot);
			ReferenceHelper.registerItem(NerveGear);
			ReferenceHelper.registerItem(LambentLight);
			ReferenceHelper.registerItem(LambentLightPowered);
<<<<<<< HEAD
=======
			
			//ReferenceHelper.registerItem(DungeonStoneItem);
>>>>>>> b0f51881fee919206e2efae932155ddf7a3fe4bf

		}
		
		
		
		


}
