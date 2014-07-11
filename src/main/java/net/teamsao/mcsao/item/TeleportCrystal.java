package net.teamsao.mcsao.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.teamsao.mcsao.SwordArtOnline;
import net.teamsao.mcsao.dimension.SDimension;
import net.teamsao.mcsao.help.ReferenceHelper;
import net.teamsao.mcsao.lib.SAOTabsManager;
import net.teamsao.mcsao.lib.SCreativeTab;
import net.teamsao.mcsao.portal.SAOTeleporter;


/**
 * @author bfox1
 *
 */
public class TeleportCrystal extends Item {

	/* Chris: Here's an idea for the crystals. Whenever they're used, 
	 * 
	 */
	
	public TeleportCrystal(){
		super();
		this.setUnlocalizedName("TeleportCrystal");
		this.setTextureName(ReferenceHelper.setItemName(this));
		this.setCreativeTab(SAOTabsManager.SAO);
	}
	//Opens a GUI to go to teleport Points. IF ALLOWED.
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3Player) {
        if (!par2World.isRemote && par3Player.isSneaking()) {
            //Opens a GUI to go to teleport Points. IF ALLOWED.
            if (par3Player.dimension != SwordArtOnline.dimensionId) {

               // par3Player.travelToDimension(14);
            }

                EntityPlayerMP player = (EntityPlayerMP) par3Player;

                MinecraftServer mServer = MinecraftServer.getServer();

                if (player.timeUntilPortal > 0)
                {
                    player.timeUntilPortal = 10;
                }
                else if (player.dimension != SwordArtOnline.dimensionId)
                {
                    player.timeUntilPortal = 10;

                    player.mcServer.getConfigurationManager().transferPlayerToDimension(player, SwordArtOnline.dimensionId, new SAOTeleporter(mServer.worldServerForDimension(SwordArtOnline.dimensionId)));
                }
                else
                {
                    player.timeUntilPortal = 10;
                    player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new SAOTeleporter(mServer.worldServerForDimension(1)));
                }
            }



        return par1ItemStack;
    }
	
	/**
	 * @author 5chris100
     *
	 */
	
	public ItemStack onItemLeftClick(ItemStack par1, World par2, EntityPlayer par3) {
		if (!par2.isRemote && par3.isSneaking()) {
			int x = par3.getPlayerCoordinates().posX;
			int y = par3.getPlayerCoordinates().posY;
			int z = par3.getPlayerCoordinates().posZ;
			// Opens a GUI to set current coordinates as teleport point.
            /*
            this will send player to DImension number.
             */
		}
		
		return par1;
		
	}
}
