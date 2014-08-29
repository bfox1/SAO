package net.teamsao.mcsao.help;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

/**
 * Class which will provide math helper methods necessary to make simple to complex geometric shapes with block
 * formations and might also have in the future generic structure gen methods for use as the API.
 * @author Ian
 *
 */
public class StructureGenHelper
{
	/**
	 * This is a method to get the distance between two 2 dimensional points, essentially on the minecraft horizontal axis.
	 * @param x1
	 * @param z1
	 * @param x2
	 * @param z2
	 * @return the integer-rounded distance between the two 2D points.
	 */
	public static Integer distance2D(double x1, double z1, double x2, double z2)
    {
    	double xDelta = Math.abs(x2-x1);
    	double zDelta = Math.abs(z2-z1);
    	double distance = Math.sqrt(xDelta*xDelta + zDelta*zDelta);
    	return (int) distance;
    }
	
	/**
	 * This method is exactly like the other distance2D method, except that it forgoes the need for calculating the
	 * difference between the two points, and relies instead on the idea that the user knows what the difference is
	 * from other information.
	 * @param xD
	 * @param zD
	 * @return the integer-rounded distance between two 2D points, using the deltas.
	 */
	public static Integer distance2D(double xD, double zD)
	{
		double xDelta = Math.abs(xD);
		double zDelta = Math.abs(zD);
		double distance = Math.sqrt(xDelta*xDelta + zDelta*zDelta);
    	return (int) distance;
	}
	
	/**
	 * This method is used to get the distance between a pair of 3D coordinates. It assumes that an array
	 * is the cheapest and fastest way to store the data in order.
	 * @param origin
	 * @param point
	 * @return the integer-rounded distance between two 3D points.
	 */
	public static Integer distance3D(double[] origin, double[] point)
	{
		if(origin == null || point == null || origin.length != 3 || point.length != 3)
		{
			//Do not use null data or those openly visible to not have the right amount of data.
			return null;
		}
		try
		{
			//Also check and be sure that no indices of the arrays are null. (later)
			double xDelta = Math.abs(origin[0]-point[0]);
			double yDelta = Math.abs(origin[1]-point[1]);
			double zDelta = Math.abs(origin[2]-point[2]);
			double distance = Math.sqrt(Math.pow(xDelta, 2) + Math.pow(yDelta, 2) + Math.pow(zDelta, 2));
			return (int) Math.round(distance);
		}
		catch(Exception e)
		{
			System.out.println("Bad variable for 3D distance somewhere inside the coordinate arrays.");
			System.out.println(e);
			return null;
		}
	}
	
	/**
	 * Block neighbor checker. This is used to determine whether the immediate neighbors (not corner neighbors)
	 * of the block at the coordinates are empty or not. Each check will short circuit to false if it finds
	 * a block there, but will still continue down the line if it doesn't.
	 * @author Ian
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return true if the block's immediate neighbors have blocks in them, false if not.
	 */
	public static boolean blockHasNeighbors(World world, int x, int y, int z)
	{
		return world.getBlock(x+1, y, z) != Blocks.air ? false :
			   world.getBlock(x-1, y, z) != Blocks.air ? false :
			   world.getBlock(x, y+1, z) != Blocks.air ? false :
			   world.getBlock(x, y-1, z) != Blocks.air ? false :
			   world.getBlock(x, y, z+1) != Blocks.air ? false :
			   world.getBlock(x, y, z-1) != Blocks.air ? false : true;
	}
	
	/**
	 * To try to clarify, this is not checking the actual floor, but the blocks just above the floor, which would
	 * conflict with the other checking method because of a downward search. Otherwise, the searching method
	 * is exactly the same. This usage assumes that schema will be built into the floor rather than just or more 
	 * than just above it.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return true if this block just above the floor has a neighbor, false if not.
	 */
	public static boolean floorBlockHasNeighbors(World world, int x, int y, int z)
	{
		return world.getBlock(x+1, y, z) != Blocks.air ? false :
			   world.getBlock(x-1, y, z) != Blocks.air ? false :
			   world.getBlock(x, y+1, z) != Blocks.air ? false :
			   world.getBlock(x, y, z+1) != Blocks.air ? false :
			   world.getBlock(x, y, z-1) != Blocks.air ? false : true;
	}
	
