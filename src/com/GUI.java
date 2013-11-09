package com;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel{

	JFrame Frame1 = new JFrame("TestFrame");

	public GUI(){

		Frame1.setSize(400,400);
		Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Frame1.setVisible(true);
		
		JPanel panel = new JPanel(new GridLayout(0, 2));
		
		//set the background to be gray
		panel.setBackground(Color.gray);
		
		//add "ovals" or cars to panel
		panel.add(new OvalComponent());
		
		//add "rectangles" or roads to panel
		panel.add(new RectangleComponent());
		
		//add Panel to frame
		Frame1.add(panel);
		
		Road r = new Road(20, 1, 3, 0, 0);
	}
	
	class OvalComponent extends JComponent {
		
	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.setColor(Color.yellow);
	        g.fillOval(20, 20, 20, 20);
	    }
	}
	
	class RectangleComponent extends JComponent {
		
		@Override
		public void paintComponent(Graphics g){
			
			super.paintComponent(g);
			g.setColor(Color.black);
			g.fill3DRect(3, 0, 10, 20, false);
		}
	}
	
	public static void main(String[] args){

		new GUI();
		
	}
}

