package com;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.util.ArrayList;

public class GUI extends JPanel implements ActionListener {

	// global variables
	JFrame Frame1 = new JFrame("TestFrame");
	RoadMap r = new RoadMap();
	Road road = r.road;//roadmap only contains one road for now -> for simplicity sake
	BouncingBall b;
	
	public GUI() {

		Frame1.setSize(400, 400);
		Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame1.setVisible(true);

		JPanel panel = new JPanel(new GridLayout(0, 2));

		Frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// set the background to be gray
		//panel.setBackground(Color.gray);
		
		b = new BouncingBall(20, 20, 4, 0);
		Frame1.add(b);
		//Frame1.add(new BouncingBall(10, 20, 4, 0));

		// add Panel to frame
		Frame1.add(panel);
		
		// a timer called every 1/10 second
		Timer timer = new Timer(100, this);
		timer.start();
	}

	// called by the Timer
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
				
		g.setColor(Color.gray);
		
		g.fillRect(0, 20, 1000, 50);

		g.fillRect(0, 20, 50, 1000);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		repaint();
	}

	public static void main(String[] args) {

		new GUI();
		
		RoadMap r = new RoadMap();
		Road road = r.road;
		ArrayList<Lane> lane = road.getLanes();
		//Lane[] lane = road.lanes;
		lane.get(0).addCar(new Car(2.2, 0));
		lane.get(0).addCar(new Car(2.2, 1));
		
		System.out.println("Lane has " + lane.get(0).cars.size() + "cars");
		
		lane.get(0).cars.get(0).velocity = 5.0;//setting the velocity to 5 just for shits and giggles
		lane.get(0).cars.get(1).velocity = 8.0;
		
		LinkedList<Car> cars_ = lane.get(0).cars;//gets the list of cars from the Lane
		
		ListIterator<Car> itr = cars_.listIterator();
		
		Vector<BouncingBall> balls = new Vector();
		balls.add(new BouncingBall(0, 0, 5.0, 0));
		balls.add(new BouncingBall(0, 0, 8.0, 0));

		while(itr.hasNext()){
			
			Car c = itr.next();
			
			System.out.println(c.serialNum + "was added to the GUI");
			
			//frame.add(new BouncingBall(0, 0, c.velocity, 0));
		}

		/*
		// a JFrame to test the whole thing
		JFrame frame = new JFrame("Bouncing ball");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// add the bouncing panel to the JFrame
		frame.add(new BouncingBall(20, 20, 4, 0));
		frame.add(new BouncingBall(0, 20, 4, 0));

		//frame.add(new Road(road.x, road.y, length, orientation) );

		frame.setVisible(true);*/

	}
}
