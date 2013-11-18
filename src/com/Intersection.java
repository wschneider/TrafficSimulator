package com;

public abstract class Intersection extends RoadFeature {
	/*
	 * Subclasses:
	 * ( ) Stop Sign Intersection,
	 * ( ) Stop Light Intersection,
	 * ( ) Cloverleaf Intersection,
	 * ( ) Optional Intersection.
	 */

	public Road targetRoad;
	public Road sourceRoad;
	public Lane targetLane;
	
	public double targetSpeed; 
	public double locOnTarget;
	
	//startLoc, endLoc, serialNum inherited from superclass
	
	public Intersection(double start, double end, double loc, Road source, Road target)
	{
		super(start,end);
		
		this.sourceRoad = source;
		this.targetRoad = target;
		this.targetLane = this.targetRoad.getLanes().get(0);
		this.locOnTarget = loc;
	}
	
	
}
