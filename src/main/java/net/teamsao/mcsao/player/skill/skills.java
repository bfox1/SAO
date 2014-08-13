package net.teamsao.mcsao.player.skill;

/**
 * Created by bfox1 on 8/11/2014.
 */
public enum skills {



        SLASHWEAPONFORGING(1, 1, "Slashing Weapon Forging"), THRUSTWEAPONFORGING(1,1, "Thrust Weapon Forging"), BLUNTWEAPONFORGING(1,1, "Blunt Weapon Forging"),
        LIGHTMETALARMORFORGING(1,1,"Light Metal Armor Forging"), HEAVYMETALARMORFORGING(1, 1, "Heavy Metal Armor Forgin"), METALEQUIPMENTREPPAIR(1, 1, "Metal Equipment Repair"),
        METALREFINING(1, 1, "Metal Refining"), PICKING(1, 1, "Lock Picking"), SEARCHING(1, 1, "Entity Tracking"), HIDING(1,1, "Player Hiding"), NIGHTVISION(1,1,"Night Vision"), REVEAL(1,1, "See Through Objects"),


        //Weapons
        STRAIGHTSWORD(1, 1, "Straight sword"), CURVEDBLADE(1,1, "Curved Blade"), DAGGER(1,1,"Dagger"), RAPIERS(1,1,"Rapiers"), ASSAULTSPEAR(1,1,"Assault Spear"),
        WARHAMMER(1,1, "WarHammer"), BLADETHROWING(1,1,"Blade Thrower"), KATANA(1,1,"Katana"),

        //Unique Skills
        DUALBLADE(1,1,"Dual Blade"), HOLYSWORD(1,1,"Holy Sword"), DARKNESSBLADE(1,1,"Darkness Blade"), BATTOUJUTSU(1,1,"Battou Jutsu"), SHURIKENJUTSU(1,1,"Shuriken Jutsu"),
        INFINITESPEAR(1,1,"Infinite Spear");

    public final int PLAYERID;
    public int LVL;
    public final String description;

    private skills(int PLAYERID, int LVL, String name) {
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
