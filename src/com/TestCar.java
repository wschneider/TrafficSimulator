package com;
import java.lang.*;

public class TestCar{

    public static void main(String[] args)
    {
    	SerialFactory fac = SerialFactory.getInstance();
    	
        RoadMap t = new RoadMap();

        Car c1 = Car.getDefault(fac.getNewSerial());
        Car c2 = new Car(2.0, fac.getNewSerial());

        t.pushCar(c1);

        int i = 0;
        while(t.road.lanes[0].cars.size() > 0)
        {
            System.out.println("iteration "+ i);
            t.iterate(0.05);
            i++;   
            /*
            try {
				Thread.currentThread().sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
                                                                                                                                                                                                                                                                                                              
        }

        System.out.println("HELLO WORLD");
    }

}
