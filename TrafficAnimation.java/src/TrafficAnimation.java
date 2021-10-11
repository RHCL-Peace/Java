import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates a [put your description here]
 *
 * @author BSU CS 121 Instructors
 * @author [put your name here]
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel {
	private static final int CarWheelsX = 0;

	private static final int CarBody = 0;

	// This is where you declare constants and variables that need to keep their
	// values between calls to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events. Note: 100ms is 10
	 * frames per second - you should not need a faster refresh rate than this
	 */
	private final int DELAY = 100; // milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = 0;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 10;

	private final Color BACKGROUND_COLOR = new Color(79, 201, 89);

	/*
	 * This method draws on the panel's Graphics context. This is where the majority
	 * of your work will be.
	 *
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height
		
		// Fill the graphics page with the background color.
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);

		// Calculate the new xOffset position of the moving object.
		xOffset = (xOffset + stepSize) % width;

// Drawing Road

		// This is for the Road Line Drawing
		int roadX = CarBody;
		int roadY = (int) (height / 1.7);
		int roadWidth = width * 2;
		int roadHeight = height / 3;
		g.setColor(Color.BLACK);
		g.fillRect(roadX, roadY, roadWidth, roadHeight);

		// This is for the First Road Line Drawing
		int roadLineX = width / 6;
		int roadLineY = (int) (height / 1.4);
		int roadLineWidth = width / 4;
		int roadLineHeight = height / 20;
		g.setColor(Color.WHITE);
		g.fillRect(roadLineX, roadLineY, roadLineWidth, roadLineHeight);

		// This For the Second Road Line Drawing
		int roadLineTwoX = (int) (width / 1.5);
		int roadLineTwoY = (int) (height / 1.4);
		int roadLineTwoWidth = width / 4;
		int roadLineTwoHeight = height / 20;
		g.fillRect(roadLineTwoX, roadLineTwoY, roadLineTwoWidth, roadLineTwoHeight);
		
		// This Draws the the Upper edge of the Road
		int uLinex1 = CarBody;
		int uLiney1 = height - (int)(height / 2.42);
		int uLinex2 = (int) (width);
		int uLiney2 = height - (int)(height / 2.42);
		g.setColor(Color.BLUE);
		g.drawLine(uLinex1, uLiney1, uLinex2, uLiney2);
		
		// This Draws the the Bottom edge of the Road
		int bLinex1 = CarBody;
		int bLiney1 = height - (int)(height / 12.39);
		int bLinex2 = (int) (width);
		int bLiney2 = height - (int)(height / 12.39);
		g.drawLine(bLinex1, bLiney1, bLinex2, bLiney2);

// 	Car Drawing		

		// This Draws the Back Car Wheel
		int carWheelX = xOffset + width / 19;
		int carWheel = height / 10;
		int carWheelY = (int) (height / 1.5) + carWheel;
		int carWheelWidth = width / 6;
		g.setColor(Color.darkGray);
		g.fillOval(carWheelX, carWheelY, carWheelWidth, carWheel);
		
		// This Draws the The front Car Wheel
		int carWheelTwoX = xOffset + (int) (width / 3.5);
		int carWheelTwoY = (int) (height / 1.5) + carWheel;
		int carWheelTwo = height / 10;
		int carWheelTwoWidth = width / 6;
		g.fillOval(carWheelTwoX, carWheelTwoY, carWheelTwoWidth, carWheelTwo);

		// This Draws the Car Body
		int carBody = height / 6;
		int carBodyY = (int) (height / 1.5) - carBody / 12;
		int carBodyWidth = width / 2;
		g.setColor(Color.GRAY);
		g.fillOval(xOffset, carBodyY, carBodyWidth, carBody);

		// This Draw the Car Cab
		int carCab = height / 6;
		int carCabY = (int) (height / 1.5) - carCab / 3;
		int carCabWidth = width / 4;
		g.fillOval(xOffset, carCabY, carCabWidth, carCab);

		// This Draws the Car Window
		int carWindowX = xOffset + (width / 25);
		int carWindow = height / 20;
		int carWindowY = (int) (height / 1.5) - carWindow / 3;
		int carWindowWidth = width / 6;
		g.setColor(Color.darkGray);
		g.fillOval(carWindowX, carWindowY, carWindowWidth, carWindow);

// background Drawing

		// Text on the screen.
		g.setColor(Color.orange);
		String text = "WOW, That's a Cool Looking Car!";
		g.setFont(new Font ("Serif", Font.BOLD, width / 30));
		int stringX = width / 3;
		int stringY = ((int) (height / 1.6) - height / 2);
		g.drawString(text,stringX,stringY);

		// The Right Shrub Stick Drawing
		Color StickColor = new Color(135, 109, 31);
		g.setColor(StickColor);

		int stickX = (int) (width / 1.26);
		int stickY = height - height / 2;
		int stickWidth = width / 50;
		int stickHeight = height / 13;
		g.fillRect(stickX, stickY, stickWidth, stickHeight);
		
		// The Left Shrub Stick Drawing
		int stickTwoX = (int) (width / 1.19);
		int stickTwoY = height - height / 2;
		int stickTwoWidth = width / 50;
		int stickTwoHeight = height / 13;
		g.fillRect(stickTwoX, stickTwoY, stickTwoWidth, stickTwoHeight);

		// The Center Side of Shrub Drawing
		Color shrubColor = new Color(37, 138, 46);
		g.setColor(shrubColor);

		int shrubX = (int) (width / 1.35);
		int shrubY = (int) (height / 1.5) - height / 2;
		int shrubWidth = width / 6;
		int shrubHeight = (int) (height / 2.7);
		g.fillOval(shrubX, shrubY, shrubWidth, shrubHeight);
		
		// The Left Side of Shrub Drawing
		int shrubTwoX = (int) (width / 1.2);
		int shrubTwoY = (int) (height / 1.29) - height / 2;
		int shrubTwoWidth = width / 6;
		int shrubTwoHeight = (int) (height / 3.5);
		g.fillOval(shrubTwoX, shrubTwoY, shrubTwoWidth, shrubTwoHeight);
		
		// The Right Side of Shrub Drawing
		int shrubThreeX = (int) (width / 1.55);
		int shrubThreeY = (int) (height / 1.29) - height / 2;
		int shrubThreeWidth = width / 6;
		int shrubThreeHeight = (int) (height / 3.5);
		g.fillOval(shrubThreeX, shrubThreeY, shrubThreeWidth, shrubThreeHeight);
		
// 	Avatar Drawing

		// The Right Leg of Avatar 
		g.setColor(Color.WHITE);
		int bodyLegX = width / 6;
		int bodyLegY = (int) (height / 1.1) - height / 2;
		int bodyLegWidth = width / 20;
		int bodyLegHeight = height / 9;
		g.fillRect(bodyLegX, bodyLegY, bodyLegWidth, bodyLegHeight);

		// The Left Leg of Avatar
		int BodyLegTwoX = (int) (width / 4.135);
		int BodyLegTwoY = (int) (height / 1.1) - height / 2;
		int BodyLegTwoWidth = width / 20;
		int BodyLegTwoHeight = height / 9;
		g.fillRect(BodyLegTwoX, BodyLegTwoY, BodyLegTwoWidth, BodyLegTwoHeight);

		// The Body of the Avatar
		int AvatarBodyX = width / 6;
		int AvatarBodyY = (int) (height / 1.5) - height / 2;
		int AvatarBodyWidth = width / 8;
		int AvatarBodyHeight = height / 4;
		g.fillRect(AvatarBodyX, AvatarBodyY, AvatarBodyWidth, AvatarBodyHeight);

		// The Left Arm of the Avatar
		int BodyArmX = (int) (width / 3.45);
		int BodyArmY = (int) (height / 1.4) - height / 2;
		int BodyArmWidth = width / 10;
		int BodyArmHeight = height / 20;
		g.fillRect(BodyArmX, BodyArmY, BodyArmWidth, BodyArmHeight);
		
		// The Right Arm Of the Avatar
		int BodyArmTwoX = width / 14;
		int BodyArmTwoY = (int) (height / 1.4) - height / 2;
		int BodyArmTwoWidth = width / 10;
		int BodyArmTwoHeight = height / 20;
		g.fillRect(BodyArmTwoX, BodyArmTwoY, BodyArmTwoWidth, BodyArmTwoHeight);

		// The Face of the Avatar
		int FaceX = (int) (width / 5.6);
		int FaceY = (int) (height / 1.8) - height / 2;
		int FaceWidth = width / 10;
		int FaceHeight = height / 8;
		g.fillOval(FaceX, FaceY, FaceWidth, FaceHeight);

		// The Right Eye of the Avatar
		g.setColor(Color.BLUE);
		int eyesX = (int) (width / 5.1);
		int eyesY = (int) (height / 1.76) - height / 2;
		int eyesWidth = width / 50;
		int eyesHeight = height / 30;
		g.drawOval(eyesX, eyesY, eyesWidth, eyesHeight);

		// The Left Eye of the Avatar
		int eyesTwoX = (int) (width / 4.2);
		int eyesTwoY = (int) (height / 1.76) - height / 2;
		int eyesTwoWidth = width / 50;
		int eyesTwoHeight = height / 30;
		g.drawOval(eyesTwoX, eyesTwoY, eyesTwoWidth, eyesTwoHeight);

		// Put your code above this line. This makes the drawing smoother.
		Toolkit.getDefaultToolkit().sync();
	}

	// ==============================================================
	// You don't need to modify anything beyond this point.
	// ==============================================================

	/**
	 * Starting point for this program. Your code will not go in the main method for
	 * this program. It will go in the paintComponent method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main(String[] args) {
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame("Traffic Animation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables. Only
	 * called once, when the program first begins. This method also sets up a Timer
	 * that will call paint() with frequency specified by the DELAY constant.
	 */
	public TrafficAnimation() {
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		// Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically. DO NOT MODIFY this method!
	 */
	private void startAnimation() {
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires. DO NOT MODIFY this
	 * class!
	 */
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}