	/**
	 * And now, the hard part. This method needs to implement adding blocks directly into the chunks rather
	 * than using the setBlock method which horribly badly lags. The method involves going across block
	 * coordinates and checking each step whether or not the block coordinates are within the currently defined
	 * chunk object. If it goes out of bounds, gather the new chunk which the block coordinates are defined
	 * inside, and by definition gather the newest ExtendedBlockStorage object. For the walls and other more
	 * vertical structures than the floor this can range upward into a new ExtendedBlockStorage object before it
	 * switches chunks. In fact, it's probably best to do x, z, y rather than y, x, z (vertical columns finish
	 * before horizontal layers), because it means less calls to grab a new chunk. One chunk will finish building
	 * the structure entirely before moving on.
	 * 
	 * But of course I forgot that each block might have a metadata associated with it that each block class no
	 * longer has any access to. Thus, BlockData 3D array rather than Block 3D array.
	 * 
	 * @author Ian
	 * @param world
	 * @param schema
	 * @param xStart
	 * @param zStart
	 */
	public static void placeSchema(World world, BlockData[][][] schema, int xStart, int zStart)
	{
		int yStart = 39;
		int xBounds = schema.length+xStart;
		int zBounds = schema[0][0].length+zStart;
		int yBounds = schema[0].length+yStart;
		Chunk chunk = world.getChunkFromBlockCoords(xStart, zStart);
		ExtendedBlockStorage miniChunk = chunk.getBlockStorageArray()[yStart >> 4];
		int chunkXStart = chunk.xPosition << 4;
		int chunkZStart = chunk.zPosition << 4;
		int miniChunkYStart = miniChunk.getYLocation();
		for(int x = xStart; x < xBounds; x++)
		for(int z = zStart; z < zBounds; z++)
		{
			if(z >> 4 != chunk.zPosition || x >> 4 != chunk.xPosition)
			{
				chunk = world.getChunkFromBlockCoords(x, z);
				miniChunk = chunk.getBlockStorageArray()[yStart >> 4];
			}
			for(int y = yStart; y < yBounds; y++)
			{
				if(y > miniChunkYStart + 15)
				{
					miniChunk = chunk.getBlockStorageArray()[y >> 4];
					miniChunkYStart = miniChunk.getYLocation();
				}
				miniChunk.func_150818_a(x & 15, y & 15, z & 15, schema[x][y][z].getBlock());
				miniChunk.setExtBlockMetadata(x & 15, y & 15, z & 15, schema[x][y][z].getMetadata());
				if(!world.isRemote)
				{
					world.markBlockForUpdate(x, y, z);
					world.notifyBlockChange(x, y, z, schema[x][y][z].getBlock());
				}
			}
		}
	}
	
	/**
	 * Reads in a schema in 3D Block Array form, checks to see if the placement location is valid, attempts minor
	 * corrections if initial attempts fail, and then when it finally has a valid location it iterates through
	 * the schema and places non-null blocks.
	 * @author Ian
	 * @param world
	 * @param schema
	 * @param xStart
	 * @param zStart
	 * @return true if the schema placement succeeded, false if slight offset did not help placement and still
	 * failed.
	 */
	public static boolean checkSchema(World world, BlockData[][][] schema, int xStart, int zStart)
	{
		if(checkBounds(world, schema, xStart, zStart))
		{
			placeSchema(world, schema, xStart, zStart);
			return true;
		}
		else if(checkBounds(world, schema, xStart+2, zStart+2))
		{
			placeSchema(world, schema, xStart+2, zStart+2);
			return true;
		}
		else if(checkBounds(world, schema, xStart-2, zStart-2))
		{
			placeSchema(world, schema, xStart-2, zStart-2);
			return true;
		}
		else if(checkBounds(world, schema, xStart+2, zStart-2))
		{
			placeSchema(world, schema, xStart+2, zStart-2);
			return true;
		}
		else if(checkBounds(world, schema, xStart-2, zStart+2))
		{
			placeSchema(world, schema, xStart-2, zStart+2);
			return true;
		}
		//At one point, figure out how to log an error to FML and do so here.
		return false;
	}
	
	/**
	 * Checks to see at what point from the starting y level at these x and z coordinates the defined "hole"
	 * reaches its bottom. It could, in fact, return 0 if you done goofed and it's not actually a hole.
	 * @author Ian
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return the number of blocks from this block that the floor of the hole is.
	 */
	public static int blocksDownToFloor(World world, int x, int y, int z)
	{
		int startY = y;
		int blocksDown = 0;
		if(world.getBlock(x, y, z) == Blocks.air)
		{
			return blocksDown;
		}
		while(startY > 0)
		{
			blocksDown++;
			startY--;
			if(world.getBlock(x, startY, z) != Blocks.air)
			{
				return blocksDown;
			}
		}
		return blocksDown;
	}
	
