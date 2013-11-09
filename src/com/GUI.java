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

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GUI extends JPanel {

	//global variables
	JFrame Frame1 = new JFrame("TestFrame");
	RoadMap r = new RoadMap();
	Road road = r.road;
	
	public GUI() {

		Frame1.setSize(400, 400);
		Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame1.setVisible(true);

		JPanel panel = new JPanel(new GridLayout(0, 2));

		// set the background to be gray
		panel.setBackground(Color.gray);

		// add "ovals" or cars to panel
		panel.add(new OvalComponent());

		// add "rectangles" or roads to panel
		panel.add(new RectangleComponent());

		// add Panel to frame
		Frame1.add(panel);
	}

	class OvalComponent extends JComponent {

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.yellow);
			g.fillOval(20, 20, 20, 20);
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			g.fillOval(30, 20, 20, 20);

			//g.translate(25, 25);
		}
		
		
	}

	class RectangleComponent extends JComponent {

		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(Color.black);
			Rectangle rect2 = new Rectangle((int)road.X, (int)road.Y, 20, road.getLength());

			//g2d.draw(rect2);
			//g2d.rotate(Math.toRadians(30));
			g2d.draw(rect2);
			g2d.fill(rect2);

			
			/*
			AffineTransform transform = new AffineTransform();
			transform.rotate(Math.toRadians(45), rect2.getX() + rect2.width/2, rect2.getY() + rect2.height/2);
			g2d.fill(rect2);
			
			AffineTransform old = g2d.getTransform();
			g2d.transform(transform);
			
			
			g2d.draw(rect2);*/
		}
	}

	public static void main(String[] args) {

		new GUI();

	}
}
