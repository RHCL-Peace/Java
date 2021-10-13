import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}
		try {
			new CircuitTracer(args); //create this with args
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {
		// usage instructions when there are problems with
		// any command line args
		System.out.println("The usage of CircuitTracer.java is: $"
				+ " java CircuitTracer [ -s || -q ] [ -c || -g ] [ filename ] "
				+ "\n [-s] for Stack."
				+ "\n [-q] for Queue Storage container"
				+ "\n [-c] for Console output."
				+ "\n [-g] for GUI output."
				+ "\n filename must be a valid file and valid path.");
	}
	
	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	private CircuitTracer(String[] args) {
		CircuitBoard circuit;
		Storage<TraceState> store = null;
		try {
			if(args[0].equals("-s")) {
				store = new Storage<TraceState>(Storage.DataStructure.stack);
			} else if (args[0].equals("-q")) {
				store = new Storage<TraceState>(Storage.DataStructure.queue);
			} else {
				printUsage();
				System.exit(1);
			} 
			if (args[1].equals("-c")) {
				ArrayList<TraceState> tracePath = new ArrayList<TraceState>();
				circuit = new CircuitBoard(args[2]);
				//finds the possible paths
				tracePath = sort(circuit, store);			
				//prints out the Paths
				for(TraceState state : tracePath) {
					System.out.println(state.toString());
				}	
			} else if (args[1].equals("-g")) {
				System.out.println("GUI is currently unavailable.");
				System.exit(1);
			} else {
				printUsage();
				System.exit(1);
			}
		}
		catch (FileNotFoundException e) {
			printUsage();
			System.exit(1);
		}
		catch (InvalidFileFormatException e) {
			System.out.println(e.toString());
			System.exit(1);
		}
	}
	
	/**
	 * Private sorting method to find the shortest traces of the circuit board.
	 */
	private ArrayList<TraceState> sort(CircuitBoard board, Storage<TraceState> store) {
		ArrayList<TraceState> path = new ArrayList<TraceState>();
		Point start = new Point(board.getStartingPoint());

		// checks if moves is allowed in any direction up, down, right, and lift and if so create and store
		// the opening traceState in specific direction
		
		// moveing up
		if(board.isOpen((int)start.getX()+1, (int)start.getY())) {
			store.store(new TraceState(board, (int)start.getX()+1, (int)start.getY()));
		}
		// moving down 
		if(board.isOpen((int)start.getX()-1, (int)start.getY())) { 
			store.store(new TraceState(board, (int)start.getX()-1, (int)start.getY()));
		}
		// moving right 
		if(board.isOpen((int)start.getX(), (int)start.getY()+1)){
			store.store(new TraceState(board, (int)start.getX(), (int)start.getY()+1));
		}
		// moving left
		if(board.isOpen((int)start.getX(), (int)start.getY()-1)) {
			store.store(new TraceState(board, (int)start.getX(), (int)start.getY()-1));
		}
		while(!store.isEmpty()) {
			TraceState tempTrace = store.retrieve();
			if(tempTrace.isComplete()) {
				if(path.isEmpty() || tempTrace.pathLength() == path.get(0).pathLength()) {
					path.add(tempTrace);
				} else if (tempTrace.pathLength() < path.get(0).pathLength()) {
					path.clear();
					path.add(tempTrace);
				}
			} else {
				// try moving up
				if(tempTrace.isOpen(tempTrace.getRow()+1, tempTrace.getCol())) {
					store.store(new TraceState(tempTrace, tempTrace.getRow()+1, tempTrace.getCol()));
				}
				// try moving down
				if(tempTrace.isOpen(tempTrace.getRow()-1, tempTrace.getCol())) {
					store.store(new TraceState(tempTrace, tempTrace.getRow()-1, tempTrace.getCol()));
				}
				// try moving right
				if(tempTrace.isOpen(tempTrace.getRow(), tempTrace.getCol()+1)) {
					store.store(new TraceState(tempTrace, tempTrace.getRow(), tempTrace.getCol()+1));
				}
				// trying moving left
				if(tempTrace.isOpen(tempTrace.getRow(), tempTrace.getCol()-1)) {
					store.store(new TraceState(tempTrace, tempTrace.getRow(), tempTrace.getCol()-1));
				}
			}
		}
		return path;

	}
	
} // class CircuitTracer
