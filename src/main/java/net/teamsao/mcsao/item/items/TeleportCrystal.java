package net.teamsao.mcsao.item.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.teamsao.mcsao.helper.ReferenceHelper;
import net.teamsao.mcsao.world.DimensionId;
import net.teamsao.mcsao.creativetabs.SAOTabsManager;

import java.io.File;


/**
 * @author bfox1
 *
 */


public class TeleportCrystal extends Item {

    public File saveDir;
    public File worldDir = null;



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
            if (par3Player.dimension != DimensionId.SAO_DIMENSION_ID) {

                /*
=======
>>>>>>> origin/Ian:src/main/java/net/teamsao/mcsao/item/items/TeleportCrystal.java
                int x = Minecraft.getMinecraft().objectMouseOver.blockX;
                int y = Minecraft.getMinecraft().objectMouseOver.blockY;
                int z = Minecraft.getMinecraft().objectMouseOver.blockZ;




                String name = par2World.getBlock(x, y, z).getUnlocalizedName().substring(5);
                if(name.equals(SAOBlocks.TeleportCrystalBlock.getUnlocalizedName().substring(5)))
                {
                    par3Player.addChatMessage(new ChatComponentText("They Equal"));
                    load();

                }

                par3Player.addChatMessage(new ChatComponentText(x + ":" + y + ":" + z + ":" + name));
                //System.out.println(Minecraft.getMinecraft().objectMouseOver);

            */



            }
            /*

                EntityPlayerMP player = (EntityPlayerMP) par3Player;

                MinecraftServer mServer = MinecraftServer.getServer();

                if (player.timeUntilPortal > 0)
                {
                    player.timeUntilPortal = 10;
                }
                else if (player.world != SwordArtOnline.dimensionId)
                {
                    player.timeUntilPortal = 10;

                    player.mcServer.getConfigurationManager().transferPlayerToDimension(player, SwordArtOnline.dimensionId, new SAOTeleporter(mServer.worldServerForDimension(SwordArtOnline.dimensionId)));
                }
                else
                {
                    player.timeUntilPortal = 10;
                    player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new SAOTeleporter(mServer.worldServerForDimension(1)));
                }

            */
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

    public void load()
    {
        if((Minecraft.getMinecraft().theWorld == null) || (Minecraft.getMinecraft().thePlayer == null))
        {
            return;
        }

        File worldDir = new File(new File(saveDir, "SAOMODs"), "default");
    }



}
