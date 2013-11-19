package com;
import java.util.Map;
import java.util.HashMap;


public class Car{
	/*
	 * A map of integers representing "acceleration ids" to actual acceleration values. 
	 * 
	 * Cars will break or accelerate along an integer scale, which corresponds to some real value
	 */
	public static Map<Integer,Double> accelerationProfile = new HashMap<Integer, Double>();
	
    public String toString()
    {
        return ("a: " + this.acceleration + " v: " + this.velocity + " p: " + this.position);
    }

    public void print()
    {
    	System.out.println("Car" + serialNum + " at position " + position + " with velocity " + velocity);
    }
    
    /*
     * Serial Number: Unique among cars, used for state-saving
     */
    public int serialNum;
    
    /*
     * Behavior Profile: Set of factors that influence how this car behaves on the road
     */
    public Behavior behaviorProfile;
    
    /*
     * Movement values:
     * 		acceleration, velocity, length, position, and state. All self explanatory. 
     */
    public Double acceleration;
    public Double velocity;
    public Double vehicleLength;
    public int accelerationState;
    public Double position; // the position of the REAR END of the vehicle.
 
    /*
     * This car is currently engaged in an intersection, and is or is not turning
     */
    public int targetSerial;
    public boolean engaged;
    public boolean turning;
    public Double targetSpeed;
    public Intersection manager;
    
    public void engage(Intersection t)
    {
    	System.out.println("ENGAGING CAR");
    	this.engaged = true;
    	/*
    	 * This will be changed later, obvs
    	 */
    	this.turning = true;
    	this.targetSerial = t.getSerialNum();
    	this.targetSpeed = t.targetSpeed;
    	this.manager = t;
    }
    public void disengage()
    {
    	this.engaged = false;
    	this.turning = false;
    	this.targetSerial = -1;
    	this.targetSpeed = this.holdingLane.getSpeedLimit();
    	this.manager = null;
    }
    
    /*
     * A reference to the physical lane that this car is in. 
     */
    public Lane holdingLane;

    /*
     * Holding state: To prevent double movement in one iteration.
     */
    public int holdingState; // Unique number for each iteration to prevent double-movement. 
    
    /*
     * Private standard constructor for default behaviour
     */
    private Car(int serialNum)
    {
    	this.accelerationState = 0;
    	this.acceleration = 0.0;
    	this.velocity = 0.;
    	this.vehicleLength = 10.;
    	this.position = 0.;
    	this.holdingLane = null;
    	this.serialNum = serialNum;
    	this.turning = false;
    	this.engaged = false;
    	
    	initializeProfile();
    }
    
    /*
     * Utility function to initialize the accelerationProfile
     */
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
    
    /*
     * Initializes a car with "Default" behavior
     * 		Used as a debug mechanism
     */
    public static Car getDefault(int serialNum)
    {
    	Car a = new Car(serialNum);
    	a.behaviorProfile = Behavior.getDefault();
    	return a;
    }
    
    /*
     * Public constructor:
     * 		DEPRECATED: DO NOT USE, USE FACTORY METHODS INSTEAD
     */
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

    /*
     * Instructs the car to begin breaking, or to slightly increase the level of breaking
     */
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
    
    /*
     * Instructs the car to break more heavily than breakLight
     */
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
    
    /*
     * Instructs the car to accelerate lightly. 
     */
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

    /*
     * Sets the car to maintain velocity. 
     */
    public void setMaintain()
    {
    	acceleration = 0.0;
    	accelerationState = 0;
    }
}
