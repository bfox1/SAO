package net.teamsao.mcsao.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;
import net.teamsao.mcsao.helper.Reference;
import net.teamsao.mcsao.tileentity.TileEntityTeleportCrystalBlock;

/**
 * Created by bfox1 on 7/20/2014.
 */
public class TeleportCrystalBlock extends BlockSAO implements ITileEntityProvider {
    public TeleportCrystalBlock( ) {
        super(Material.anvil);
        this.setBlockName("BlockCrystalLocator");
        this.setCreativeTab(SAOTabsManager.saoBlocks);
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityTeleportCrystalBlock();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
		if(world.isRemote)
		{
			if(player.isSneaking())
			{
                player.openGui(SwordArtOnline.instance, Reference.GUI_TELEPORT_CRYSTAL_BLOCK, player.worldObj, (int) player.posX,
                        (int) player.posY, (int)player.posZ);
            }
			else
			{
				TileEntityTeleportCrystalBlock tileEntity = (TileEntityTeleportCrystalBlock) world.getTileEntity(x, y, z);
				tileEntity.addPlayerData(player);
			}
		}
		
		return true;
    }
}
