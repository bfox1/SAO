package net.teamsao.mcsao.player.skill;

import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bfox1 on 9/3/2014.
 */
public class SkillBase {
    private static final Map<String, Integer> skillData = new HashMap<String, Integer>();

    public static String[] skills = {
      "Combat", "BlackSmithing"
    };


    public static void saveSkillData(String name, int LVL)
    {
        skillData.put(name, LVL);
    }

    public static Integer getSkillData(String name)
    {
        return skillData.remove(name);
    }

    public static Integer checkSkillData(String name)
    {
       return skillData.get(name);
    }
}
