package com;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.Timer;

class TestMultipleBalls extends JFrame {
	
	public ballPanel ballPanel;

	public TestMultipleBalls(){
		
		setTitle("Traffic Simulator");

		ballPanel = new ballPanel();
		ballPanel.setBackground(Color.white);
		ballPanel.setPreferredSize(new Dimension(500,500));
		//left to right
		ballPanel.addball(new ball(0, 39, 100, 2, "+x", Color.cyan));
		ballPanel.addball(new ball(0, 39, 100, 5, "+x", Color.green));
		ballPanel.addball(new ball(0, 39, 100, 3, "+x", Color.pink));
		
		//right to left
		ballPanel.addball(new ball(500, 13, 100, 2, "-x", Color.cyan));
		ballPanel.addball(new ball(500, 13, 100, 5, "-x", Color.green));
		ballPanel.addball(new ball(500, 13, 100, 3, "-x", Color.pink));
		
		//up to down
		ballPanel.addball(new ball(13, 20, 100, -2, "-y", Color.red));
		ballPanel.addball(new ball(13, 20, 100, -6, "-y", Color.gray));
		ballPanel.addball(new ball(13, 20, 100, -5, "-y", Color.magenta));
		
		//down to up
		ballPanel.addball(new ball(39, 500, 100, -2, "+y", Color.red));
		ballPanel.addball(new ball(39, 500, 100, -6, "+y", Color.gray));
		ballPanel.addball(new ball(39, 500, 100, -5, "+y", Color.magenta));

		add(ballPanel, BorderLayout.CENTER);

		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		
		new TestMultipleBalls();	
		
	}
}

class ball {

	private int xMax, yMax;   
	private int r, x0, y0;    
	private String move_direction;//where +x = left to right and -y is upper left corner to bottom left corner, 0, 0 = upper left corner of GUI
	
	// (Ball radius and midpoint)
	private int xSteg, ySteg; // Ball step-length
	private int v, v0= 5;     // Ball velocity
	Color color;

	public ball(int x0_, int y0_, int size, int velocity, String move_dir, Color color){
		
		// (arg: size and velocity)
		this.color = color;
		r= size/10;                  //radius
		xSteg= ySteg= v= v0= velocity; //Start velocity
		x0= x0_;                          
		y0= y0_;                       
		move_direction = move_dir;
	}

	public void step(int width, int height, List<ball> balls_) {

		xMax= width-1;        
		yMax= height-1;       
		
		//traveling from left to right
		if(move_direction.equals("+x")){
			
			boolean collission = false;
			int nextMove = x0 + v0;
			for(int i = 0; i < balls_.size(); i++){
				
				if(balls_.get(i).x0 == nextMove){
					collission = true;
					System.out.println("collission");
					
					//x0 = x0 + 1;
				}		
			}
			
			if(collission == false){	
				x0 = x0 + v0;
			}
			
			if (x0  < 0){ 
				//v++;        
				//xSteg= v;   
				x0 = 0;
			}
			
			else if (x0 > xMax){
				
				//v++;               
				//xSteg= -v;         
				x0= 0;
			}
		}
		
		//traveling from right to left
		if(move_direction.equals("-x")){
			
			x0 = x0 - v0;
			
			if (x0  < 0){ 
				//v++;        
				//xSteg= v;   
				x0 = 500;
			}
			
			else if (x0 > xMax){ 
				
				//v++;               
				//xSteg= -v;         
				x0= 500;
			}
		}
		
		//traveling from up down
		if(move_direction.equals("-y")){
			
			y0 = y0 - v0;
			
			if( y0 + r > yMax)
				y0= 0;
			
			else if( y0 + r <= 0)
				y0 = 0;
		}
		
		//traveling from down -> up
		if(move_direction.equals("+y")){
			
			y0 = y0 + v0;
			
			if( y0  > yMax)
				y0= 500;
			
			else if( y0 < 0)
				y0 = 500;
		}

	}

	public void draw(Graphics g) {		
		
		g.setColor(color);
		g.fillOval(x0-r, y0-r, 2*r, 2*r); 
		
		g.setColor(Color.blue);
		
		
	}
}

class ballPanel extends JPanel implements ActionListener{

	List<ball> balls = new ArrayList<ball>();
	private Timer timer = new Timer(100, this);

	public ballPanel() {
		//        addComponentListener(xyPlan);
		//        //Ändringar av spelplanens storlek (If changing the frame-size)
		timer.start();
	}

	public void actionPerformed(ActionEvent e){

		int w = getWidth();
		int h = getHeight();
		for(ball ball : balls) {
			//passing in width and height of GUI along with the List of other balls
			ball.step(w, h, balls);
		}

		repaint();
	}

	@Override
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		for(ball ball : balls) {
			ball.draw(g);
		}
		
		g.drawRect(0, 0, 25, 1000);//road
		g.drawRect(0, 0, 1000, 25);
		
		g.drawRect(0, 0, 50, 1000);//road
		g.drawRect(0, 0, 1000, 50);
		
	}

	public void addball(ball ball) {
		balls.add(ball);
	}
	
}