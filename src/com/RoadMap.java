package com;

import java.util.ArrayList;


public class RoadMap{
/*
    RoadMap is the wrapper class that holds the roads and the cars, and 
    handles the iteration of the routine.     
*/
	private SerialFactory numberGenerator = SerialFactory.getInstance();
	
    public Road road;

    public RoadMap(){

        road = new Road(5280, 2, numberGenerator.getNewSerial());

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
            for(Car car : lane.cars)
            {
                doMove(car, timeStep);
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

}
