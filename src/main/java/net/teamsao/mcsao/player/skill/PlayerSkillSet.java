package net.teamsao.mcsao.player.skill;



/**
 * Created by bfox1 on 8/11/2014.
 */
public enum PlayerSkillSet {

    /**
     * These are the Players Individual Skills. You can Add More below.
     */
    COMBAT(1, 50, "Combat"), WEAPON(2,50, "Weapons"),SUPPORT(3, 50, "Support");

    public final int PLAYERID;
    public int LVL;
    public final String description;

    private PlayerSkillSet(int PLAYERID, int LVL, String name) {
        this.PLAYERID = PLAYERID;
        this.LVL = LVL;
        this.description = name;
    }

    public int getSkillID()
    {
        return this.PLAYERID;
    }

    public int getSkillLevel()
    {
        return this.LVL;
    }

    public String getSkillDescription()
    {
        return this.description;
    }
}
