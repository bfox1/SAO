package net.teamsao.mcsao.helper;

public class FloorPoint
{
	private int x, y, z;
	private int floorNumber;
	private int floorRadius;
	private int wallThickness;
	private int wallEnd;
	private double floorAmplitude;

	public FloorPoint(int floorNumber, int x, int y, int z, int radius, int wallThickness, double floorAmplitude)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.floorNumber = floorNumber;
		this.floorRadius = radius;
		this.wallThickness = wallThickness;
		wallEnd = floorRadius+wallThickness;
		this.floorAmplitude = floorAmplitude;
	}
	
	public double getFloorAmplitude()
	{
		return floorAmplitude;
	}

	public void setFloorAmplitude(double floorAmplitude)
	{
		this.floorAmplitude = floorAmplitude;
	}

	public int getPosXBoundary()
	{
		return x+wallEnd;
	}
	
	public int getNegXBoundary()
	{
		return x-wallEnd;
	}
	
	public int getPosZBoundary()
	{
		return z+wallEnd;
	}
	
	public int getNegZBoundary()
	{
		return z-wallEnd;
	}
	
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getZ()
	{
		return z;
	}

	public void setZ(int z)
	{
		this.z = z;
	}

	public int getFloorNumber()
	{
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber)
	{
		this.floorNumber = floorNumber;
	}

	public int getFloorRadius()
	{
		return floorRadius;
	}

	public void setFloorRadius(int floorRadius)
	{
		this.floorRadius = floorRadius;
	}

	public int getWallThickness()
	{
		return wallThickness;
	}

	public void setWallThickness(int wallThickness)
	{
		this.wallThickness = wallThickness;
	}

	public int getWallEnd()
	{
		return wallEnd;
	}

	public void setWallEnd(int wallEnd)
	{
		this.wallEnd = wallEnd;
	}
}