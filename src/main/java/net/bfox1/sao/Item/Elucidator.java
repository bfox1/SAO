package net.bfox1.sao.Item;

import java.util.List;

import org.lwjgl.util.Color;

import cpw.mods.fml.client.config.GuiConfigEntries.ChatColorEntry;
import net.bfox1.sao.help.Reference;
import net.bfox1.sao.lib.SCreativeTab;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * @author bfox1
 *
 */
public class Elucidator extends ItemSword {

	public Elucidator(ToolMaterial par1) {
		super(par1);
		setUnlocalizedName("Elucidator");
		setTextureName(Reference.MODID + ":" + getUnlocalizedName().substring(5));
		this.setCreativeTab(SCreativeTab.SAO);

	}
	
	@Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3)
    {
        par3.setItemInUse(par1, this.getMaxItemUseDuration(par1));
        ItemStack sword = new ItemStack(SItem.Elucidator_Powered);
        sword.addEnchantment(Enchantment.fireAspect, 2);

        if(!par2.isRemote)
        {
    		if (par1.getItemDamage() > 0)
    		{
    			sword.setItemDamage(par1.getItemDamage());
    			return sword;
    		}else
    		{
    			return sword;
    		}
        }
        
        return par1;
    }


	
	
}
