package net.teamsao.mcsao.block.inventoryblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.lib.SAOTabsManager;
import net.teamsao.mcsao.tileentity.TileEntityForgeStation;

import java.util.Random;

/**
 * Created by bfox1 on 7/12/2014.
 */
public class ForgeStation extends BlockContainer {
    int cookTime;

    public ForgeStation() {
        super(Material.iron);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setBlockName("ForgeStation");
        this.setCreativeTab(SAOTabsManager.saoBlocks);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
                                    int metadata, float what, float these, float are)
    {
        TileEntity tileEntity = world.getTileEntity(x, y , z);
        if(tileEntity == null || player.isSneaking()){
            return false;
        }
        //Code to Open Gui.
        player.openGui(SwordArtOnline.instance, 0, world, x, y, z);
        return true;
    }

    @Override
    public void breakBlock(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_)
    {
        if (hasTileEntity(p_149749_6_) && !(this instanceof BlockContainer))
        {
            p_149749_1_.removeTileEntity(p_149749_2_, p_149749_3_, p_149749_4_);
        }
    }

    private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
            return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack item = inventory.getStackInSlot(i);

            if (item != null && item.stackSize > 0) {
                float rx = rand.nextFloat() * 0.8F + 0.1F;
                float ry = rand.nextFloat() * 0.8F + 0.1F;
                float rz = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world,
                        x + rx, y + ry, z + rz,
                        new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                if (item.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                item.stackSize = 0;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityForgeStation();
    }
}