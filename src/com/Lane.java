package com;

import java.util.*;

public class Lane{
    /*A lane represents an array of cars on the road. Lanes belong to specific roads and employ a has-a relationship to roads. Lanes interact with one-another, as is dictated by the handler class. */
    public static double start, end;
    public LinkedList<Car> cars;
    public Road holdingRoad;
    public final int serialNum;
    
    public double speedLimit;
    
    public Lane(double start, double end, int serialNum){
        cars = new LinkedList<Car>();
        this.start = start;
        this.end   = end;
        this.holdingRoad = null;
        this.serialNum = serialNum;
    }

    public void addCar(Car n)
    {
        //inserts Car n to the appropriate location in this lane. 

        ListIterator<Car> i = cars.listIterator(0);

        while(i.hasNext() && i.next().position < n.position)
        {;}

        i.add(n);

        n.holdingLane = this;
    }

    public void print()
    {
        //prints all of the cars in the lane
        for(Car car : cars)
        {
            System.out.println(car.toString());
        }
    }

    public void removeCar(Car n)
    {
        cars.remove(n);
    }

    public Car nextCar(Car n)
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

 
}
