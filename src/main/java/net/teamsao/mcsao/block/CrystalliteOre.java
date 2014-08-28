package net.teamsao.mcsao.block;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.teamsao.mcsao.init.SAOItems;

import java.util.Random;

/**
 * Created by bfox1 on 8/26/2014.
 */
public class CrystalliteOre extends BlockSAO {
    public CrystalliteOre() {
        super(Material.rock);
        this.setHardness(3.5F);
        this.setBlockName("CrystalliteOre");
        this.setHarvestLevel("pickaxe", 3);
    }

    @Override
    public int quantityDropped(Random p_149745_1_)
    {
        return 1;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return SAOItems.CrystalliteIngot;
    }

    @Override
    public int quantityDroppedWithBonus(int p_149679_1_, Random p_149679_2_)
    {
        return this.quantityDropped(p_149679_2_);
    }
}
