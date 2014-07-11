package net.teamsao.mcsao.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.teamsao.mcsao.block.SBlock;
import net.teamsao.mcsao.item.SItem;

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
            return SItem.TeleportCrystal;
        }
    };

    public static final SAOTabsManager saoTools = new SAOTabsManager(CreativeTabs.getNextID(), "SaoTools") {


    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return SItem.Elucidator;
    }

};


    public static final SAOTabsManager saoFood = new SAOTabsManager(CreativeTabs.getNextID(), "SaoFoods")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return SItem.ragoutRabbitMeat;
        }
    };

    public static final SAOTabsManager saoBlocks = new SAOTabsManager(CreativeTabs.getNextID(), "SaoBlocks")
    {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return SItem.DungeonStoneItem;
        }
    };
    @Override
    public Item getTabIconItem() {
        return SItem.TeleportCrystal;
    }


}
