package com;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;

public class RoadMap{
/*
    RoadMap is the wrapper class that holds the roads and the cars, and 
    handles the iteration of the routine.     
*/
	private SerialFactory numberGenerator = SerialFactory.getInstance();
	public ArrayList<Road> roads;
	
    //public Road road;

    public void createNewRoadsForTestCase()
    {
    	Road a = new Road(500,0,0,0.0);
    	Road b = new Road(500,400,0,90.0); //at the end of a, at a right angle
    	
    	a.createLane(0.0,500.0,34.0);
    	b.createLane(0.0,500.0,34.0);
    	
    	roads.add(a);
    	roads.add(b);
    	
    	a.addStopSignIntersection(b, 400.0,0.0);
    	
    }
    
    public RoadMap(){

    	roads = new ArrayList<Road>();
    	createNewRoadsForTestCase();
    	//road = new Road(5280, 0, 0, 0.0);
    	//road.createLane(0.0,5280.0,88.0);
    	//road.createLane(0.0,5280.0,88.0);
    	/*
        road = new Road(5280, 2,0,0, numberGenerator.getNewSerial());
         */
    }
     
    public void pushCar(int nroad, Car nCar)
    {
    	if(nroad >= 0 && nroad < roads.size())
    	{
    		roads.get(nroad).pushCar(0,nCar); 
    	}
    }

    public void print()
    {
        for(Road road : roads)
        {
        	System.out.println("Road" + road.getSerialNum());
        	road.print();
        }
    }

    public void iterate(Double timeStep)
    {
    	int n = numberGenerator.getNewSerial();
    	
    	for(Road road : roads)
    	{
    		for(Lane lane : road.getLanes())
        	{
        		Iterator<Car> i = lane.cars.descendingIterator();
        		while(i.hasNext())
        		{
        			doMove3(i.next(),timeStep,n);
        		}
        	
        	}
    	}
        this.print();

    }

    private void doMove(Car car, Double timeStep)
    {
        //move this car one iteration in timeStep seconds
        //  what the car does is a function of it's behavior profile
        Lane rLane = car.holdingLane;
        Road rRoad = rLane.getHoldingRoad();

        //Implementation one: Cars move forward at constant acceleration
        //  d = initialVelocity * time + (1/2) * acceleration * time * time

        Double distance = car.velocity * timeStep + ((1/2) * car.acceleration * timeStep * timeStep);
        Double finalVelocity = car.velocity + car.acceleration * timeStep;
        Double finalPosition = car.position + distance;

        car.position = finalPosition; 
        car.velocity = finalVelocity; 

        //If the car's position is greater than the length of the lane, it will leave the road. 

        if(car.position > rLane.getEnd())
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
    	 *   (4) Have I left the road?
    	 *     (a) Yes:: remove
    	 *     (b) No:: Move on.
    	 * 
    	 */
        Lane rLane = car.holdingLane;
        Road rRoad = rLane.getHoldingRoad();
        
        //Step 0: Physically move the car for this unit of time. 
        Double distance = car.velocity * timeStep + ((1/2) * car.acceleration * timeStep * timeStep);
        Double finalVelocity = car.velocity + car.acceleration * timeStep;
        Double finalPosition = car.position + distance;

        if(finalVelocity < 0)
        {
        	finalVelocity = 0.0;
        }
        
        car.position = finalPosition; 
        car.velocity = finalVelocity; 
        
        if(car.position > rLane.getEnd())
        {
        	System.out.println("Removing car from end of road");
            rLane.removeCar(car);
            return;
        }
   	
    	//Step 1: Is there a car <10 seconds ahead?
    	Car nextCar = rLane.nextCar(car);
    	if(nextCar != null && separation(car, nextCar) < 10.0)
    	{
    		//yes: Can I merge?
    			/*
    			 * TO BE IMPLEMENTED LATER, ASSUME NO
    			 */
    		
    		//no: 
    		car.breakLight();
    		System.out.println("Breaking because there's a car ahead" + car.serialNum + " " + nextCar.serialNum);
    		return;
    	}
    	
    	//Step 2: Is there a road feature ahead of me that I care about (<= 10 seconds) ?
    	/*
    	 * TO BE IMPLEMENTED LATER, ASSUME NO
    	 */
    	
    	//Step 3: Speed Limit Adjustment
    	if(( rLane.getSpeedLimit() * car.behaviorProfile.limitRatio) - car.velocity > (1.33 * car.behaviorProfile.limitRatio))
    	{
    		System.out.println("Car's velocity is " + car.velocity + " which is below speed limit of " + rLane.getSpeedLimit());
    		car.accelerateLight();
    		return;
    	}
    	else
    	{
    		car.setMaintain();
    	}
    	
    }
    
