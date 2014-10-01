package net.teamsao.mcsao.player.skill;

import net.minecraft.nbt.NBTTagCompound;
import net.teamsao.mcsao.helper.LogHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by bfox1 on 9/3/2014.
 */
public class SkillBase {

    private final int MAXIMUM_SKILL_SLOT = 15;

    private int skillSlots = 0;

    private static int max_skill_lvl = 1000;

    private static int maximum_skill_exp = 100;


    public static String[] skills = {
      "Combat", "BlackSmithing", "Fishing"
    };

    /**Returns the Max Skill Level*/
    public static int maxSkillLvl()
    {
        return max_skill_lvl;
    }

    /**Returns the Max Exp per skill*/
    public static int maxSkillExp()
    {
        return maximum_skill_exp;
    }










}
