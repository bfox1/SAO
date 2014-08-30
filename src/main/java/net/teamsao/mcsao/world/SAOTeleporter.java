package net.teamsao.mcsao.world;

import java.util.*;


import cpw.mods.fml.common.FMLCommonHandler;
import ibxm.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.*;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.teamsao.mcsao.block.BlockSAO;
import net.teamsao.mcsao.init.SAOBlocks;
import net.teamsao.mcsao.player.PlayerSAO;

public class SAOTeleporter extends Teleporter
{



    int spawnX = 0;
    int spawnY = 40;
    int spawnZ = 0;
    private final WorldServer worldServerInstance;
 
    /** A private Random() function in Teleporter */
    private final Random random;
 
    /** Stores successful portal placement locations for rapid lookup. */
    private final LongHashMap destinationCoordinateCache = new LongHashMap();
 
    /**
     * A list of valid keys for the destinationCoordainteCache. These are based on the X & Z of the players initial
     * location.
     */
    @SuppressWarnings("rawtypes")
    private final List destinationCoordinateKeys = new ArrayList();
 
    public SAOTeleporter(WorldServer par1WorldServer)
    {
        super(par1WorldServer);
        this.worldServerInstance = par1WorldServer;
        this.random = new Random(par1WorldServer.getSeed());
    }

    /*
    Will explain what this junk does later
    
     */

    public static void tranferPlayerToDimension(EntityPlayerMP mpPlayer, int newDim)
    {
        EntityPlayer player = (EntityPlayerMP)mpPlayer;
        PlayerSAO.loadProxyData(player);
        PlayerSAO props = PlayerSAO.get(player);
        int X = props.getXCoord();
        int Y = props.getYCoord();
        int Z = props.getZCoord();

        MinecraftServer mcServer = MinecraftServer.getServer();
        int s = mpPlayer.dimension;
        WorldServer worldServer = mcServer.worldServerForDimension(mpPlayer.dimension);
        mpPlayer.dimension = newDim;
        WorldServer worldServer1 = mcServer.worldServerForDimension(mpPlayer.dimension);
        mpPlayer.playerNetServerHandler.sendPacket(new S07PacketRespawn(mpPlayer.dimension, mpPlayer.worldObj.difficultySetting, mpPlayer.worldObj.getWorldInfo().getTerrainType(), mpPlayer.theItemInWorldManager.getGameType()));
        worldServer.removePlayerEntityDangerously(mpPlayer);
        mpPlayer.isDead = false;
        transferEntityToWorld(mpPlayer, s, worldServer, worldServer1);
        func_72375_a(mpPlayer, worldServer);

        if(newDim != s && newDim == 2) {
            Chunk chunk = worldServer.getChunkFromBlockCoords(0, 0);
            int height = chunk.getHeightValue(0, 0);
            mpPlayer.playerNetServerHandler.setPlayerLocation(0, height, 0, mpPlayer.rotationYaw, mpPlayer.rotationPitch);
        } else if (newDim != s && newDim == 0) {
            mpPlayer.playerNetServerHandler.setPlayerLocation(X, Y, Z, mpPlayer.rotationYaw, mpPlayer.rotationPitch);
        }

        mpPlayer.theItemInWorldManager.setWorld(worldServer1);
        updateTimeAndWeatherForPlayer(mpPlayer, worldServer1);
        syncPlayerInventory(mpPlayer);

        for (Object o : mpPlayer.getActivePotionEffects()) {
            PotionEffect potioneffect = (PotionEffect) o;
            mpPlayer.playerNetServerHandler.sendPacket(new S1DPacketEntityEffect(mpPlayer.getEntityId(), potioneffect));
        }
        FMLCommonHandler.instance().firePlayerChangedDimensionEvent(mpPlayer, s, newDim);
    }

    private static void transferEntityToWorld(EntityPlayer player, int currentDim, WorldServer currentWorldServer, WorldServer newWorldServer)
    {
        WorldProvider pOld = currentWorldServer.provider;
        WorldProvider pNew = newWorldServer.provider;
        double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
        double d0 = player.posX * moveFactor;
        double d1 = player.posZ * moveFactor;
        currentWorldServer.theProfiler.startSection("moving");

        currentWorldServer.theProfiler.endSection();

        currentWorldServer.theProfiler.startSection("placing");
        d0 = MathHelper.clamp_int((int) d0, -29999872, 29999872);
        d1 = MathHelper.clamp_int((int) d1, -29999872, 29999872);

        if (player.isEntityAlive())
        {
            newWorldServer.spawnEntityInWorld(player);
            player.setLocationAndAngles(d0, player.posY, d1, player.rotationYaw, player.rotationPitch);
            newWorldServer.updateEntityWithOptionalForce(player, false);
        }

        currentWorldServer.theProfiler.endSection();

        player.setWorld(newWorldServer);
    }

