package com;

public class Behavior {
	private Behavior() { }
	
	public double limitRatio;  //Ratio of the speed limit this person is willing to travel
	public double limitExcess; //fps this person is willing to exceed the speed limit by
	
	public static Behavior getDefault(){
		Behavior a = new Behavior();
		a.limitRatio  = 1.;
		a.limitExcess = 0.;
		return a;
	}

	public static Behavior getStandard(){
		Behavior a = new Behavior();
		a.limitRatio  = 1.02;
		a.limitExcess = 10.;
		return a;
	}
}
