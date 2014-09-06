package net.teamsao.mcsao.init;

import net.minecraft.item.Item;
import net.teamsao.mcsao.block.ItemBlockMetaData;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.item.*;
import net.teamsao.mcsao.item.ingots.*;
import net.teamsao.mcsao.item.armor.*;
import net.teamsao.mcsao.item.foods.*;
import net.teamsao.mcsao.item.items.*;
import net.teamsao.mcsao.item.hammers.*;
import net.teamsao.mcsao.item.items.SAOCD;
import net.teamsao.mcsao.item.potions.*;
import net.teamsao.mcsao.item.swords.*;
import net.teamsao.mcsao.material.SArmorMaterial;
import net.teamsao.mcsao.material.SToolMaterial;
import net.teamsao.mcsao.recipes.ForgeStationRecipes;

/**
 * Created by bfox1 on 7/23/2014.
 */
public class SAOItems {

    // Swords
    public static Item Elucidator = new Elucidator(SToolMaterial.Elucidator);
    public static Item ElucidatorPowered = new Elucidator_Powered(SToolMaterial.Elucidator);
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
    public static Item LiberatorShield = new LiberatorShield(SToolMaterial.LiberatorShield);
    public static Item LiberatorSword = new LiberatorSword(SToolMaterial.LiberatorSword);
    public static Item MateChopper = new MateChopper(SToolMaterial.MateChopper);
    public static Item MateChopperPowered = new MateChopperPowered(SToolMaterial.MateChopper);
    public static Item ObjectEraser = new ObjectEraser(SToolMaterial.ObjectEraser);
    public static Item ShadowDagger = new ShadowDagger(SToolMaterial.ShadowDagger);
    public static Item ShadowDaggerPowered = new ShadowDaggerPowered(SToolMaterial.ShadowDagger);
    public static Item TyrantDragon = new TyrantDragon(SToolMaterial.TyrantDragon);
    public static Item TyrantDragonPowered = new TyrantDragonPowered(SToolMaterial.TyrantDragon);

    public static Item KagemitsuG4;
    public static Item KagemitsuG4Powered;

    // Guns
    public static Item FnFiveSeven /* = new FnFiveSeven(SToolMaterial.FnFiveSeven)*/;
    public static Item PgmUltimaRatioHecateII /* = new PgmUltimaRatioHecateII(SToolMaterial.PgmUltimaRatioHecateII)*/;
    public static Item Type54BlackStar;
    
    //Hammers
    public static Item ZoringenHammer = new ZoringenHammer();

    // ItemBlocks
    public static Item DungeonStoneItem = new ItemBlockMetaData(SAOBlocks.DungeonStone);
    public static Item CrystalDungeonItem = new ItemBlockMetaData(SAOBlocks.CrystalStone);

    // Items
    public static Item BossKillToken = new BossKillToken();
    public static Item HeartOfKobold = new HeartOfKobold();
    public static Item NerveGear = new NerveGear(SArmorMaterial.nerveGear, 0, "NerveGear");
    public static Item TeleportCrystal = new TeleportCrystal();
    public static Item AntidoteCrystal = new AntidoteCrystal();
    public static Item TownTeleportCrystal = new TownTeleportCrystal();
    public static Item HealingCrystal = new HealingCrystal();
    public static Item CDSAO = new SAOCD();

    //Potions
    public static Item Elixer = new Elixir();
    public static Item EmptyVial = new EmptyVial();

    public static Item WeaponTypeSelector = new WeaponTypeSelector();
    public static Item SwordTypeSelector = new SwordTypeSelector();
    
    //Ingots
    public static Item CrystalliteIngot = new CrystalliteIngot();
    public static Item SteelIngot = new SteelIngot();
    