    private void doMove3(Car car, Double timeStep, int SERIAL)
    {
    	/*
    	 * BASIC AI FOR NAVIGATION
    	 *  (0) Adjust position
    	 * 	(1) Is there a car ahead of me that I need to slow down to avoid hitting?
    	 *  (2) Do I need to break because I am engaged in an intersection or road feature?
    	 *  (3) Am I going the speed limit (But also not slowing down or accelerating because of a road feature or other car)
    	 *  (4) 
    	 */
    	
    	// STEP -1: Check Holding State
    	if(car.holdingState == SERIAL)
    	{
    		//car has already moved for this block of time. don't repeat it. 
    		return;
    	}
    	
    	// STEP 0: Adjust position for this block of time
    	Lane rLane = car.holdingLane;
        Road rRoad = rLane.getHoldingRoad();
        
        Double distance = car.velocity * timeStep + ((1/2) * car.acceleration * timeStep * timeStep);
        Double finalVelocity = car.velocity + car.acceleration * timeStep;
        Double finalPosition = car.position + distance;

        if(finalVelocity < 0)
        {
        	finalVelocity = 0.0;
        }
        
        car.position = finalPosition; 
        car.velocity = finalVelocity; 
        
        if(car.position > rLane.getEnd())
        {
        	System.out.println("Car has left the map.");
            rLane.removeCar(car);
            return;
        }
    	
        // STEP 1: Check if need to break because of immediate car ahead. (NON COMMITTAL)
        Car nextCar = rLane.nextCar(car);
    	if(nextCar != null && separation(car, nextCar) < 10.0)
    	{
    		//yes: Can I merge?
    			/*
    			 * TO BE IMPLEMENTED LATER, ASSUME NO
    			 */
    		
    		//no: 
    		car.breakLight();
    		System.out.println("Breaking because there's a car ahead" + car.serialNum + " " + nextCar.serialNum);
    	}
    	
    	// STEP 2: Check if car is engaged. If it is, the intersection it is engaged in handles AI
    	if(car.engaged)
    	{
    		System.out.println("ENGAGED ACTION NOW:");
    		car.manager.performAction(car,timeStep);
    		return;
    	}
    	else{
    		//THIS IS BAD: FIX IT
    		Intersection n = car.holdingLane.getHoldingRoad().getNextIntersection(car);
    		if(n != null)
    		{
    			n.performAction(car, timeStep);
    		}
    	}
    	
    	// STEP 3: Speed Limit Adjustment
    	if(( rLane.getSpeedLimit() * car.behaviorProfile.limitRatio) - car.velocity > (1.33 * car.behaviorProfile.limitRatio))
    	{
    		System.out.println("Car's velocity is " + car.velocity + " which is below speed limit of " + rLane.getSpeedLimit());
    		car.accelerateLight();
    		return;
    	}
    	else
    	{
    		car.setMaintain();
    	}
    	
        
    	
    }
    
    private double separation(Car back, Car forward)
    {
    	if(back.holdingLane.getSerialNum() != forward.holdingLane.getSerialNum())
    	{
    		return -1;
    	}
    	
    	else
    	{
    		double d = Math.abs(back.position - forward.position + back.vehicleLength);
    		double vi = back.velocity;
    		double a = back.acceleration;
    		return (Math.sqrt(vi*vi+2*a*d)-vi)/a;
    	}
    }
    
    public int numCars()
    {
    	int sum = 0;
    	for(Road road: roads)
    	{
    		sum += road.numCars();
    	}
    	return sum;
    }
    
}
