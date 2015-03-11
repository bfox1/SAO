package net.teamsao.mcsao.helper;

/**
 * @author bfox1
 *
 */
public class Reference 
{
		public static final String MODID = "sao";
		public static final String NAME = "Sword Art Online";
		public static final String VERSION = "0.5.7-Alpha";
		public static final String DEPENDENCIES = "require-after:forgeSrc@(1.7.10)";
		public static final String CLIENTPROXY = "net.teamsao.mcsao.proxy.ClientProxy";
		public static final String SERVERPROXY = "net.teamsao.mcsao.proxy.ServerProxy";
        public static final String FINGERPRINT = "Test";
        public static final String GUI_FACTORY = "net.teamsao.mcsao.client.gui.SAOGuiFactory";
        public static final int NUMBER_OF_TOWNS = 11;
        
        public static final int MOD_GUI_INDEX = 0;
        public static final int GUI_ITEM_INV = MOD_GUI_INDEX + 1;
        public static final int GUI_TELEPORT_CRYSTAL = MOD_GUI_INDEX + 2;
        public static final int GUI_TELEPORT_CRYSTAL_BLOCK = MOD_GUI_INDEX + 3;
        public static final int GUI_SKILL = MOD_GUI_INDEX + 4;
        public static final int GUI_FORGE_STATION = MOD_GUI_INDEX + 5;
        public static final int GUI_PLAYER_INTERFACE = MOD_GUI_INDEX + 6;
}
