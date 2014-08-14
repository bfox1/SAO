package net.teamsao.mcsao.item.potions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemPotion;
import net.minecraft.util.IIcon;
import net.teamsao.mcsao.lib.SAOTabsManager;

/**
 * Created by bfox1 on 8/11/2014.
 */
public class Elixir extends ItemPotion{
    @SideOnly(Side.CLIENT)
    private IIcon healing;

    @SideOnly(Side.CLIENT)
    private IIcon strength;

    @SideOnly(Side.CLIENT)
    private IIcon speed;

    public Elixir()
    {
        this.setUnlocalizedName("1");
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(SAOTabsManager.SAO);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass(int p_77618_1_, int p_77618_2_)
    {
        return p_77618_2_ == 0 ? this.healing : super.getIconFromDamageForRenderPass(p_77618_1_, p_77618_2_);
    }
}
