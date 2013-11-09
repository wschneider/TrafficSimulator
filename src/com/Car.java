package com;

public class Car{

    public String toString()
    {
        return ("a: " + this.acceleration + " v: " + this.velocity + " p: " + this.position);
    }

    private int serialNum;
    public Behavior behaviorProfile;
    
    public Double acceleration;
    public Double velocity;
    public Double vehicleLength;
    
    public Double position; // the position of the REAR END of the vehicle.
 
    public Lane holdingLane;

    public int holdingState; // Unique number for each iteration to prevent double-movement. 
    
    public Car(Double acceleration, int serialNum)
    {
        this.acceleration = acceleration;
        this.velocity = 0.0;
        this.vehicleLength = 10.0;
        this.position = 0.0;
        this.holdingLane = null;
        this.serialNum = serialNum;
        this.behaviorProfile = Behavior.getDefault();
    }


}
