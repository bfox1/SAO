package net.teamsao.mcsao.material;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWood;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import net.teamsao.mcsao.init.SAOItems;
import net.teamsao.mcsao.item.ItemIngot;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by bfox1 on 1/3/2015.
 */

public class SwordMaterial{



    private Item itemMaterial;
    private Item itemModifier;

    private Block blockMaterial;
    private Block blockModifier;

    private int itemMaterialAmt;
    private int itemModAmt;

    private int blockMaterialAmount;
    private int blockModAmt;

    private int metaData;
    private int maxUses;
    private float effeciency;
    private float dmgVsEntity;

    public SwordMaterial(Item item)
    {
        this.itemMaterial = item;
    }

    public SwordMaterial(Item item, int amount)
    {
        this.itemMaterial = item;
        this.itemMaterialAmt = amount;
    }
    public SwordMaterial(Block block)
    {
        this.blockMaterial = block;
    }
    public SwordMaterial(Block block, int amount)
    {
        this.blockMaterial = block;
        this.blockMaterialAmount = amount;
    }

    protected void setItemMaterial(Item item, int amount)
    {
        this.itemMaterial = item;
        this.itemMaterialAmt = amount;
    }
    protected void setBlockMaterial(Block block, int amount)
    {
        this.blockMaterial = block;
        this.blockMaterialAmount = amount;
    }
    protected Item getItemMaterial()
    {
        return this.itemMaterial;
    }
    protected int getItemMaterialAmt()
    {
        return this.itemMaterialAmt;
    }
    protected Block getBlockMaterial()
    {
        return this.blockMaterial;
    }
    protected int getBlockMaterialAmount()
    {
        return this.blockMaterialAmount;
    }
    protected void setItemModifier(Item item, int amount)
    {
        this.itemModifier = item;
        this.itemModAmt = amount;
    }
    protected void setBlockModifier(Block block, int amount)
    {
        this.blockModifier = block;
        this.blockModAmt = amount;
    }
    protected Item getItemModifier()
    {
        return this.itemModifier;
    }
    protected Block getBlockModifier()
    {
        return this.blockModifier;
    }
    protected int getItemModAmt()
    {
        return this.itemModAmt;
    }
    protected int getBlockModAmt()
    {
        return this.blockModAmt;
    }

    public void getMaterialDetails(SwordMixMaterial material)
    {
        if(this.getItemMaterial().equals(Items.iron_ingot))
        {
            material.determineMixMaterial(material.IRON);

        }else if(this.getItemMaterial().equals(Items.diamond))
        {
            material.determineMixMaterial(material.DIAMOND);

        }else if(this.getItemMaterial().equals(Items.gold_ingot))
        {
            material.determineMixMaterial(material.GOLD);

        }else if(this.getItemMaterial().equals(SAOItems.SteelIngot))
        {
            material.determineMixMaterial(material.STEEL);

        }else if(this.getItemMaterial().equals(SAOItems.CrystalliteIngot))
        {
            material.determineMixMaterial(material.CRYSTALLITE);

        }else if(this.getBlockMaterial() instanceof BlockWood)
        {
            material.determineMixMaterial(material.WOOD);
        }

        this.metaData = material.metaData;
        this.maxUses = material.maxUses;
        this.effeciency = material.effeciency;
        this.dmgVsEntity = material.dmgVsEntity;
    }

    public static enum SwordMixMaterial {

        WOOD(0,25,3,2),
        IRON(1, 150, 13, 4),
        GOLD(2, 15, 4, 3),
        DIAMOND(3,1500,30,8),
        STEEL(4, 500, 20, 6),
        CRYSTALLITE(5, 1700, 32, 7);
        /**
         * Special Class for ALL Swords. This class main focus is for defining the material of the sword.
         */


        private int metaData;
        private int maxUses;
        private float effeciency;
        private float dmgVsEntity;


        private SwordMixMaterial(int metadata, int maxUses, float eff, float dmgVsEnt)
        {
            this.metaData = metadata;
            this.maxUses = maxUses;
            this.effeciency = eff;
            this.dmgVsEntity = dmgVsEnt;
        }

        private void determineMixMaterial(SwordMixMaterial value)
        {

            switch (value)
            {
                case WOOD:
                {
                    this.maxUses = this.maxUses + 25;
                    this.effeciency = this.effeciency + 5;
                    this.dmgVsEntity = this.dmgVsEntity + 2;
                    break;
                }
                case IRON:
                {
                    this.maxUses = this.maxUses + 150;
                    this.effeciency = this.effeciency + 13;
                    this.dmgVsEntity = this.dmgVsEntity + 4;
                    break;
                }
                case STEEL:
                {
                    this.maxUses = this.maxUses + 500;
                    this.effeciency = this.effeciency + 20;
                    this.dmgVsEntity = this.dmgVsEntity + 6;
                    break;
                }
                case CRYSTALLITE:
                {
                    this.maxUses = this.maxUses + 1700;
                    this.effeciency = this.effeciency + 32;
                    this.dmgVsEntity = this.dmgVsEntity + 7;
                    break;
                }
                case DIAMOND:
                {
                    this.maxUses = this.maxUses + 1500;
                    this.effeciency = this.effeciency + 30;
                    this.dmgVsEntity = this.dmgVsEntity + 8;
                    break;
                }
            }
        }
    }

}
