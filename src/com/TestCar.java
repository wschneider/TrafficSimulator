package com;
import java.lang.*;

public class TestCar{

    public static void main(String[] args)
    {
    	SerialFactory fac = SerialFactory.getInstance();
    	
        RoadMap t = new RoadMap();
        
        
        Car c1 = Car.getDefault(fac.getNewSerial());
        Car c2 = Car.getDefault(fac.getNewSerial());

        c1.print();
        c2.print();
        
        t.pushCar(0,c1);
        t.pushCar(1,c2);
        
        int i = 0;
        while(t.numCars() > 0)
        {
            System.out.println("iteration "+ i);
            t.iterate(0.05);
            i++;   
            
            try {
				Thread.currentThread().sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                                                                                                                                                                                                                                                                                                              
        }

        System.out.println("HELLO WORLD");
    }

}