    private static void updateTimeAndWeatherForPlayer(EntityPlayerMP par1EntityPlayerMP, WorldServer par2WorldServer)
    {
        par1EntityPlayerMP.playerNetServerHandler.sendPacket(new S03PacketTimeUpdate(par2WorldServer.getTotalWorldTime(), par2WorldServer.getWorldTime(), par2WorldServer.getGameRules().getGameRuleBooleanValue("doDaylightCycle")));

        if (par2WorldServer.isRaining())
        {
            par1EntityPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(1, 0.0F));
            par1EntityPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(7, par2WorldServer.getRainStrength(1.0F)));
            par1EntityPlayerMP.playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(8, par2WorldServer.getWeightedThunderStrength(1.0F)));
        }
    }

    private static void syncPlayerInventory(EntityPlayerMP par1EntityPlayerMP)
    {
        par1EntityPlayerMP.sendContainerToPlayer(par1EntityPlayerMP.inventoryContainer);
        par1EntityPlayerMP.setPlayerHealthUpdated();
        par1EntityPlayerMP.playerNetServerHandler.sendPacket(new S09PacketHeldItemChange(par1EntityPlayerMP.inventory.currentItem));
    }

    public static void func_72375_a(EntityPlayerMP par1EntityPlayerMP, WorldServer par2WorldServer)
    {
        WorldServer worldserver1 = par1EntityPlayerMP.getServerForPlayer();

        if (par2WorldServer != null)
            par2WorldServer.getPlayerManager().removePlayer(par1EntityPlayerMP);

        worldserver1.getPlayerManager().addPlayer(par1EntityPlayerMP);
        worldserver1.theChunkProviderServer.loadChunk((int) par1EntityPlayerMP.posX >> 4, (int) par1EntityPlayerMP.posZ >> 4);
    }
 
    /**
     * Place an entity in a nearby portal, creating one if necessary.
     */
    @Override
    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
        if (this.worldServerInstance.provider.dimensionId != 1)
        {
            if (!this.placeInExistingPortal(par1Entity, par2, par4, par6, par8))
            {
                this.makePortal(par1Entity);
                this.placeInExistingPortal(par1Entity, par2, par4, par6, par8);
            }
        }
        else
        {
            int i = MathHelper.floor_double(par1Entity.posX);
            int j = MathHelper.floor_double(par1Entity.posY) - 1;
            int k = MathHelper.floor_double(par1Entity.posZ);
            byte b0 = 1;
            byte b1 = 0;
 
            for (int l = -2; l <= 2; ++l)
            {
                for (int i1 = -2; i1 <= 2; ++i1)
                {
                    for (int j1 = -1; j1 < 3; ++j1)
                    {
                        int k1 = i + i1 * b0 + l * b1;
                        int l1 = j + j1;
                        int i2 = k + i1 * b1 - l * b0;
                        boolean flag = j1 < 0;
                        this.worldServerInstance.setBlock(k1, l1, i2, flag ? Blocks.sandstone : Blocks.air);
                    }
                }
            }
 
            par1Entity.setLocationAndAngles((double)i, (double)j, (double)k, par1Entity.rotationYaw, 0.0F);
            par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
        }
    }
 
    /**
     * Place an entity in a nearby portal which already exists.
     */
    //@SuppressWarnings("unchecked")
    @Override
    public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
    {
        short short1 = 128;
        double d3 = -1.0D;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = MathHelper.floor_double(par1Entity.posX);
        int i1 = MathHelper.floor_double(par1Entity.posZ);
        long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
        boolean flag = true;
        double d4;
        int k1;
 
        if (this.destinationCoordinateCache.containsItem(j1))
        {
            PortalPosition portalposition = (PortalPosition)this.destinationCoordinateCache.getValueByKey(j1);
            d3 = 0.0D;
            i = portalposition.posX;
            j = portalposition.posY;
            k = portalposition.posZ;
            portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
            flag = false;
        }
        else
        {
            for (k1 = l - short1; k1 <= l + short1; ++k1)
            {
                double d5 = (double)k1 + 0.5D - par1Entity.posX;
 
                for (int l1 = i1 - short1; l1 <= i1 + short1; ++l1)
                {
                    double d6 = (double)l1 + 0.5D - par1Entity.posZ;
 
                    for (int i2 = this.worldServerInstance.getActualHeight() - 1; i2 >= 0; --i2)
                    {
                    	//System.out.println(k1+","+i2+","+l1);
                        if (this.worldServerInstance.getBlock(k1, i2, l1) == net.teamsao.mcsao.init.SAOBlocks.SAOPortalBlock)
                        {
                            while (this.worldServerInstance.getBlock(k1, i2 - 1, l1) == net.teamsao.mcsao.init.SAOBlocks.SAOPortalBlock)
                            {
                                --i2;
                            }
 
                            d4 = (double)i2 + 0.5D - par1Entity.posY;
                            double d7 = d5 * d5 + d4 * d4 + d6 * d6;
 
                            if (d3 < 0.0D || d7 < d3)
                            {
                                d3 = d7;
                                i = k1;
                                j = i2;
                                k = l1;
                            }
                        }
                    }
                }
            }
        }
 
        if (d3 >= 0.0D)
        {
            if (flag)
            {
                this.destinationCoordinateCache.add(j1, new PortalPosition(i, j, k, this.worldServerInstance.getTotalWorldTime()));
                this.destinationCoordinateKeys.add(Long.valueOf(j1));
            }
 
            double d8 = (double)i + 0.5D;
            double d9 = (double)j + 0.5D;
            d4 = (double)k + 0.5D;
            int j2 = -1;
 
            if (this.worldServerInstance.getBlock(i - 1, j, k) == SAOBlocks.SAOPortalBlock)
            {
                j2 = 2;
            }
 
            if (this.worldServerInstance.getBlock(i + 1, j, k) == SAOBlocks.SAOPortalBlock)
            {
                j2 = 0;
            }
 
            if (this.worldServerInstance.getBlock(i, j, k - 1) == SAOBlocks.SAOPortalBlock)
            {
                j2 = 3;
            }
 
            if (this.worldServerInstance.getBlock(i, j, k + 1) == SAOBlocks.SAOPortalBlock)
            {
                j2 = 1;
            }
 
            int k2 = par1Entity.getTeleportDirection();
 
            if (j2 > -1)
            {
                int l2 = Direction.rotateLeft[j2];
                int i3 = Direction.offsetX[j2];
                int j3 = Direction.offsetZ[j2];
                int k3 = Direction.offsetX[l2];
                int l3 = Direction.offsetZ[l2];
                boolean flag1 = !this.worldServerInstance.isAirBlock(i + i3 + k3, j, k + j3 + l3) || !this.worldServerInstance.isAirBlock(i + i3 + k3, j + 1, k + j3 + l3);
                boolean flag2 = !this.worldServerInstance.isAirBlock(i + i3, j, k + j3) || !this.worldServerInstance.isAirBlock(i + i3, j + 1, k + j3);
 
                if (flag1 && flag2)
                {
                    j2 = Direction.rotateOpposite[j2];
                    l2 = Direction.rotateOpposite[l2];
                    i3 = Direction.offsetX[j2];
                    j3 = Direction.offsetZ[j2];
                    k3 = Direction.offsetX[l2];
                    l3 = Direction.offsetZ[l2];
                    k1 = i - k3;
                    d8 -= (double)k3;
                    int i4 = k - l3;
                    d4 -= (double)l3;
                    flag1 = !this.worldServerInstance.isAirBlock(k1 + i3 + k3, j, i4 + j3 + l3) || !this.worldServerInstance.isAirBlock(k1 + i3 + k3, j + 1, i4 + j3 + l3);
                    flag2 = !this.worldServerInstance.isAirBlock(k1 + i3, j, i4 + j3) || !this.worldServerInstance.isAirBlock(k1 + i3, j + 1, i4 + j3);
                }
 
                float f1 = 0.5F;
                float f2 = 0.5F;
 
                if (!flag1 && flag2)
                {
                    f1 = 1.0F;
                }
                else if (flag1 && !flag2)
                {
                    f1 = 0.0F;
                }
                else if (flag1 && flag2)
                {
                    f2 = 0.0F;
                }
 
                d8 += (double)((float)k3 * f1 + f2 * (float)i3);
                d4 += (double)((float)l3 * f1 + f2 * (float)j3);
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;
                float f6 = 0.0F;
 
                if (j2 == k2)
                {
                    f3 = 1.0F;
                    f4 = 1.0F;
                }
                else if (j2 == Direction.rotateOpposite[k2])
                {
                    f3 = -1.0F;
                    f4 = -1.0F;
                }
                else if (j2 == Direction.rotateRight[k2])
                {
                    f5 = 1.0F;
                    f6 = -1.0F;
                }
                else
                {
                    f5 = -1.0F;
                    f6 = 1.0F;
                }
 
                double d10 = par1Entity.motionX;
                double d11 = par1Entity.motionZ;
                par1Entity.motionX = d10 * (double)f3 + d11 * (double)f6;
                par1Entity.motionZ = d10 * (double)f5 + d11 * (double)f4;
                par1Entity.rotationYaw = par8 - (float)(k2 * 90) + (float)(j2 * 90);
            }
            else
            {
                par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
            }
 
            par1Entity.setLocationAndAngles(d8, d9, d4, par1Entity.rotationYaw, par1Entity.rotationPitch);
            return true;
        }
        else
        {
            return false;
        }
    }
 
    @Override
    public boolean makePortal(Entity par1Entity)
    {
        byte b0 = 16;
        double d0 = -1.0D;
        int i = MathHelper.floor_double(par1Entity.posX);
        int j = MathHelper.floor_double(par1Entity.posY);
        int k = MathHelper.floor_double(par1Entity.posZ);
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = this.random.nextInt(4);
        int i2;
        double d1;
        double d2;
        int j2;
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        double d3;
        double d4;
 
        for (i2 = i - b0; i2 <= i + b0; ++i2)
        {
            d1 = (double)i2 + 0.5D - par1Entity.posX;
 
            for (j2 = k - b0; j2 <= k + b0; ++j2)
            {
                d2 = (double)j2 + 0.5D - par1Entity.posZ;
                label274:
 
                for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2)
                {
                    if (this.worldServerInstance.isAirBlock(i2, k2, j2))
                    {
                        while (k2 > 0 && this.worldServerInstance.isAirBlock(i2, k2 - 1, j2))
                        {
                            --k2;
                        }
 
                        for (i3 = l1; i3 < l1 + 4; ++i3)
                        {
                            l2 = i3 % 2;
                            k3 = 1 - l2;
 
                            if (i3 % 4 >= 2)
                            {
                                l2 = -l2;
                                k3 = -k3;
                            }
 
                            for (j3 = 0; j3 < 3; ++j3)
                            {
                                for (i4 = 0; i4 < 4; ++i4)
                                {
                                    for (l3 = -1; l3 < 4; ++l3)
                                    {
                                        k4 = i2 + (i4 - 1) * l2 + j3 * k3;
                                        j4 = k2 + l3;
                                        int l4 = j2 + (i4 - 1) * k3 - j3 * l2;
 
                                        if (l3 < 0 && !this.worldServerInstance.getBlock(k4, j4, l4).getMaterial().isSolid() || l3 >= 0 && !this.worldServerInstance.isAirBlock(k4, j4, l4))
                                        {
                                            continue label274;
                                        }
                                    }
                                }
                            }
 
                            d4 = (double)k2 + 0.5D - par1Entity.posY;
                            d3 = d1 * d1 + d4 * d4 + d2 * d2;
 
                            if (d0 < 0.0D || d3 < d0)
                            {
                                d0 = d3;
                                l = i2;
                                i1 = k2;
                                j1 = j2;
                                k1 = i3 % 4;
                            }
                        }
                    }
                }
            }
        }
 
        if (d0 < 0.0D)
        {
            for (i2 = i - b0; i2 <= i + b0; ++i2)
            {
                d1 = (double)i2 + 0.5D - par1Entity.posX;
 
                for (j2 = k - b0; j2 <= k + b0; ++j2)
                {
                    d2 = (double)j2 + 0.5D - par1Entity.posZ;
                    label222:
 
                    for (k2 = this.worldServerInstance.getActualHeight() - 1; k2 >= 0; --k2)
                    {
                        if (this.worldServerInstance.isAirBlock(i2, k2, j2))
                        {
                            while (k2 > 0 && this.worldServerInstance.isAirBlock(i2, k2 - 1, j2))
                            {
                                --k2;
                            }
 
                            for (i3 = l1; i3 < l1 + 2; ++i3)
                            {
                                l2 = i3 % 2;
                                k3 = 1 - l2;
 
                                for (j3 = 0; j3 < 4; ++j3)
                                {
                                    for (i4 = -1; i4 < 4; ++i4)
                                    {
                                        l3 = i2 + (j3 - 1) * l2;
                                        k4 = k2 + i4;
                                        j4 = j2 + (j3 - 1) * k3;
 
                                        if (i4 < 0 && !this.worldServerInstance.getBlock(l3, k4, j4).getMaterial().isSolid() || i4 >= 0 && !this.worldServerInstance.isAirBlock(l3, k4, j4))
                                        {
                                            continue label222;
                                        }
                                    }
                                }
 
                                d4 = (double)k2 + 0.5D - par1Entity.posY;
                                d3 = d1 * d1 + d4 * d4 + d2 * d2;
 
                                if (d0 < 0.0D || d3 < d0)
                                {
                                    d0 = d3;
                                    l = i2;
                                    i1 = k2;
                                    j1 = j2;
                                    k1 = i3 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }
 
        int i5 = l;
        int j5 = i1;
        j2 = j1;
        int k5 = k1 % 2;
        int l5 = 1 - k5;
 
        if (k1 % 4 >= 2)
        {
            k5 = -k5;
            l5 = -l5;
        }
 
        boolean flag;
 
        if (d0 < 0.0D)
        {
            if (i1 < 70)
            {
                i1 = 70;
            }
 
            if (i1 > this.worldServerInstance.getActualHeight() - 10)
            {
                i1 = this.worldServerInstance.getActualHeight() - 10;
            }
 
            j5 = i1;
            /* Ian - Attempt at tower generation made here, didn't work. Laggy too, but always has been really.
            //x value to the front and back of the portal
            for (k2 = -1; k2 <= 1; ++k2)
            {
            	//z value to the left and right of the portal
                for (i3 = 1; i3 < 3; ++i3)
                {
                	//y value extension below and above the portal floor
                    for (l2 = -1; l2 < 3; ++l2)
                    {
                        k3 = i5 + (i3 - 1) * k5 + k2 * l5;
                        j3 = j5 + l2;
                        i4 = j2 + (i3 - 1) * l5 - k2 * k5;
                        flag = l2 < 0;
                        //System.out.println("When generating the portal frame x y and z are: (x:"+k3+", y:"+j3+", z:"+i4+")");
                        this.worldServerInstance.setBlock(k3, j3, i4, flag ? Blocks.sandstone : Blocks.air);
                    }
                }
            }*/
        }
 
        for (k2 = 0; k2 < 4; ++k2)
        {
            for (i3 = 0; i3 < 4; ++i3)
            {
                for (l2 = -1; l2 < 4; ++l2)
                {
                    k3 = i5 + (i3 - 1) * k5;
                    j3 = j5 + l2;
                    i4 = j2 + (i3 - 1) * l5;
                    flag = i3 == 0 || i3 == 3 || l2 == -1 || l2 == 3;
                    this.worldServerInstance.setBlock(k3, j3, i4, flag ? Blocks.sandstone : SAOBlocks.SAOPortalBlock, 0, 2);
                    //System.out.println("When generating the portal blocks x y and z are: (x:"+k3+", y:"+j3+", z:"+i4+")");
                }
            }
 
            for (i3 = 0; i3 < 4; ++i3)
            {
                for (l2 = -1; l2 < 4; ++l2)
                {
                    k3 = i5 + (i3 - 1) * k5;
                    j3 = j5 + l2;
                    i4 = j2 + (i3 - 1) * l5;
                    this.worldServerInstance.notifyBlocksOfNeighborChange(k3, j3, i4, this.worldServerInstance.getBlock(k3, j3, i4));
                }
            }
        }
 
        return true;
    }
 
    /**
     * called periodically to remove out-of-date portal locations from the cache list. Argument par1 is a
     * WorldServer.getTotalWorldTime() value.
     */
    @SuppressWarnings("rawtypes")
    @Override
    public void removeStalePortalLocations(long par1)
    {
        if (par1 % 100L == 0L)
        {
            Iterator iterator = this.destinationCoordinateKeys.iterator();
            long j = par1 - 600L;
 
            while (iterator.hasNext())
            {
                Long olong = (Long)iterator.next();
                PortalPosition portalposition = (PortalPosition)this.destinationCoordinateCache.getValueByKey(olong.longValue());
 
                if (portalposition == null || portalposition.lastUpdateTime < j)
                {
                    iterator.remove();
                    this.destinationCoordinateCache.remove(olong.longValue());
                }
            }
        }
    }
    
    public class PortalPosition extends ChunkCoordinates
    {
        /** The worldtime at which this PortalPosition was last verified */
        public long lastUpdateTime;
 
        public PortalPosition(int par2, int par3, int par4, long par5)
        {
            super(par2, par3, par4);
            this.lastUpdateTime = par5;
        }
    }
}