package com;
import java.util.ArrayList;
import java.util.ListIterator;

public class Road{

    private int length;
    public int getLength(){ return this.length; }
    
    /* DEPRECATED:
    public Lane[] laneList;
    public int nLanes;
    
    */
    
    private ArrayList<Lane> lanes;
    public ArrayList<Lane> getLanes() {return this.lanes;}
    
    private ArrayList<Intersection> intersections;
    public ArrayList<Intersection> getIntersections() {return this.intersections;}
    
    private ArrayList<RoadFeature> features;
    public ArrayList<RoadFeature> getFeatures() {return this.features;}
    
    private int serialNum;
    
    //X, Y locations to orient road to grid. X and Y are the starting locations of the road.
    public final int X;
    public final int Y;
    public final double Direction; // in degrees % 360, where 0 = north, 90 = West, etc.

    /*
     * Default constructor. To be repaired soon for ease of use. 
     */
    /*
    public Road(int length, int numLanes, int X, int Y, int serialNum)
    {
    	SerialFactory n = SerialFactory.getInstance();
        this.length = length;
        this.X = X;
        this.Y = Y;
        this.laneList = new Lane[numLanes];
        this.nLanes = numLanes;
        for(int i=0;i<numLanes;i++)
        {
            this.laneList[i] = new Lane(0.0,5280.0,88.0,n.getNewSerial());
            this.laneList[i].holdingRoad = this;
            this.laneList[i].speedLimit = 88.0;
        }
        this.serialNum = serialNum;
        this.features = new ArrayList<RoadFeature>();
        this.intersections = new ArrayList<Intersection>();
    }*/

    public Road(int length, int X, int Y, Double direction)
    {
    	SerialFactory n = SerialFactory.getInstance();
    	this.length = length;
    	this.X = X;
    	this.Y = Y;
    	this.Direction = direction;
    	this.serialNum = n.getNewSerial();
    	this.features = new ArrayList<RoadFeature>();
        this.intersections = new ArrayList<Intersection>();
        this.lanes = new ArrayList<Lane>();
    	
    }
    
    /*
     * Add a car to this road in a given lane
     */
    public void pushCarOld(int laneNum, Car nCar)
    {
    	/* DEPRECATED:
        if(0 <= laneNum && laneNum < nLanes)
        {
            this.laneList[laneNum].addCar(nCar);
        }
        */
    }

    public void pushCar(int laneNum, Car nCar)
    {
    	if(0 <= laneNum && laneNum < this.lanes.size())
        {
            this.lanes.get(laneNum).addCar(nCar);
        }
    }
    
    public void createLane(double start, double end, double speedLimit)
    {
    	SerialFactory n = SerialFactory.getInstance();
    	Lane l = new Lane(start, end, speedLimit, n.getNewSerial(), this);
    	this.lanes.add(l);
    }
    
    /*
     * Print the contents of this road. 
     */
    public void print()
    {
        for(Lane lane : lanes)
        {
            System.out.println("Lane:");
            lane.print();
        }
    }

    public void addStopSignIntersection(Road target, double positionSource, double positionTarget)
    {
    	/*
    	 * LATER TO BE IMPLEMENTED:
    	 *		Sanity checks that X and Y values (used in rendering) check out with intersections of roads 
    	 */
    	
    	//distance = rate * time
    	//         = speedLimit (f/s) * 10 (s)
    	double stoppingDist = 10 * this.lanes.get(0).getSpeedLimit();
    	
    	StopSignIntersection a = new StopSignIntersection(positionSource-stoppingDist, positionSource, positionTarget, this, target);
    	
    	this.intersections.add(a);
    }
    
    public Intersection getNextIntersection(Car car)
    {
    	ListIterator<Intersection> iter = intersections.listIterator(0);
    	
    	while(iter.hasNext())
    	{
    		Intersection current = iter.next();
    		if(current.endLoc > car.position){
    			return current;
    		}
    	}
    	
    	return null;
    	
    }
    
    public int numCars()
    {
    	int sum = 0;
    	for (Lane lane : lanes)
    	{
    		sum += lane.numCars();
    	}
    	return sum;
    }

	public int getSerialNum() {
		return serialNum;
	}
}