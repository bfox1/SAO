package net.teamsao.mcsao.world.generation;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;
import net.teamsao.mcsao.world.generation.SAOTowerGen;

/**
 * 
 * @author Ian
 * Pretty sure this class doesn't have any desirable features for us yet. It's basically just here as a placeholder
 * in case we want to add ores later.
 */
public class SAOWorldGenerator implements IWorldGenerator
{
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
    	
    	//System.out.println("Is this class even being used?!");
        switch (world.provider.dimensionId)
        {
        case -1:
            generateNether(world, random, chunkX * 16, chunkZ * 16);
        case 0:
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
        case 1:
            generateEnd(world, random, chunkX * 16, chunkZ * 16);
        case 2:
        	generateAincrad(world, random, chunkX * 16, chunkZ * 16);
        }
    }
 
    private void generateEnd(World world, Random random, int x, int z)
    {
    	
    }
 
    private void generateSurface(World world, Random random, int x, int z)
    {
        //this.addOreSpawn(Tutorial.tutorialBlock, world, random, x, z, 16, 16, 4 + random.nextInt(3), 5, 15, 50);
    }
 
    private void generateNether(World world, Random random, int x, int z)
    {
       /* int Xcoord = x + random.nextInt(16);
        int Ycoord = 10 + random.nextInt(128);
        int Zcoord = z + random.nextInt(16);
        (new WorldGenMinable(Tutorial.tutorialBlock, 1, 15, Blocks.netherrack)).generate(world, random, Xcoord, Ycoord, Zcoord);*/
    }
    
    /**
     * 
     * In here goes the execution of another IWorldGenerator implementation which *will* contain the code for structure gen
     * of the tower.
     * 
     * @param world
     * @param rand
     * @param blockX
     * @param blockZ
     */
    private void generateAincrad(World world, Random rand, int blockX, int blockZ)
    {
    	WorldGenerator towerGen = new SAOTowerGen();
    	towerGen.generate(world, rand, blockX, blockZ, 0);
    }
 
    /**
     * Adds an Ore Spawn to Minecraft. Simply register all Ores to spawn with this method in your Generation method
     * in your IWorldGeneration implementing Class
     * 
     * @param The Block to spawn
     * @param The World to spawn in
     * @param A Random object for retrieving random positions within the world to spawn the Block
     * @param An int for passing the X-Coordinate for the Generation method
     * @param An int for passing the Z-Coordinate for the Generation method
     * @param An int for setting the maximum X-Coordinate values for spawning on the X-Axis on a Per-Chunk basis
     * @param An int for setting the maximum Z-Coordinate values for spawning on the Z-Axis on a Per-Chunk basis
     * @param An int for setting the maximum size of a vein
     * @param An int for the Number of chances available for the Block to spawn per-chunk
     * @param An int for the minimum Y-Coordinate height at which this block may spawn
     * @param An int for the maximum Y-Coordinate height at which this block may spawn
     **/
    public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
    {
        assert maxY > minY : "The maximum Y must be greater than the Minimum Y";
        assert maxX > 0 && maxX <= 16 : "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
        assert minY > 0 : "addOreSpawn: The Minimum Y must be greater than 0";
        assert maxY < 256 && maxY > 0 : "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
        assert maxZ > 0 && maxZ <= 16 : "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";
 
        int diffBtwnMinMaxY = maxY - minY;
        for (int x = 0; x < chancesToSpawn; x++)
        {
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(diffBtwnMinMaxY);
            int posZ = blockZPos + random.nextInt(maxZ);
            (new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
        }
    }
}