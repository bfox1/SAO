package net.teamsao.mcsao.item.ingots;

import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.item.ItemIngot;

/**
 * Created by bfox1 on 8/25/2014.
 */
public class SteelIngot extends ItemIngot {

    public SteelIngot() {
        super();
        this.setUnlocalizedName("SteelIngot");
        this.setMalleability(2);
        this.setTextureName(ReferenceHelper.setItemName(this));
    }
}
