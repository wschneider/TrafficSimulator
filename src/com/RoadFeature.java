package com;

public abstract class RoadFeature {
	/*
	 * Road Features:
	 *     Stop Signs
	 *     Intersections
	 *     Caution Signs
	 *     End of Lane
	 *     End of Road
	 */
	
	public double startLoc, endLoc;
	public boolean isStop;
	
	public abstract void performAction();
	
}
