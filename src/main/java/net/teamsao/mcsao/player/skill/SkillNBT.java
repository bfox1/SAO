package net.teamsao.mcsao.player.skill;


import net.minecraft.util.MathHelper;

/**
 * Created by bfox1 on 8/11/2014.
 */
public class SkillNBT {

    private long baseExp = 0;
    private String skillName;
    private int lvl;
    private long exp;
    private long nextExp = 0;
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

    /**
     * Creates the Base Experience per Level for Skills.
     * @param level The Level of the Skill.
     * @return returns the BaseExp for Skill.
     */
    public long getBaseExp(int level)
    {
        //double exp = (level^3)*expGain;
        double exp = (Math.pow(level, 3))*expGain;
        this.baseExp = (long)exp;
        if(level == 1)
        {
            this.nextExp = 6;
            exp = 6;
        }
        System.out.println("[Get Base EXP]" + exp);
        return (long)exp;
    }

    /**
     * Generates the Modifier based on the Conditions you need.
     * @param index ex( 0 = killed Animal
     *                  1 = killed Mob
     *                  2 = killed Player
     *                  3 = killed Boss
     *                  4 = killed Floor Boss)
     * @return
     */
    public double getModifier(int index)
    {
        double mod;
        return mod = index == 0 ? 0.8
                   : index == 1 ? 1.0
                   : index == 2 ? 1.5
                   : index == 3 ? 2.0
                   : index == 4 ? 2.5 : 0.5;
    }

    public int getMaxLevel()
    {
        return maxLevel;
    }


    /**
     * Generates the Amount of Exp obtained.
     * @param level The Skill level.
     * @param baseExp The Base Experience passed in by the getBaseExp Method
     * @param modifier The Modifier that determines How much is given after calculations.
     * @return returns the exact amount to be received
     */
    public static long generateExp(int level, long baseExp, double modifier)
    {
            System.out.println("[GENERATE EXP]" + level + ":" + baseExp);
            double exp = ((baseExp * level) * modifier) / 7;
            System.out.println("[GENERATE EXP]" + (long)exp);
            return (long)exp;
    }

    public long generateNextExp(int level)
    {
        int nxtLevel = level + 1;
        double nxtExp = (Math.pow(level, 3))*expGain;
        return (long)nxtExp;
    }


}
