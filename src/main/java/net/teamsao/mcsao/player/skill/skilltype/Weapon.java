package net.teamsao.mcsao.player.skill.skilltype;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.ItemSAO;
import net.teamsao.mcsao.item.SItemSword;
import org.lwjgl.Sys;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by bfox1 on 11/17/2014.
 */
public class Weapon {


   private static boolean isWeapon = false;

   private SItemSword weapon;

    public Weapon(SItemSword weapon)
    {
        this.weapon = weapon;
    }


    /**
     * Determines if the weapon is the same as the Array List.
     * @return returns the Type of Skill the weapon belongs to
     */
    public String getWeapon()
    {
        if(isSItemSword())
        {
            for(SItemSword name : weaponsList)
            {
                if(name == weapon || weapon.getUnlocalizedName().toLowerCase().equals(name.getUnlocalizedName().toLowerCase() + "powered"))
                {
                    System.out.println(name);
                    System.out.println(weapon);
                    return name.getTypeName();
                }
            }
        }
        return null;
    }


   public SItemSword[] weaponsList =
    {
            SAOItems.AnnealBlade,
            SAOItems.Elucidator,
            SAOItems.DarkRepulser,
            SAOItems.EbonDagger,
            SAOItems.GuiltyThorn,
            SAOItems.Karakurenai,
            SAOItems.LambentLight,
            SAOItems.TyrantDragon,
            SAOItems.LiberatorSword,
            SAOItems.MateChopper,
            SAOItems.ShadowDagger
    };



    private boolean isSItemSword()
    {
        return weapon instanceof SItemSword;
    }

}