	/**
	 * Checks to see at what point from the starting y level at these x and z coordinates the defined "hole"
	 * reaches its bottom. It could, in fact, return 0 if you done goofed and it's not actually a hole.
	 * This variant uses a BlockData object instead of world coordinates. Works better with the recursive
	 * method.
	 * @author Ian
	 * @param world
	 * @param block - A BlockData object used in place of coordinates.
	 * @return the number of blocks from this block that the floor of the hole is.
	 */
	public static int blocksDownToFloor(World world, BlockData block)
	{
		int startY = block.getBlockY();
		int blocksDown = 0;
		if(block.getBlock() == Blocks.air)
		{
			return blocksDown;
		}
		while(startY > 0)
		{
			blocksDown++;
			startY--;
			if(world.getBlock(block.getBlockX(), startY, block.getBlockZ()) != Blocks.air)
			{
				return blocksDown;
			}
		}
		return blocksDown;
	}
	
	/**
	 * This method is just used to shorten repetitive code. Also makes it easier to use block data elsewhere.
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return a new BlockData object which contains the block type, metadata, and entered coordinates.
	 */
	public static BlockData getBlockDataAt(IBlockAccess world, int x, int y, int z)
	{
		return new BlockData(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z), x, y, z);
	}
	
	/**
	 * This method is used to determine the block types in the immediate horizontal area around the block.
	 * Thanks to the BlockData method it can also be used to determine block metadata value.
	 * @author Ian
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return the BlockData array listing all four neighbors of the block at the listed coordinates.
	 */
	public static BlockData[] blockHorizontalNeighbors(World world, int x, int y, int z)
	{
		BlockData[] neighbors = new BlockData[4];
		neighbors[0] = getBlockDataAt(world, x+1, y, z);
		neighbors[1] = getBlockDataAt(world, x-1, y, z);
		neighbors[2] = getBlockDataAt(world, x, y, z+1);
		neighbors[3] = getBlockDataAt(world, x, y, z-1);
		return neighbors;
	}
	
	/**
	 * Thankfully, this method now returns a BlockData array so each data point knows where it is and what it is.
	 * This can be used to find special block types in neighboring blocks and possibly used in recursion to determine
	 * a pattern of custom blocks.
	 * @author Ian
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return an array of blocks with the list of all immediate neighbors to the block at the given coordinates.
	 */
	public static BlockData[] blockNeighbors(World world, int x, int y, int z)
	{
		BlockData[] neighbors = new BlockData[6];
		neighbors[0] = getBlockDataAt(world, x+1, y, z);
		neighbors[1] = getBlockDataAt(world, x-1, y, z);
		neighbors[2] = getBlockDataAt(world, x, y+1, z);
		neighbors[3] = getBlockDataAt(world, x, y-1, z);
		neighbors[4] = getBlockDataAt(world, x, y, z+1);
		neighbors[5] = getBlockDataAt(world, x, y, z-1);
		return neighbors;
	}
	
	/**
	 * Recursive method to determine whether neighbor blocks also have sheer drop-like holes, which in large
	 * enough amounts creates the pattern of a cliff. This threshold is likely to be tuned as it is tested, but
	 * for now the threshold for how many really big holes you can find before this thing shorts to true is 5.
	 * This method uses a "traversed" block data list to determine whether or not a block has already been gone over
	 * in the current step of recursion. It has the possibility of going over some blocks more than once, but it's
	 * better than not being able to find a hole because a path has been blocked.
	 * 
	 * @author Ian
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param count
	 * @return true if a large amount of sheer drops is found, false if the pattern ends early on in any direction.
	 */
	public static boolean isLargeDrop(World world, int x, int y, int z, int count, ArrayList<BlockData> traversed)
	{
		if(count >= 5)
		{
			return true;
		}
		BlockData block1 = getBlockDataAt(world, x+1, y, z);
		if(!traversed.contains(block1))
		{
			traversed.add(block1);
			if(world.getBlock(x+1, y, z) == Blocks.air && blocksDownToFloor(world, block1) >= 6)
			{
				return isLargeDrop(world, x+1, y, z, count++, BlockData.copyList(traversed));
			}
		}
		BlockData block2 = getBlockDataAt(world, x-1, y, z);
		if(!traversed.contains(block2))
		{
			traversed.add(block2);
			if(world.getBlock(x-1, y, z) == Blocks.air && blocksDownToFloor(world, block2) >= 6)
			{
				return isLargeDrop(world, x-1, y, z, count++, BlockData.copyList(traversed));
			}
		}
		BlockData block3 = getBlockDataAt(world, x, y, z+1);
		if(!traversed.contains(block3))
		{
			traversed.add(block3);
			if(world.getBlock(x, y, z+1) == Blocks.air && blocksDownToFloor(world, block3) >= 6)
			{
				return isLargeDrop(world, x, y, z+1, count++, BlockData.copyList(traversed));
			}
		}
		BlockData block4 = getBlockDataAt(world, x, y, z-1);
		if(!traversed.contains(block4))
		{
			traversed.add(block4);
			if(world.getBlock(x, y, z-1) == Blocks.air && blocksDownToFloor(world, block4) >= 6)
			{
				return isLargeDrop(world, x, y, z-1, count++, BlockData.copyList(traversed));
			}
		}
		return false;
	}
	
	/**
	 * Method to check and see if the floor is valid. You don't build a structure on the edge of a cliff
	 * because it would tip over or fall outright. Another bad example of a floor is if there are holes in it in 
	 * a significant amount. Small (1-2 blocks) holes are acceptable because you'd cover them anyway, but deep 
	 * holes and sheer drops are bad because they indicate a cliff or at least a newb hole.
	 * @author Ian
	 * @param world
	 * @param schema
	 * @param xStart
	 * @param zStart
	 * @return true if there is no sudden drop into the abyss or large (>= 25% surface area) amount of holes, 
	 * otherwise return false.
	 */
	public static boolean hasFloor(World world, BlockData[][][] schema, int xStart, int zStart)
	{
		int yStart = 39;
		int xBounds = schema[0].length+xStart;
		int zBounds = schema[0][0].length+zStart;
		int holeCount = 0;
		
		for(int x = xStart; x < xBounds; x++)
		{
			for(int z = zStart; z < zBounds; z++)
			{
				if(world.getBlock(x, yStart, z) == Blocks.air)
				{
					int holeDepth = blocksDownToFloor(world, x, yStart, z);
					if(holeDepth >= 6)
					{
						holeCount++;
						ArrayList<BlockData> traversed = new ArrayList<BlockData>();
						traversed.add(getBlockDataAt(world, x, yStart, z));
						if(isLargeDrop(world, x, yStart, z, 0, traversed))
						{
							return false;
						}
					}
					else if(holeDepth > 2)
					{
						holeCount++;
					}
				}
			}
		}
		return ((holeCount / (float)(xBounds*zBounds))*100.0F) < 25.0F;
	}
	
	/**
	 * Use the bounds of the schema in order to determine whether it's ok to go ahead and place the schema at
	 * the current given location or not. Note: The way this works it expands in all positive axes rather than
	 * in a particular direction. However, it also does not treat the schema like a box. If there's nothing
	 * in a coordinate in the schema then it doesn't care whether there's a block at those coordinates. It will
	 * also check for block neighbors to ensure there there is no structure overlap (besides the floor) and the
	 * floor for an alarming drop.
	 * @author Ian
	 * @param world
	 * @param schema - Generated in the SchematicHelper class from a .schematic file, it can also be trimmed to
	 * allow for the null block spots as mentioned in this method's description.
	 * @param xStart
	 * @param zStart
	 * @return true if there is no direct overlap or overlap from immediate neighbors and false otherwise.
	 */
	public static boolean checkBounds(World world, BlockData[][][] schema, int xStart, int zStart)
	{
		if(schema == null)
		{
			return false;
		}
		int yStart = 39;
		int xBounds = schema[0].length+xStart;
		int zBounds = schema[0][0].length+zStart;
		int yBounds = schema.length+yStart;
		if(yBounds > 255 || yStart < 0)
		{
			return false;
		}
		if(xStart > 30000000 || xStart < -30000000 || zStart > 30000000 || zStart < -30000000)
		{
			return false;
		}
		if(xBounds > 30000000 || xBounds < -30000000 || zBounds > 30000000 || zBounds < -30000000)
		{
			return false;
		}
		if(!hasFloor(world, schema, xStart, zStart))
		{
			return false;
		}
		for(int y = yStart+1; y < yBounds; y++)
		for(int x = xStart; x < xBounds; x++)
		for(int z = zStart; z < zBounds; z++)
		{
			if(schema[y][x][z] == null)
			{
				continue;
			}
			if(world.getBlock(x, y, z) != Blocks.air)
			{
				return false;
			}
			else if(y > yStart+1 && blockHasNeighbors(world, x, y, z))
			{
				return false;
			}
			else if(y > yStart && floorBlockHasNeighbors(world, x, y, z))
			{
				return false;
			}
		}
		return true;
	}
    
}