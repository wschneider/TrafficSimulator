package com;
import java.util.Map;
import java.util.HashMap;


public class Car{
	public static Map<Integer,Double> accelerationProfile = new HashMap<Integer, Double>();
	
    public String toString()
    {
        return ("a: " + this.acceleration + " v: " + this.velocity + " p: " + this.position);
    }

    private int serialNum;
    public Behavior behaviorProfile;
    
    public Double acceleration;
    public Double velocity;
    public Double vehicleLength;
    
    public int accelerationState;
    
    public Double position; // the position of the REAR END of the vehicle.
 
    public Lane holdingLane;

    public int holdingState; // Unique number for each iteration to prevent double-movement. 
    
    private Car(int serialNum)
    {
    	this.accelerationState = 0;
    	this.acceleration = 0.0;
    	this.velocity = 0.;
    	this.vehicleLength = 10.;
    	this.position = 0.;
    	this.holdingLane = null;
    	this.serialNum = serialNum;
    	initializeProfile();
    }
    
    
    private void initializeProfile()
    {
    	accelerationProfile.put(-5,-20.0);
    	accelerationProfile.put(-4,-15.0);
    	accelerationProfile.put(-3,-10.0);
    	accelerationProfile.put(-2,-5.0);
    	accelerationProfile.put(-1,-2.0);
    	
    	accelerationProfile.put(0,0.0);
    	
    	accelerationProfile.put(1,1.0);
    	accelerationProfile.put(2,2.0);
    	accelerationProfile.put(3,4.0);
    	accelerationProfile.put(4,8.0);
    	accelerationProfile.put(5,10.0);
    	accelerationProfile.put(6,15.0);
    	accelerationProfile.put(7,20.0);
    	accelerationProfile.put(8,25.0);
    }
    
    public static Car getDefault(int serialNum)
    {
    	Car a = new Car(serialNum);
    	a.behaviorProfile = Behavior.getDefault();
    	return a;
    }
    
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

    public void breakLight()
    {
    	if(accelerationState > 0)
    	{
    		accelerationState = 0;
    	}
    	
    	accelerationState -= 1;
    	
    	if(accelerationState < -5)
    	{
    		accelerationState = -5;
    	}
    	
    	acceleration = this.accelerationProfile.get(accelerationState);
    }
    
    public void breakHeavy()
    {
    	if(accelerationState > 0)
    	{
    		accelerationState = 0;
    	}
    	
    	accelerationState -= 2;
    	
    	if(accelerationState < -5)
    	{
    		accelerationState = -5;
    	}
    	
    	acceleration = this.accelerationProfile.get(accelerationState);
    }
    
    public void accelerateLight()
    {
    	if(accelerationState < 0)
    	{
    		accelerationState = 0;
    	}
    	
    	accelerationState += 1;
    	
    	if(accelerationState > 8)
    	{
    		accelerationState = 8;
    	}
    	
    	acceleration = this.accelerationProfile.get(accelerationState);
    }

    public void setMaintain()
    {
    	acceleration = 0.0;
    	accelerationState = 0;
    }
}
