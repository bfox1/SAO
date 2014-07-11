package net.teamsao.mcsao.item;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SAOTabsManager;
import net.teamsao.mcsao.lib.SCreativeTab;

/**
 * Created by bfox1 on 7/9/2014.
 */
public class AntidoteCrystal extends Item{

    public AntidoteCrystal(){
        super();
        this.setUnlocalizedName("AntidoteCrystal");
        this.setTextureName(ReferenceHelper.setItemName(this));
        this.setCreativeTab(SAOTabsManager.SAO);
        this.setMaxDamage(12);
    }



    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player){

        player.setItemInUse(item, this.getMaxItemUseDuration(item));
        ItemStack thisItem = new ItemStack(SItem.AntidoteCrystal);
        if(!world.isRemote && player.isPotionActive(Potion.wither) || player.isPotionActive(Potion.blindness) || player.isPotionActive(Potion.weakness) || player.isPotionActive(Potion.poison) || player.isPotionActive(Potion.confusion) || player.isBurning())
        {
            player.clearActivePotions();
            player.extinguish();
            thisItem.setItemDamage(item.getItemDamage());
            thisItem.damageItem(1, player);
            return thisItem;
        }
        return item;
    }
}
