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
public class SkillList {


    private final int MAXIMUM_SKILL_SLOT = 15;

    private int skillSlots = 0;

    public static final String[] WEAPON = {
        "one-handed-sword", "one-handed-curved-sword", "two-handed-battle-axe", "two-handed-sword",
            "rapier-sword", "dagger", "knife-throwing", "weapon-defense", "two-handed-long-bow",
            "spear"
    };

    public static final String[] SUPPORT = {
        "slash-weapon-forging", "thrust-weapon-forging", "blunt-weapon-forging",
            "light-metal-armor-forging", "heavy-metal-armor-forging", "metal-equipment-repairing",
            "metal-refining", "wood-crafting", "picking", "searching", "tracking", "night-vision",
            "straining", "hiding", "reveal", "sprint", "extended-weight-limit"
    };

    public static final String[] COMBAT = {
      "parry", "howl", "battle-healing", "emergency-recovery", "meditation", "light-metal-equipment",
            "heavey-metal-equipment"
    };

    public static final String[] FISHING = {

    };

    public static final String[] COOKING = {

    };

    public static final String[] EXTRA = {
      "martial-arts", "katana"
    };

    public static final String[] UNIQUE = {
      "dual-blades", "holy-sword", "darkness-blade", "battoujutsu", "shurikenjutsu",
            "infinite-spear"
    };














}
