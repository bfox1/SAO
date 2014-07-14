package net.teamsao.mcsao.help;

/**
 * Class which will provide math helper methods necessary to make simple to complex geometric shapes with block
 * formations and might also have in the future generic structure gen methods for use as the API.
 * @author Ian
 *
 */
public class StructureGenHelper
{
	public static Integer distance2D(double x1, double z1, double x2, double z2)
    {
    	double xDelta = Math.abs(x2-x1);
    	double zDelta = Math.abs(z2-z1);
    	double distance = Math.sqrt(xDelta*xDelta + zDelta*zDelta);
    	return (int) distance;
    }
	
	public static Integer distance2D(double xD, double zD)
	{
		double xDelta = Math.abs(xD);
		double zDelta = Math.abs(zD);
		double distance = Math.sqrt(xDelta*xDelta + zDelta*zDelta);
    	return (int) distance;
	}
	
	public static Integer distance3D(double[] origin, double[] point)
	{
		if(origin.length != 3 || point.length != 3)
		{
			//This should not happen. Will write more code later to make sure of it.
		}
		double xDelta = Math.abs(origin[0]-point[0]);
		double yDelta = Math.abs(origin[1]-point[1]);
		double zDelta = Math.abs(origin[2]-point[2]);
		double distance = Math.sqrt(Math.pow(xDelta, 2) + Math.pow(yDelta, 2) + Math.pow(zDelta, 2));
		return (int) Math.round(distance);
	}
}