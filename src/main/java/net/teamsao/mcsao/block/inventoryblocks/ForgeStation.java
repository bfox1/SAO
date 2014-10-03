package net.teamsao.mcsao.block.inventoryblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.init.SAOBlocks;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;
import net.teamsao.mcsao.tileentity.TileEntityForgeStation;

import java.util.Random;

/**
 * Created by bfox1 on 7/12/2014.
 * Edited by Skymmer on 8/7/2014.
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

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(x, y , z);
        if(tileEntity == null || player.isSneaking()) {
            return false;
        }
        //Code to Open Gui.
        player.openGui(SwordArtOnline.instance, SAOBlocks.ForgingStationID, world, x, y, z); // moved id to a separate area for easier reference later - Skymmer
        return true;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int i) {
        if (hasTileEntity(i) && !(this instanceof BlockContainer)) {
            world.removeTileEntity(x, y, z);
        }
    }

    /*private void dropItems(World world, int x, int y, int z){ // Don't know what this does, it might be covered by another method - Skymmer
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
    } */
    
    public boolean hasComparatorInputOverride() {
		return true;
	}

	public int getComparatorInputOverride(World world, int x, int y, int z, int i) {
		return Container.calcRedstoneFromInventory((IInventory)world.getTileEntity(x, y, z));
	}
	
	public Item getItemDropped(int i, Random rand, int j) {
		return Item.getItemFromBlock(SAOBlocks.ForgingStation);
	}
	
	public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(SAOBlocks.ForgingStation);
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new TileEntityForgeStation();
    }
    
    @Override
    public String getUnlocalizedName()
    {
    	return "sao:ForgeStation";
    }
}
