package com;

public class Road{
    //Roads have 2 lanes 

    private int length;
    public int getLength(){ return this.length; }
    public Lane[] lanes;
    public int nLanes;
    
    private int serialNum;
    

    public Road(int length, int numLanes, int serialNum)
    {
        this.length = length;
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
