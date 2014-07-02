package net.bfox1.sao.lib;

import net.minecraft.util.EnumChatFormatting;

public enum EnumColor {
	
    common(EnumChatFormatting.WHITE, "Common"),
    uncommon(EnumChatFormatting.YELLOW, "Uncommon"),
    rare(EnumChatFormatting.AQUA, "Rare"),
    superrare(EnumChatFormatting.DARK_AQUA, "SuperRare"),
    ultrarare(EnumChatFormatting.GOLD, "UltraRare"),
    ultimaterare(EnumChatFormatting.DARK_BLUE, "UltimateRare"),
    secretrare(EnumChatFormatting.RED, "SecretRare"),
    epic(EnumChatFormatting.LIGHT_PURPLE, "Epic");
    /**
     * A decimal representation of the hex color codes of a the color assigned to this rarity type. (13 becomes d as in
     * 247d which is light purple)
     */
    public final EnumChatFormatting rarityColor;
    /** Rarity name. */
    public final String rarityName;

    private static final String __OBFID = "CL_00000056";

    private EnumColor(EnumChatFormatting p_i45349_3_, String p_i45349_4_)
    {
        this.rarityColor = p_i45349_3_;
        this.rarityName = p_i45349_4_;
    }


}
