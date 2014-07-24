package net.teamsao.mcsao.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.teamsao.mcsao.item.SItem;

/**
 * Created by bfox1 on 7/22/2014.
 */
public abstract class SEntityPlayer extends EntityPlayer {

    int sleepTimer;

    EntityPlayer player;

    public SEntityPlayer(World p_i45324_1_, GameProfile p_i45324_2_) {
        super(p_i45324_1_, p_i45324_2_);
    }

    @Override
    public void addChatMessage(IChatComponent p_145747_1_) {

    }

    @Override
    public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_) {
        return false;
    }

    @Override
    public ChunkCoordinates getPlayerCoordinates() {
        return null;
    }

    @Override
    public boolean isPlayerFullyAsleep()
    {
        return this.sleeping && this.sleepTimer >= 100;


    }
    @Override
    public void wakeUpPlayer(boolean p_70999_1_, boolean p_70999_2_, boolean p_70999_3_)
    {
        this.setSize(0.6F, 1.8F);
        this.resetHeight();
        ChunkCoordinates chunkcoordinates = this.playerLocation;
        ChunkCoordinates chunkcoordinates1 = this.playerLocation;
        Block block = (chunkcoordinates == null ? null : worldObj.getBlock(chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ));

        if (chunkcoordinates != null && block.isBed(worldObj, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, this))
        {
            block.setBedOccupied(this.worldObj, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, this, false);
            chunkcoordinates1 = block.getBedSpawnPosition(this.worldObj, chunkcoordinates.posX, chunkcoordinates.posY, chunkcoordinates.posZ, this);

            if (chunkcoordinates1 == null)
            {
                chunkcoordinates1 = new ChunkCoordinates(chunkcoordinates.posX, chunkcoordinates.posY + 1, chunkcoordinates.posZ);
            }

            if(this.player.inventory.armorInventory[3] == new ItemStack(SItem.NerveGear) && this.player.isPlayerSleeping()) {

                ChunkCoordinates name = player.getBedLocation(0);

                int x = name.posX;
                int y = name.posY;
                int z = name.posZ;

                this.player.addChatMessage(new ChatComponentText(x + ":" + y + ":" + z));
                System.out.println( "Final If Statement");

                // this.tickCounter = 0;
            }
            this.setPosition((double)((float)chunkcoordinates1.posX + 0.5F), (double)((float)chunkcoordinates1.posY + this.yOffset + 0.1F), (double)((float)chunkcoordinates1.posZ + 0.5F));
        }

        this.sleeping = false;

        if (!this.worldObj.isRemote && p_70999_2_)
        {
            this.worldObj.updateAllPlayersSleepingFlag();
        }

        if (p_70999_1_)
        {
            this.sleepTimer = 0;
        }
        else
        {
            this.sleepTimer = 100;
        }

        if (p_70999_3_)
        {
            this.setSpawnChunk(this.playerLocation, false);
        }
    }

}
