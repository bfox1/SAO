package net.teamsao.mcsao.player.skill;

/**
 * Created by bfox1 on 8/11/2014.
 */
public enum SkillType {

    //Weapon Skills
    ONEHANDEDSWORD(0,0, "one-handed-sword", "weapon"),
    ONECURVEDSWORD(0,0, "one-handed-curved-sword", "weapon"),
    TWOHANDEDBATTLEAXE(0,0, "two-handed-battle-axe", "weapon"),
    TWOHANDEDSWORD(0,0, "two-handed-sword", "weapon"),
    RAPIERSWORD(0,0, "rapier-sword", "weapon");
    /*
    TODO
    Support
    Combat
    Fishing
    Cooking
    Extra
    Unique
    */


    private int LVL;
    private final String name;
    private final String className;
    private long exp;

    private SkillType(int LVL, long exp, String name, String className) {

        this.LVL = LVL;
        this.name = name;
        this.className = className;
        this.exp = exp;
    }

    public long getExp()
    {
        return this.exp;
    }

    public int getSkillLevel()
    {
        return this.LVL;
    }

    public String getSkillDescription()
    {
        return this.name;
    }

    public String getClassName()
    {
        return this.className;
    }



}
