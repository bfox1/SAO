package net.teamsao.mcsao.player.skill;


import net.minecraft.util.MathHelper;

import java.util.Random;

/**
 * Created by bfox1 on 8/11/2014.
 */
public class SkillNBT {

    private long baseExp = 0;
    private String skillName;
    private int lvl;
    private long exp;
    private double expGain = 0.8;
    private int maxLevel = 1000;

    private int skillIndex;

    public SkillNBT(int skillIndex, String skillName, int lvl, long exp)
    {
        this.skillIndex = skillIndex;
        this.skillName = skillName;
        this.lvl = lvl;
        this.exp = exp;
    }

    public String getSkillName()
    {
        return skillName;
    }

    public Integer getSkillLevel()
    {
        return lvl;
    }

    public Long getSkillExp()
    {
        return exp;
    }

    public void setSkillName(String skillName)
    {
        this.skillName = skillName;
    }

    public void setLvl(int level)
    {
        this.lvl = level;
    }
    public void setExp(long exp)
    {
        this.exp = exp;
    }
    public int getMaxLevel()
    {
        return maxLevel;
    }

    public int getBaseExp(int level)
    {
        int baseExp = (100 * level) + 100;
        return baseExp;
    }

    public int generateMobExp(int start, int end)
    {
        Random rand = new Random();
        int range = end - start + 1;
        int fraction = (int)(range * rand.nextDouble());
        int randnum = fraction + start;
        return randnum;
    }

}
