package com;

public abstract class RoadFeature {
	/*
	 * Road Features:
	 *     Stop Signs
	 *     Intersections
	 *     End of Lane
	 *     End of Road
	 */
	
	private int serialNum;
	public double startLoc, endLoc;
	
	public abstract void performAction(Car target, double timeStep);
	
	public RoadFeature(double start, double end)
	{
		SerialFactory n = SerialFactory.getInstance();
		this.serialNum = n.getNewSerial();
		
		this.startLoc = start;
		this.endLoc = end;
		
	}

	public int getSerialNum() {
		return serialNum;
	}
}
