import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

/**
 * Lesson 4: Activity - Using Classes and Objects
 * 
 * Uses the MiniFig class to draw a custom avatar.
 * 
 * @author CS121 instructors
 * @author <you>
 */
@SuppressWarnings("serial")
public class MyAvatar extends JPanel {
	public final int INITIAL_WIDTH = 800;
	public final int INITIAL_HEIGHT = 600;

	/**
	 * Draws the picture in the panel. This is where all of your code will go.
	 * 
	 * @param canvas The drawing area of the window.
	 */
	public void paintComponent(Graphics g) {
		/*
		 * Store the height and width of the panel at the time the paintComponent()
		 * method is called.
		 */
		int currentHeight = getHeight();
		int currentWidth = getWidth();

		/* This is the anchor point for the MiniFig (x,y) -> (mid,top) */
		int mid = currentWidth / 2;
		int top = 50;

		/*
		 * This is the scaler that is used to calculate the dimensions (height / width)
		 * of each of the MiniFig components. It uses the Math.min() function to select
		 * the smaller of currentWidth/INITIAL_WIDTH and currentHeight/INITIAL_HEIGHT.
		 * This way all the components are scaled to fit within the smaller of the two
		 * panel dimensions.
		 */
		double scaleFactor = Math.min(currentWidth / (double) INITIAL_WIDTH, currentHeight / (double) INITIAL_HEIGHT);

		// TODO: 1. Instantiate a new Point object called "anchor". Use "mid" as your x
		// value and
		// "top" as your y value.
		Point anchor = new Point(mid, top);
		// TODO: 2. Instantiate a new MiniFig object and give the reference variable a
		// name of a person,
		// such as "bob". Use the MiniFig constructor with the following
		// parameters: MiniFig(g, scaleFactor, anchor)
		MiniFig bob = new MiniFig(g, scaleFactor, anchor);

		// Background WorkSpace
		g.setColor(Color.GREEN);
		int shrupX = bob.getRightHandCenterPoint().x - bob.getWidth();
		int shrupY = bob.getRightHandCenterPoint().y - bob.getFaceHeight() / 2;
		int shrupWidth = bob.getWidth();
		int shrupHeight = bob.getHeight();
		g.fillOval(shrupX, shrupY, shrupWidth, shrupHeight);

		g.setColor(Color.GREEN);
		int shrupTwoX = bob.getRightHandCenterPoint().x - bob.getWidth() / 2;
		int shrupTwoY = bob.getRightHandCenterPoint().y;
		int shrupTwoWidth = (int) (bob.getWidth() / 1.5);
		int shrupTwoHeight = bob.getHeight() / 2;
		g.fillOval(shrupTwoX, shrupTwoY, shrupTwoWidth, shrupTwoHeight);

		// TODO: 3. Create a new custom Color object. An example is shown below.
		Color torsoColor = new Color(79, 171, 68);
		// TODO: 4. Invoke the setTorsoColor(Color color) method on your MiniFig
		// instance.
		// Use your color object as a parameter to change the shirt color.
		// This lets you change the color of "bob's" shirt. :)
		bob.setTorsoColor(torsoColor);
		// TODO: 5. Invoke the draw() method on your MiniFig instance. This is where
		// "bob" is displayed on the screen.
		bob.draw();
		// TODO: 6. Adjust the size of your Avatar's window. Notice how the avatar does
		// not stay grounded
		// on the grass. To fix this, use the getBaseMidPoint() method to find the the
		// base mid point of your
		// MiniFig. This method returns a Point object that represents the x,y
		// coordinates at the
		// base of the MiniFig, right between its feet.
		// Replace the hard-coded value of grassYOffset with the y value
		// of the returned point.
		Point baseMidpoint = bob.getBaseMidPoint();
		int grassYOffset = baseMidpoint.y;

		Color grassGreen = new Color(93, 189, 81);
		g.setColor(grassGreen);
		g.fillRect(0, grassYOffset, currentWidth, currentHeight - grassYOffset);

		// TODO: 7. Create an Alias of for your MiniFig object and change the torso
		// color of the alias.
		// If in step 2 you used the variable name "bob", you can create an alias named
		// "robert"
		// using the following:
		MiniFig robert = bob;
		Color BrownColor = new Color(138, 126, 51);
		robert.setBeltColor(BrownColor);
		robert.setTorsoColor(Color.RED);
		robert.setLegColor(Color.RED);
		robert.setArmColor(BrownColor);

		// TODO: 8. Comment out the draw statement under TODO item 5 and then draw the
		// original MiniFig
		// below. If you used the variable name "bob" is would simply be the following:
		bob.draw();

		// Hat Draw
		
		Color hatColor = new Color(138, 126, 51);
		g.setColor(hatColor);

		Point capPoint = bob.getCapPoint();

		int hatX = capPoint.x - bob.getFaceHeight();
		int hatY = capPoint.y - (capPoint.y - bob.getTopMidPoint().y);
		int hatWidth = (capPoint.x + bob.getFaceWidth()) - hatX;
		int hatHeight = capPoint.y - hatY;

		g.fillRect(hatX, hatY, hatWidth, hatHeight);

		// Hat top

		int hatTwoX = capPoint.x - bob.getFaceWidth() / 2;
		int hatTwoY = hatY - bob.getFaceHeight() / 2;
		g.fillRect(hatTwoX, hatTwoY, bob.getFaceWidth(), bob.getFaceWidth() / 2);

//		Left Hand Gun (Top)	
		
		g.setColor(Color.BLACK);
		int LHandGunTwoX = bob.getLeftHandCenterPoint().x + (bob.getWidth() / 10);
		int LHandGunTwoY = bob.getLeftHandCenterPoint().y;
		int LHandGunTwoWidth = -(int) (bob.getFaceWidth() / 2.5);
		int LHandGunTwoHeight = bob.getFaceWidth() / 6;
		g.fillRect(LHandGunTwoX, LHandGunTwoY, LHandGunTwoWidth, LHandGunTwoHeight);

//		Left Hand Gun (Bottom)
		
		g.setColor(Color.BLACK);
		int LHandGunX = bob.getLeftHandCenterPoint().x + (bob.getWidth() / 15);
		int LHandGunY = bob.getLeftHandCenterPoint().y - bob.getWidth() / 15;
		int LHandGunWidth = bob.getFaceWidth() / 6;
		int LHandGunHeight = bob.getFaceHeight();
		g.fillRect(LHandGunX, LHandGunY, LHandGunWidth, LHandGunHeight);

//		Rope Color
		
		Color RopeColor = new Color(138, 126, 51);
		g.setColor(RopeColor);

//		Right Hand Rope (part One)

		int RHandRopeX = bob.getRightHandCenterPoint().x;
		int RHandRopeY = bob.getRightHandCenterPoint().y;
		int RHandRopeWidth = -bob.getFaceWidth() / 2;
		int RHandRopeHeight = bob.getFaceHeight() / 2;
		g.fillRect(RHandRopeX, RHandRopeY, RHandRopeWidth, RHandRopeHeight);

// 		Right Hand Rope (part Two)

		int RHandRopeTwoX = bob.getRightHandCenterPoint().x;
		int RHandRopeTwoY = bob.getRightHandCenterPoint().y;
		int RHandRopeTwoWidth = -bob.getFaceWidth() / 6;
		int RHandRopeTwoHeight = (int) (bob.getHeight() / 1.5);
		g.fillRect(RHandRopeTwoX, RHandRopeTwoY, RHandRopeTwoWidth, RHandRopeTwoHeight);

// 		Right Hand Rope (part Three)

		int RHandRopeThreeX = bob.getRightHandCenterPoint().x;
		int RHandRopeThreeY = bob.getRightHandCenterPoint().y + (bob.getWidth() + 2);
		int RHandRopeThreeWidth = -(int) (bob.getFaceWidth() * 2.72);
		int RHandRopeThreeHeight = (int) (bob.getFaceHeight() / 4.5);
		g.fillRect(RHandRopeThreeX, RHandRopeThreeY, RHandRopeThreeWidth, RHandRopeThreeHeight);

// 		Right Hand Rope (part Four)

		int RHandRopeFourX = bob.getRightHandCenterPoint().x - (int) (bob.getFaceWidth() * 2.5);
		int RHandRopeFourY = bob.getRightHandCenterPoint().y + bob.getFaceHeight() * 2;
		int RHandRopeFourWidth = -(int) (bob.getFaceWidth() / 4.5);
		int RHandRopeFourHeight = bob.getFaceHeight();
		g.fillRect(RHandRopeFourX, RHandRopeFourY, RHandRopeFourWidth, RHandRopeFourHeight);

//		Right Hand Rope (part Five)
		
		int RHandRopeFiveX = bob.getRightHandCenterPoint().x - (int) (bob.getFaceWidth() * 2.72);
		int RHandRopeFiveY = bob.getRightHandCenterPoint().y + (bob.getFaceHeight() * 2);
		int RHandRopeFiveWidth = bob.getFaceWidth() * 2;
		int RHandRopeFiveHeight = -bob.getFaceHeight() / 4;
		g.fillRect(RHandRopeFiveX, RHandRopeFiveY, RHandRopeFiveWidth, RHandRopeFiveHeight);

// 		Right Hand Rope (part Six) 
		
		int RHandRopesixX = bob.getRightHandCenterPoint().x - (bob.getWidth() / 3);
		int RHandRopesixY = bob.getRightHandCenterPoint().y + (int) (bob.getFaceHeight() * 1.77);
		int RHandRopesixWidth = (int) (bob.getFaceWidth() / 4.5);
		int RHandRopesixHeight = (int) (bob.getHeight() / 3.1);
		g.fillRect(RHandRopesixX, RHandRopesixY, RHandRopesixWidth, RHandRopesixHeight);

// 		Right Hand Rope (part Seven)(Knot)
		
		int RHandRopesevenX = bob.getRightHandCenterPoint().x - (int) (bob.getWidth() / 2.6);
		int RHandRopesevenY = bob.getRightHandCenterPoint().y + (int) (bob.getFaceHeight() * 2.82);
		int RHandRopesevenWidth = bob.getFaceWidth() / 2;
		int RHandRopesevenHeight = bob.getFaceHeight() / 2;
		g.fillRect(RHandRopesevenX, RHandRopesevenY, RHandRopesevenWidth, RHandRopesevenHeight);
	}

	/**
	 * Constructor (panel initialization)
	 */
	public MyAvatar() {
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
	}

	/**
	 * Sets up a JFrame and the MiniFigDriver panel.
	 * 
	 * @param args unused
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("MyAvatar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new MyAvatar());
		frame.pack();
		frame.setVisible(true);
	}
}