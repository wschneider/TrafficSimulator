package com;

public class Road{
    //Roads have 2 lanes 

    private int length;
    public int getLength(){ return this.length; }
    public Lane[] lanes;
    public int nLanes;
    
    private int serialNum;
    
    //X, Y locations to orient road to grid. X and Y are the starting locations of the road.
    public double X;
    public double Y;
    

    public Road(int length, int numLanes, int X, int Y, int serialNum)
    {
        this.length = length;
        this.X = X;
        this.Y = Y;
        this.lanes = new Lane[numLanes];
        this.nLanes = numLanes;
        for(int i=0;i<numLanes;i++)
        {
            this.lanes[i] = new Lane(0,5280.0);
            this.lanes[i].holdingRoad = this;
        }
        this.serialNum = serialNum;
    }

    public void pushCar(int laneNum, Car nCar)
    {
        if(0 <= laneNum && laneNum < nLanes)
        {
            this.lanes[laneNum].addCar(nCar);
        }
    }

    public void print()
    {
        for(Lane lane : lanes)
        {
            System.out.println("Lane:");
            lane.print();
        }
    }

}
