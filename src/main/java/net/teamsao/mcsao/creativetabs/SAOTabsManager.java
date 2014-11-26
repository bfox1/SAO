package net.teamsao.mcsao.creativetabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.teamsao.mcsao.init.SAOItems;

/**
 * @author bfox1
 *
 */
public class SAOTabsManager extends CreativeTabs {

    public SAOTabsManager(int id, String name) {
        super(id, name);
    }
    public static final SAOTabsManager SAO = new SAOTabsManager(CreativeTabs.getNextID(), "SAO") {

        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return SAOItems.TownTeleportCrystal;
        }
    };

    public static final SAOTabsManager saoTools = new SAOTabsManager(CreativeTabs.getNextID(), "SaoTools") {


    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return SAOItems.Elucidator;
    }

};

	public static final SAOTabsManager saoIngots = new SAOTabsManager(CreativeTabs.getNextID(), "SaoIngots") {
		@SideOnly(Side.CLIENT)
    	public Item getTabIconItem() {
    		return SAOItems.CrystalliteIngot;
    	}
	};


    public static final SAOTabsManager saoFood = new SAOTabsManager(CreativeTabs.getNextID(), "SaoFoods")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return SAOItems.RawRagoutRabbitMeat;
        }
    };

    public static final SAOTabsManager saoBlocks = new SAOTabsManager(CreativeTabs.getNextID(), "SaoBlocks")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return SAOItems.DungeonStoneItem;
        }
    };

    public static final SAOTabsManager saoGuns = new SAOTabsManager(CreativeTabs.getNextID(), "SAO Guns") {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() { return SAOItems.CDSAO; }
    };

    @Override
    public Item getTabIconItem() {
        return SAOItems.TownTeleportCrystal;
    }


}
