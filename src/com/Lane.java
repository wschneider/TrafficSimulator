package com;

import java.util.*;

public class Lane{
    /*
     * A lane represents an array of cars on the road. Lanes belong to specific roads and employ a has-a relationship to roads. Lanes interact with one-another, as is dictated by the handler class. */
    
	/*
	 * Positioning: 
	 * 		start, end positions in feet, relative to the start of the road. 
	 */
	public static double start, end;
	public double speedLimit;
	
	/*
	 * List of Cars in this lane, in proper order from closest to the start of the road to furthest
	 */
    public LinkedList<Car> cars;
    
    public ArrayList<RoadFeature> features;
    
    public ArrayList<Lane> borderingLanes;
    
    /*
     * Reference to the road this lane belongs to (this should never change)
     */
    public Road holdingRoad;
    
    /*
     * Serialization
     */
    public final int serialNum;
    
    /*
     * Public constructor
     */
    public Lane(double start, double end, double limit, int serialNum){
        cars = new LinkedList<Car>();
        this.start = start;
        this.end   = end;
        this.holdingRoad = null;
        this.serialNum = serialNum;
        this.speedLimit = limit;
    }

    /*
     * Pushes a car into the lane at the appropriate location (via position)
     */
    public void addCar(Car n)
    {
        //inserts Car n to the appropriate location in this lane. 

        ListIterator<Car> i = cars.listIterator(0);

        while(i.hasNext() && i.next().position < n.position)
        {;}

        i.add(n);

        n.targetSpeed = this.speedLimit;
        n.holdingLane = this;
    }

    /*
     * Prints the content of the lane
     */
    public void print()
    {
        //prints all of the cars in the lane
        for(Car car : cars)
        {
            System.out.println(car.toString());
        }
    }

    /*
     * Removes the car from the lane
     */
    public void removeCar(Car n)
    {
        cars.remove(n);
    }

    /*
     * Often it is important to know the next car ahead of a given car. 
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
 
}








