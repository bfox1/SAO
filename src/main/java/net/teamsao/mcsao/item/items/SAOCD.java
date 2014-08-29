package net.teamsao.mcsao.item.items;

import net.teamsao.mcsao.help.Reference;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.init.SAOBlocks;
import net.teamsao.mcsao.item.ItemSAO;

/**
 * Created by bfox1 on 7/25/2014.
 */
public class SAOCD extends ItemSAO{

    public SAOCD()
    {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName("cartridge");
        this.setTextureName(ReferenceHelper.setItemName(this));
    }
}
