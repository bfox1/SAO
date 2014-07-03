package net.bfox1.sao.Item;

import net.bfox1.sao.help.ReferenceHelper;
import net.bfox1.sao.material.SToolMaterial;
import net.minecraft.item.Item;

public class SItem {

		public static Item Elucidator;
		public static Item Elucidator_Powered;
		
		public static Item BossKillToken;
		public static Item HeartOfKobold;

		
		
		public static void init()
		{
			Elucidator = new Elucidator(SToolMaterial.Elucidator);
			Elucidator_Powered = new Elucidator_Powered(SToolMaterial.Elucidator);
			
			BossKillToken = new BossKillToken();
			HeartOfKobold = new HeartOfKobold();

		}
		
		public static void registerInit()
		{
			ReferenceHelper.registerItem(Elucidator);
			ReferenceHelper.registerItem(Elucidator_Powered);
			ReferenceHelper.registerItem(BossKillToken);
			ReferenceHelper.registerItem(HeartOfKobold);
		}
}
