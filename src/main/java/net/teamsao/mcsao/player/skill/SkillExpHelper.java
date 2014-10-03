package net.teamsao.mcsao.player.skill;

/**
 * Created by bfox1 on 10/2/2014.
 */
public class SkillExpHelper {

    private long baseExp = 0;
    private String skillName;
    private int lvl;
    private long exp;
    private long nextExp = 0;
    private double expGain = 0.8;

    private int skillIndex;

    public SkillExpHelper(int index, String name, int level, long exp)
    {
        this.skillIndex = index;
        this.skillName = name;
        this.lvl = level;
        this.exp = exp;
    }

    public long getBaseExp(int level)
    {
        double exp = (5*level^3)*expGain;
        this.baseExp = (long)exp;
        return (long)exp;
    }


}
