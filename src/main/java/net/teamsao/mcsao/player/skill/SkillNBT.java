package net.teamsao.mcsao.player.skill;



/**
 * Created by bfox1 on 8/11/2014.
 */
public class SkillNBT {

    private String skillName;
    private int lvl;
    private long exp;

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
}
