package net.bfox1.sao.Item;

import net.bfox1.sao.help.ReferenceHelper;
import net.bfox1.sao.material.SToolMaterial;
import net.minecraft.item.Item;

public class SItem {

		public static Item ExampleSword;
		public static Item ExampleSword_Powered;
		
		public static Item BossKillToken;
		public static Item HeartOfKobold;
		
		public static Enum EnumColor;
		
		
		public static void init()
		{
			ExampleSword = new ExampleSword(SToolMaterial.ExampleToolMaterial);
			ExampleSword_Powered = new ExampleSword_Powered(SToolMaterial.ExampleToolMaterial);
			
			BossKillToken = new BossKillToken();
			HeartOfKobold = new HeartOfKobold();

		}
		
		public static void registerInit()
		{
			ReferenceHelper.registerItem(ExampleSword);
			ReferenceHelper.registerItem(ExampleSword_Powered);
			ReferenceHelper.registerItem(BossKillToken);
			ReferenceHelper.registerItem(HeartOfKobold);
		}
}
