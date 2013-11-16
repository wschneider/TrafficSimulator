package com;
import java.util.ArrayList;

public class Road{

    private int length;
    public int getLength(){ return this.length; }
    
    public Lane[] laneList;
    public int nLanes;
    
    
    
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
    public void pushCar(int laneNum, Car nCar)
    {
        if(0 <= laneNum && laneNum < nLanes)
        {
            this.laneList[laneNum].addCar(nCar);
        }
    }

    public void createLane(double start, double end, double speedLimit)
    {
    	SerialFactory n = SerialFactory.getInstance();
    	Lane l = new Lane(start, end, speedLimit, n.getNewSerial());
    	l.holdingRoad = this;
    	this.lanes.add(l);
    }
    
    /*
     * Print the contents of this road. 
     */
    public void print()
    {
        for(Lane lane : laneList)
        {
            System.out.println("Lane:");
            lane.print();
        }
    }

}