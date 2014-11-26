package net.teamsao.mcsao.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

/**
 * Created by Bobby on 7/8/2014.
 */
public class SItemSword extends ItemSword

{
    private String typeName;


    public SItemSword(ToolMaterial material)
    {
        super(material);

    }


	public SItemSword(ToolMaterial p_i45356_1_, String typeName)
	{
		super(p_i45356_1_);
        this.typeName = typeName;
	}

	@Override
	public  ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {
            return par1ItemStack;
    }

    public String getTypeName()
    {
       return typeName;
    }

    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }




}
