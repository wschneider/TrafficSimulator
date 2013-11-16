package com;

public abstract class RoadFeature {
	/*
	 * Road Features:
	 *     Stop Signs
	 *     Intersections
	 *     End of Lane
	 *     End of Road
	 */
	
	public double startLoc, endLoc;
	public boolean isStop;
	
	public abstract void performAction(Car target);
	
}
