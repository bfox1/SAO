package net.bfox1.sao.Item;

import net.bfox1.sao.help.ReferenceHelper;
import net.bfox1.sao.material.SToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

/**
 * @author bfox1
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
		
		public static Item LambentLight;
		public static Item LambentLightPowered;
		
		public static Item Liberator;
		public static Item LiberatorPowered;
		
		public static Item MateChopper;
		
		public static Item RingofAngelsWhisper;
		
		public static Item ShadowDagger;
		
		public static Item TyrantDragon;
		
		
	//Items
		public static Item BossKillToken = new BossKillToken();
		public static Item HeartOfKobold = new HeartOfKobold();
		public static Item CrystalliteIngot = new CrystalliteIngot();
		

		
		
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

		}
		


}
