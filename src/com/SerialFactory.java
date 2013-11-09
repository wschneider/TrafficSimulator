package com;

public class SerialFactory {
	private static SerialFactory instance = null;
	private int stateNumber = 0;
	
	private SerialFactory() { }
	
	public static SerialFactory getInstance()
	{
		if(instance == null)
		{
			instance = new SerialFactory();
		}
		
		return instance;
	}
	
	public int getNewSerial()
	{
		return ++stateNumber;
	}
	
	public void setSerial(int other)
	{
		if(other > stateNumber)
		{
			stateNumber = other;
		}
	}
	
}
