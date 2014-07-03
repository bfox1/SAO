package net.bfox1.sao.Item;

import net.bfox1.sao.help.ReferenceHelper;
import net.bfox1.sao.material.SToolMaterial;
import net.minecraft.item.Item;

/**
 * @author bfox1
 *
 */
public class SItem {

		public static Item Elucidator;
		public static Item Elucidator_Powered;
		
		public static Item AnnealBlade;
		public static Item AnnealBladePowered;
		
		public static Item BossKillToken;
		public static Item HeartOfKobold;

		
		
		public static void init()
		{
			Elucidator = new Elucidator(SToolMaterial.Elucidator);
			Elucidator_Powered = new Elucidator_Powered(SToolMaterial.Elucidator);
			
			AnnealBlade = new AnnealBlade(SToolMaterial.Elucidator);
			AnnealBlade = new AnnealBladePowered(SToolMaterial.Elucidator);
			
			BossKillToken = new BossKillToken();
			HeartOfKobold = new HeartOfKobold();

		}
		
		public static void registerInit()
		{
			ReferenceHelper.registerItem(Elucidator);
			ReferenceHelper.registerItem(Elucidator_Powered);
			ReferenceHelper.registerItem(BossKillToken);
			ReferenceHelper.registerItem(HeartOfKobold);
			ReferenceHelper.registerItem(AnnealBlade);
			//ReferenceHelper.registerItem(AneealBlockPowered);
		}
}
