/*
 * Title: Lane.java
 * Author: Will Schneider
 * 
 * Purpose:
 *     A Lane is the fundamental container of travel. An individual Lane will contain a list of cars, representing the 
 * in-order cars in the lane. 
 * 
 * Class Outline:
 *     Positioning:
 *         Variables:
 *             final double length: the length, in feet, of this road
 *             final int X: X position on the global grid
 *             final int Y: Y position on the global grid
 *             final double Direction: angle, relative to North, that this road runs. 
 *         Methods:
 *             double getLength(): returns this.length
 *             int getX(): returns this.X
 *             int getY(): returns this.Y
 *             double getDirection(): returns this.Direction
 *     Utility: 
 *         Variables:
 *             final int serialNum: an integer that will be unique to each item in the program for state-saving functionality
 *         Functions:
 *             int getSerialNum(): returns this.serialNum
 *             void Print():       prints the lanes on this road, and all the cars in that lane. 
 *     Road Functionality:
 *         Variables: 
 *             ArrayList<RoadFeature> features:       An array of non-intersection features on this road. 
 *             ArrayList<Intersection> intersections: An array of intersection objects that represent roads this road intersects
 *             ArrayList<Lane> lanes:                 An array of the lanes that comprise this road
 *         Functions:
 *             ArrayList<RoadFeature> getFeatures(): returns this.features
 *             ArrayList<Lane> getLanes(): returns this.lanes
 *             int numCars(): returns the cars in all of the lanes on this road.
 *             void pushCar(int, Car): pushes a car into the lane specified by int
 *     Intersection Functionality:
 *	       Variables:
 *		       ArrayList<Intersection> intersections: An array of intersection objects that represent roads this road intersects
 *		   Functions:
 *			   ArrayList<Intersection> getIntersections(): returns this.intersections
 *			   Intersection getNextIntersection(Car): returns the next intersection after the position of this car
 *			   void addStopSignIntersection(double, double, Road): adds an intersection with another road at source and target locations
 *             
 * 
 */

package com;
import java.util.ArrayList;
import java.util.ListIterator;

public class Road{

	/**************************************************************************
	 * POSITIONING VARIABLES AND FUNCTIONS:
	 **************************************************************************/
	
    private final int length;
    private final int X;
    private final int Y;
    private final double Direction;
    
    public int getLength(){ 
    	return this.length; 
    }
    
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public double getDirection() {
		return Direction;
	}
    
 
	/**************************************************************************
	 * CLASS UTILITY VARIABLES AND FUNCTIONS:
	 **************************************************************************/

    private int serialNum;
    
	public int getSerialNum() {
		return serialNum;
	}
    
    public void print() {
        for(Lane lane : lanes)
        {
            System.out.println("Lane:");
            lane.print();
        }
    }
    
	/**************************************************************************
	 * ROAD FUNCTIONALITY VARIABLES AND FUNCTIONS:
	 **************************************************************************/
    
    
    private ArrayList<RoadFeature> features;
    private ArrayList<Lane> lanes;
    
    public ArrayList<RoadFeature> getFeatures() {
    	return this.features;
    }
    
    public ArrayList<Lane> getLanes() {
    	return this.lanes;
    }

    public int numCars() {
    	int sum = 0;
    	for (Lane lane : lanes)
    	{
    		sum += lane.numCars();
    	}
    	return sum;
    }
    
    public void pushCar(int laneNum, Car nCar) {
    	if(0 <= laneNum && laneNum < this.lanes.size())
        {
            this.lanes.get(laneNum).addCar(nCar);
        }
    }
    
    /**************************************************************************
	 * INTERSECTION FUNCTIONALITY VARIABLES AND FUNCTIONS:
	 **************************************************************************/
    
    private ArrayList<Intersection> intersections;
  
    public ArrayList<Intersection> getIntersections() {
    	return this.intersections;
    }
    
    public Intersection getNextIntersection(Car car) {
    	ListIterator<Intersection> iter = intersections.listIterator(0);
    	
    	while(iter.hasNext())
    	{
    		Intersection current = iter.next();
    		if(current.endLoc > car.position){
    			return current;
    		}
    	}
    	
    	return null;
    }
    
    public void addStopSignIntersection(Road target, double positionSource, double positionTarget) {
    	/*
    	 * LATER TO BE IMPLEMENTED:
    	 *		Sanity checks that X and Y values (used in rendering) check out with intersections of roads 
    	 */
    	
    	//distance = rate * time
    	//         = speedLimit (f/s) * 10 (s)
    	double stoppingDist = 10 * this.lanes.get(0).getSpeedLimit();
    	
    	StopSignIntersection a = new StopSignIntersection(positionSource-stoppingDist, positionSource, positionTarget, this, target);
    	
    	this.intersections.add(a);
    }
    
    /**************************************************************************
  	 * UNDOCUMENTED VARIABLES AND FUNCTIONS:
  	 **************************************************************************/
    
    public Road(int length, int X, int Y, Double direction) {
    	SerialFactory n = SerialFactory.getInstance();
    	this.length = length;
    	this.X = X;
    	this.Y = Y;
    	this.Direction = direction;
    	this.serialNum = n.getNewSerial();
    	this.features = new ArrayList<RoadFeature>();
        this.intersections = new ArrayList<Intersection>();
        this.lanes = new ArrayList<Lane>();
    }


    
    public void createLane(double start, double end, double speedLimit) {
    	SerialFactory n = SerialFactory.getInstance();
    	Lane l = new Lane(start, end, speedLimit, n.getNewSerial(), this);
    	this.lanes.add(l);
    }
    
}