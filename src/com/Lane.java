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
 *             final double start: a double representing the location in feet on the road that this lane starts
 *             final double end:   a double representing the location in feet on the road that this lane finishes
 *         Methods:
 *             double getStart(): returns this.start
 *             double getEnd():   returns this.end
 *     Utility: 
 *         Variables:
 *             final int serialNum: an integer that will be unique to each item in the program for state-saving functionality
 *         Functions:
 *             int getSerialNum(): returns this.serialNum
 *             void Print():       prints the in-order cars in this lane
 *     Road Functionality:
 *         Variables: 
 *             final Road holdingRoad:          A reference to the lane thats holding this road
 *             ArrayList<RoadFeature> features: an array of RoadFeatures that cars in this lane may have to interact with
 *             ArrayList<Lane> borderingLanes:  an array of Lanes that cars in this lane can merge into (SHOULD only have 2 at any given foot)
 *             LinkedList<Car> cars:            an array of Cars in this lane
 *             final double speedLimit:         The speed limit in this lane
 *         Functions:
 *             double getSpeedLimit(): returns this.speedLimit
 *             Road getHoldingRoad():  returns this.holdingRoad
 *             int numCars():          returns this.cars.size()
 *             Car nextCar(Car):       given a car, return the next car in front of this car, null if it doesnt exist. Raise an error if not in this lane. 
 *             void addCar(Car):       adds a car to the lane in the appropriate location
 *             
 * 
 */

package com;

import java.util.*;

public class Lane{

	/**************************************************************************
	 * POSITIONING VARIABLES AND FUNCTIONS:
	 **************************************************************************/
	private final double start, end;
	
	public double getStart() {
		return start;
	}

	public double getEnd() {
		return end;
	}
	
	/**************************************************************************
	 * CLASS UTILITY VARIABLES AND FUNCTIONS:
	 **************************************************************************/
	
	private final int serialNum;
	
	public int getSerialNum() 
	{
		return serialNum;
	}
    
	public void print()
    {
        //prints all of the cars in the lane
        for(Car car : cars)
        {
            car.print();
        }
    }
	
	/**************************************************************************
	 * ROAD FUNCTIONALITY VARIABLES AND FUNCTIONS:
	 **************************************************************************/
	
	private final Road holdingRoad;
	
    public ArrayList<RoadFeature> features;
    
    public ArrayList<Lane> borderingLanes;
    
    public LinkedList<Car> cars;
    
    private final double speedLimit;
    
	public double getSpeedLimit()
	{
		return this.speedLimit;
	}

	public Road getHoldingRoad() {
		return holdingRoad;
	}
    
    public int numCars()
    {
    	return this.cars.size();
    }
    
    /*
     * DEPRECATED: Replaced below
     */
    public Car nextCar1(Car n)
    {
        //Returns the next car in this lane, immediately in front of car n. Returns null if no such car exists or if n is not in this lane
        int index = cars.indexOf(n);
        if (index == -1)
        {
            return null;
        }

        ListIterator<Car> iter = cars.listIterator(index);
        if (!iter.hasNext())
        {
            return null;
        }

        return iter.next();
    }

    public Car nextCar(Car n)
    {
    	ListIterator<Car> iter = cars.listIterator(0);
    	
    	while(iter.hasNext())
    	{
    		Car current = iter.next();
    		if(iter.hasNext() && current.serialNum == n.serialNum)
    		{
    			//if this car isn't the last in the list
    			return iter.next();
    		}
    	}
    	
    	return null;
    }
	
    public void addCar(Car n)
    {
        ListIterator<Car> i = cars.listIterator(0);

        while(i.hasNext() && i.next().position < n.position)
        {;}

        i.add(n);

        n.targetSpeed = this.speedLimit;
        n.holdingLane = this;
    }
	
	/**************************************************************************
	 * UNDOCUMENTED VARIABLES AND FUNCTIONS:
	 **************************************************************************/
    /*
     * Public constructor
     */
    public Lane(double start, double end, double limit, int serialNum, Road holdingRoad){
        cars = new LinkedList<Car>();
        this.start = start;
        this.end   = end;
        this.holdingRoad = holdingRoad;
        this.serialNum = serialNum;
        this.speedLimit = limit;
    }

    /*
     * Removes the car from the lane
     */
    public void removeCar(Car n)
    {
        cars.remove(n);
    }	
 
}