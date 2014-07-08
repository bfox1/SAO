package net.teamsao.mcsao.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SCreativeTab;

public class DungeonStone extends Block {

	public DungeonStone() {
		
	      super(Material.rock);
	        this.setCreativeTab(SCreativeTab.SAO);
	        this.setBlockName("DungeonBlock");
	    }
	    
		@Override
	    public int damageDropped(int par1)
	    {
	        return par1;
	    }
	    
	    @SideOnly(Side.CLIENT)
	    private IIcon[] icons;
	  
	    @SideOnly(Side.CLIENT)
	    @Override
	    public void registerBlockIcons(IIconRegister par1IconRegister)
	    {
	        icons = new IIcon[4];
	        
	        for(int i = 0; i < icons.length; i++)
	        {
	            icons[i] = par1IconRegister.registerIcon(ReferenceHelper.setBlockName(this) + i);
	        }
	    }
	  
	    @SideOnly(Side.CLIENT)
	    @Override
	    public IIcon getIcon(int par1, int par2)
	    {
	        switch(par2)
	        {
	            case 0:
	                return icons[0];
	            case 1:
	            {
	                switch(par1)
	                {
	                    case 0:
	                        return icons[1];
	                    case 1:
	                        return icons[2];
	                    default:
	                        return icons[3];
	                }
	            }
	            default:
	            {
	                System.out.println("Invalid metadata for " + this.getUnlocalizedName());
	                return icons[0];
	            }
	        }
	    }
	  
	    @SideOnly(Side.CLIENT)
	    @Override
	    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	    {
	          for(int i = 0; i < 4; i++)
	          {
	                 par3List.add(new ItemStack(par1, 1, i));
	          }
	    }

}
