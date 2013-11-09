package com;

import java.util.ArrayList;
import java.util.Iterator;

public class RoadMap{
/*
    RoadMap is the wrapper class that holds the roads and the cars, and 
    handles the iteration of the routine.     
*/
	private SerialFactory numberGenerator = SerialFactory.getInstance();
	
    public Road road;

    public RoadMap(){

        road = new Road(5280, 2,0,0, numberGenerator.getNewSerial());
    }
    
    public void pushCar(Car nCar)
    {
        road.pushCar(0,nCar); 
    }

    public void print()
    {
        road.print();
    }

    public void iterate(Double timeStep)
    {
        for(Lane lane : road.lanes)
        {
        	Iterator<Car> i = lane.cars.descendingIterator();
        	while(i.hasNext())
        	{
        		doMove(i.next(),timeStep);
        	}
        	
        }
        road.print();

    }

    private void doMove(Car car, Double timeStep)
    {
        //move this car one iteration in timeStep seconds
        //  what the car does is a function of it's behavior profile
        Lane rLane = car.holdingLane;
        Road rRoad = rLane.holdingRoad;

        //Implementation one: Cars move forward at constant acceleration
        //  d = initialVelocity * time + (1/2) * acceleration * time * time

        Double distance = car.velocity * timeStep + ((1/2) * car.acceleration * timeStep * timeStep);
        Double finalVelocity = car.velocity + car.acceleration * timeStep;
        Double finalPosition = car.position + distance;

        car.position = finalPosition; 
        car.velocity = finalVelocity; 

        //If the car's position is greater than the length of the lane, it will leave the road. 

        if(car.position > rLane.end)
        {
            rLane.removeCar(car);
        }
    }

    private void doMove2(Car car, Double timeStep){
    	/*
    	 * Basic AI for Road Navigation
    	 *   (1) Is there a car ahead of me that I care about (<=10 seconds) ?
    	 *     (a) Yes:: Can I merge?
    	 *       (i)  Yes:: MERGE
    	 *       (ii) No::  BREAK
    	 *     (b) No:: Move on.
    	 *   (2) Is there a road feature ahead of me that I care about (<=10 seconds) ?
    	 *     (a) Yes:: BREAK
    	 *     (b) No:: Move on.
    	 *   (3) Am I going the speed limit?
    	 *     (a) Yes:: Move on.
    	 *     (b) No:: ACCELERATE
    	 * 
    	 * 
    	 */
    	
    	
    }
    
    
    
}
