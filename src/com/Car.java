package com;

public class Car{

    public String toString()
    {
        return ("a: " + this.acceleration + " v: " + this.velocity + " p: " + this.position);
    }

    public Double acceleration;
    public Double velocity;
    public static Double vehicleLength;
    
    public Double position; // the position of the REAR END of the vehicle.
 
    public Lane holdingLane;

    public Car(Double acceleration)
    {
        this.acceleration = acceleration;
        this.velocity = 0.0;
        this.vehicleLength = 10.0;
        this.position = 0.0;
        this.holdingLane = null;
    }


}
