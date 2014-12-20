package net.teamsao.mcsao.player.skill;

/**
 * Created by bfox1 on 8/11/2014.
 */
public enum SkillType {

    //Weapon Skills
    ONEHANDEDSWORD(0, "one-handed-sword", "weapon"),
    ONECURVEDSWORD(0, "one-handed-curved-sword", "weapon"),
    TWOHANDEDBATTLEAXE(0, "two-handed-battle-axe", "weapon"),
    TWOHANDEDSWORD(0, "two-handed-sword", "weapon"),
    RAPIERSWORD(0, "rapier-sword", "weapon");
    /*
    TODO
    Support
    Combat
    Fishing
    Cooking
    Extra
    Unique
    */


    public int LVL;
    public final String name;
    public final String className;

    private SkillType(int LVL, String name, String className) {

        this.LVL = LVL;
        this.name = name;
        this.className = className;
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
