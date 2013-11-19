package com;

public class StopSignIntersection extends Intersection {

	public StopSignIntersection(double start, double end, double loc, Road source, Road target) {
		super(start, end, loc, source, target);
		
		this.targetSpeed = 0.0;
		
	}

	@Override
	public void performAction(Car target, double timeStep) {
		/*
		 * Stop Sign AI:
		 * 		(1) If car is nowhere near this intersection and this is called erroneously, do nothing
		 * 		(2) If car is near this intersection but is not engaged, engage it. 
		 * 		(3) If car is at or past the intersection, and is turning, change it's lane. 
		 */

		
		if(!target.engaged)
		{
			/*
			 * If the car is not currently engaged in any intersection 
			 */
			
			if(target.position < this.startLoc || target.position > this.endLoc)
			{
				//Car is out of bounds. Do nothing.
				System.out.println("Checking Bounds" + this.startLoc);
				return;
			}
			
			//set the car's target speed, serial target, etc.
			System.out.println("ENGAGING CAR IN INTERSECTION");
			target.engage(this);
			return;
		}
		
		
		if(target.position >= this.endLoc)
		{
			//target is at or past the end location of the intersection
			if(target.turning)
			{
				System.out.println("TARGET IS TURNING");
				target.position = this.locOnTarget;
				target.holdingLane.removeCar(target);
				this.targetLane.addCar(target);
			}
			
			target.disengage();
			return;
		}
		
		//Target is already engaged. handle movement adjustments:
		//If car is not moving, accelerate one step
		//If car will stop AFTER intersection, break once
		//If car will stop BEFORE intersection, set maintain
		
		
		
		double estimatedTravel = -(target.velocity * target.velocity) / (2 * target.acceleration);

		//double estimatedTravel = target.velocity * timeStep + ((1/2) * target.acceleration * timeStep * timeStep);
		double distanceToInt   = this.endLoc - target.position;
		
		if(target.acceleration == 0)
		{
			estimatedTravel = distanceToInt * 2;
		}
		
		System.out.println("CHANGES?" + estimatedTravel + " " + distanceToInt + " " +  target.position);
		if(target.velocity == 0.0)
		{
			System.out.println("GOTTA SPEED UP");
			target.accelerateLight();
			return;
		}
		if(estimatedTravel > distanceToInt)
		{
			System.out.println("GOTTA SLOW DOWN");
			target.breakLight();
			return;
		}
		System.out.println("NOPE");
		target.setMaintain();
		
		
		/*
		Double distance = car.velocity * timeStep + ((1/2) * car.acceleration * timeStep * timeStep);
        Double finalVelocity = car.velocity + car.acceleration * timeStep;
        Double finalPosition = car.position + distance;
        */
		

	}

}