    //Armor
    public static Item CoatOfMidnightBody = new COM_Body(SArmorMaterial.coatOfMidnight, 1, "CoatOfMidnightBody");
    public static Item CoatOfMidnightLeggings = new COM_Leggings(SArmorMaterial.coatOfMidnight, 2, "CoatOfMidnightLeggings");
    public static Item CoatOfMidnightBoots = new COM_Boots(SArmorMaterial.coatOfMidnight, 3, "CoatOfMidnightBoots");
    public static Item debugHelmet = new Debug_Helmet(SArmorMaterial.debugMaterial, 0, "DebugHelmet");
    public static Item debugChestPlate = new Debug_ChestPlate(SArmorMaterial.debugMaterial,  1, "DebugChestPlate");
    public static Item debugLeggings = new Debug_Leggings(SArmorMaterial.debugMaterial,  2, "DebugLeggings");
    public static Item debugBoots = new Debug_Boots(SArmorMaterial.debugMaterial,  3, "DebugBoots");

    // Food
    public static Item ragoutRabbitMeat = new RabbitMeat(10, 15.0F, false, "ragoutmeat");
    public static Item RawRagoutRabbitMeat = new RawRabbitMeat(2, 2.0F, false, "rawragoutmeat");
    public static SItemFood ScavengerToadMeat = new ScavengerToadMeat(1, 1.0F, false, "toadmeat");
    public static SItemFood ScavengerCookedToadMeat = new ScavengerCookedToadMeat(3, 4.0F, false, "cookedtoadmeat");
    



    public static void registerInit() {

        Item[] items = { BossKillToken, HeartOfKobold, NerveGear,AntidoteCrystal,
        		TownTeleportCrystal, CoatOfMidnightBody, CoatOfMidnightLeggings, CoatOfMidnightBoots,
                ObjectEraser, debugHelmet, debugChestPlate, debugLeggings, debugBoots, CDSAO, Elixer, EmptyVial,
                HealingCrystal/*WeaponTypeSelector,
                SwordTypeSelector*/};


        Item[] foods = {ragoutRabbitMeat , ScavengerToadMeat, RawRagoutRabbitMeat, ScavengerCookedToadMeat};

        Item[] swords = {Elucidator, AnnealBlade, DarkRepulser, GuiltyThorn, LambentLight, EbonDagger, MateChopper,
                        Karakurenai, ShadowDagger, TyrantDragon, /*KagemitsuG4,*/ LiberatorShield, LiberatorSword};

        Item[] ingots = {CrystalliteIngot, SteelIngot};


        Item[] pSwords = {ElucidatorPowered, AnnealBladePowered, DarkRepulserPowered, GuiltyThornPowered, LambentLightPowered,
                         EbonDaggerPowered, MateChopperPowered, KarakurenaiPowered, ShadowDaggerPowered, TyrantDragonPowered,
                         /*KagemitsuG4Powered, LiberatorShieldPowered, LiberatorSwordPowered*/};

        /*Item[] guns = {FnFiveSeven, PgmUltimaRatioHecateII, Type54BlackStar};*/
        
        Item[] hammers = {ZoringenHammer};

        for (Item item : items) {
            ReferenceHelper.registerItem(item);
        }


        for (Item food : foods) {
            ReferenceHelper.registerItem(food);
        }

        
        for(int i = 0; i < ingots.length; i++) {
        	ReferenceHelper.registerItem(ingots[i]);
        	
        	for(int j = 0; j < hammers.length; j++) {
        		ForgeStationRecipes.forging().addForgingRecipe(ingots[i], WeaponTypeSelector, SwordTypeSelector, hammers[j], 0.1F);
        	}
        }
        
        for(int i = 0; i < foods.length; i++){
            ReferenceHelper.registerItem(foods[i]);

        }

        for (Item sword : swords) {
            ReferenceHelper.registerItem(sword);
        }


        for (Item pSword : pSwords) {
            ReferenceHelper.registerItem(pSword);
        }

       /* for (Item gun : guns) {
            ReferenceHelper.registerItem(gun);
        }
        */
        
        for(int i = 0; i < hammers.length; i++) {
        	ReferenceHelper.registerItem(hammers[i]);
        }
    }
}
