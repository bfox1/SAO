package net.teamsao.mcsao.item.swords;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.init.SAOItems;

/**
 * @author 5chris100
 */
public class KagemitsuG4Powered extends KagemitsuG4
{

    public KagemitsuG4Powered(ToolMaterial material, String typeName )
    {
        super(material, typeName);
        this.setUnlocalizedName("KagemitsuG4Powered");
        this.setTextureName("KagemitsuG4");
        this.setCreativeTab(null);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack swordPowered, World world, EntityPlayer player)
    {
        player.setItemInUse(swordPowered, this.getMaxItemUseDuration(swordPowered));
        ItemStack sword = new ItemStack(SAOItems.KagemitsuG4);
        if(!world.isRemote)
        {
            if(player.isSneaking())
            {
                sword.setItemDamage(swordPowered.getItemDamage());
                return sword;
            }
        }
        return swordPowered;
    }
}
