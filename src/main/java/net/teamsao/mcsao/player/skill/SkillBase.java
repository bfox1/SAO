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
    private static final Map<String, Integer> skillData = new HashMap<String, Integer>();

    private static final Map<String, Integer> skillEXPData = new HashMap<String, Integer>();

    private static final String skillName = "skill";

    private int max_skill_lvl = 1000;

    private int skill_LVL = 0;

    private int skill_exp = 0;

    private int maximum_skill_exp = 100;


    public static String[] skills = {
      "Combat", "BlackSmithing"
    };


    public void setSkillLevel(int lvl)
    {
        this.skill_LVL = lvl;
    }

    public void addSkillLevel(int lvl)
    {
        if(skillCapCheck())
        {
            this.skill_LVL = this.skill_LVL + lvl;
        }
    }


    public void addexp(int lvl)
    {
        int experience = lvl + this.skill_exp;
        if(expCapCheck(lvl)) {
            int remainskill = this.skill_exp - this.maximum_skill_exp;
            addSkillLevel(1);
            experience = remainskill;
        }
        this.skill_exp = experience;
    }

    public boolean skillCapCheck()
    {
        if(this.skill_LVL == this.max_skill_lvl)
        {
            System.out.println("Reached Maxed Cap!");
            return false;
        }
        return true;
    }

    public boolean expCapCheck(int lvl)
    {
        if(this.skill_exp + lvl <= this.maximum_skill_exp)
        {
            return true;
        }
        return false;
    }


    public  void saveSkillData(String name, int LVL)
    {
        skillData.put(name, LVL);
    }

    public  Integer getSkillData(String name)
    {
        return skillData.remove(name);
    }

    public  Integer checkSkillData(String name)
    {
       return skillData.get(name);
    }

    public  boolean isSkill(String name)
    {


        for(String list : skills)
        {

        }
        return false;
    }

    public void loadNBTData(NBTTagCompound compound)
    {
        for(String name : skills)
        {
            compound.getInteger(name);
        }
    }

    public void saveNBTData(NBTTagCompound compound)
    {
        NBTTagCompound properties = new NBTTagCompound();
        for(String name : skills) {
            properties.setInteger(name, this.skill_LVL);
            compound.setTag(skillName, properties);
        }

    }
}
