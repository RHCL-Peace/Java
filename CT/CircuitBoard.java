import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents a 2D circuit board as read from an input file.
 *  
 * @author mvail
 */
public class CircuitBoard {
	/** current contents of the board */
	private char[][] board;
	/** location of row,col for '1' */
	private Point startingPoint;
	/** location of row,col for '2' */
	private Point endingPoint;

	//constants you may find useful
	private final int ROWS; //initialized in constructor
	private final int COLS; //initialized in constructor
	private final char OPEN = 'O'; //capital 'o'
	private final char CLOSED = 'X';
	private final char TRACE = 'T';
	private final char START = '1';
	private final char END = '2';
	private final String ALLOWED_CHARS = "OXT12";

	/** Construct a CircuitBoard from a given board input file, where the first
	 * line contains the number of rows and columns as ints and each subsequent
	 * line is one row of characters representing the contents of that position.
	 * Valid characters are as follows:
	 *  'O' an open position
	 *  'X' an occupied, unavailable position
	 *  '1' first of two components needing to be connected
	 *  '2' second of two components needing to be connected
	 *  'T' is not expected in input files - represents part of the trace
	 *   connecting components 1 and 2 in the solution
	 * 
	 * @param filename
	 * 		file containing a grid of characters
	 * @throws FileNotFoundException if Scanner cannot read the file
	 * @throws InvalidFileFormatException for any other format or content issue that prevents reading a valid input file
	 */
	public CircuitBoard(String filename) throws FileNotFoundException {
		Scanner fileScan = new Scanner(new File(filename));

		int rowC = 0; // Counter row
		int rowH = 0; //Header row
		int colC = 0; // Counter Colums
		int colH = 0; // Header Colums
		boolean header = false;
		boolean vFlag = false; // vflog = Valid flag
		boolean fRowFlag = false; // fRowFlag = first Row flag
		boolean oFlag = false; // oFlag = one flag
		boolean tFlag = false; // tFlag = two flags
		while(fileScan.hasNextLine()) {
			String fileLine = fileScan.nextLine();
			Scanner lineScan = new Scanner(fileLine);
			colC = 0;
			if(header == false) {
				if(lineScan.hasNextInt()) {
					try {
						rowH = lineScan.nextInt();
						colH = lineScan.nextInt();
						board = new char[rowH][colH];
						header = true;
					} catch (InputMismatchException e) {
						lineScan.close();
						throw new InvalidFileFormatException("The header contains"
								+ " non-integer values.");
					}
					if(lineScan.hasNext()) {
						lineScan.close();
						throw new InvalidFileFormatException("The header of the file is not "
								+ "in the correct format, only 2 integers needed.");
					}
				} 
				else {
					lineScan.close();
					throw new InvalidFileFormatException("The header of the file is not "  
							+ "in the correct format, only 2 integers needed.");
				}
			} else {
				if(fRowFlag == true) {
					rowC++;
				}
				fRowFlag = true;
				while(lineScan.hasNext()) {
					if(rowC >= rowH) {
						lineScan.close();
						throw new InvalidFileFormatException("The Circuit Board doesn't match"
								+ " the size given in the header");
					}
					String nextChar = lineScan.next();
					
					if(colC >= colH) {
						lineScan.close();
						throw new InvalidFileFormatException("The Circuit Board doesn't match"
								+ " the size given in the header");
					}
					if(ALLOWED_CHARS.contains(nextChar)) {
						if(nextChar.equals("O")) {
							board[rowC][colC] = OPEN;
						} else if (nextChar.equals("X")) {
							board[rowC][colC] = CLOSED;
						} else if (nextChar.equals("1")) {
							if(oFlag == true) {
								lineScan.close();
								throw new InvalidFileFormatException("Too many start components in the file.");
							}
							board[rowC][colC] = START;
							startingPoint = new Point(rowC, colC);
							oFlag = true;
						} else if (nextChar.equals("2")) {
							if(tFlag == true) {
								lineScan.close();
								throw new InvalidFileFormatException("Too many end components in the file.");
							}
							board[rowC][colC] = END;
							endingPoint = new Point(rowC, colC);
							tFlag = true;
						} else {
							board[rowC][colC] = TRACE;
						}
					}
					else {
						lineScan.close();
						throw new InvalidFileFormatException("The file contains"
								+ " elements different than O, X, T, 1, or 2.");
					}
					colC++;	
				}
			}
			lineScan.close();
			vFlag = true;		
		}
		if(colC != colH) {
			throw new InvalidFileFormatException("The Circuit Board doesn't match the size given in the header");
		}
		if(rowC != rowH - 1) {
			throw new InvalidFileFormatException("The Circuit Board doesn't match the size given in the header");
		}
		if(oFlag == false) {
			throw new InvalidFileFormatException("The Circuit Board ending starting cannot be found to connect.");
		}
		if(tFlag == false) {
			throw new InvalidFileFormatException("The Circuit Board ending component cannot be found to connect.");
		}
		if(vFlag == false) {
			throw new InvalidFileFormatException("File Is Empty.");
		}
		ROWS = rowH;
		COLS = colH;	
	}
	
	/** Copy constructor - duplicates original board
	 * 
	 * @param original board to copy
	 */
	public CircuitBoard(CircuitBoard original) {
		board = original.getBoard();
		startingPoint = new Point(original.startingPoint);
		endingPoint = new Point(original.endingPoint);
		ROWS = original.numRows();
		COLS = original.numCols();
	}

	/** utility method for copy constructor
	 * @return copy of board array */
	private char[][] getBoard() {
		char[][] copy = new char[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				copy[row][col] = board[row][col];
			}
		}
		return copy;
	}
	
	/** Return the char at board position x,y
	 * @param row row coordinate
	 * @param col col coordinate
	 * @return char at row, col
	 */
	public char charAt(int row, int col) {
		return board[row][col];
	}
	
	/** Return whether given board position is open
	 * @param row
	 * @param col
	 * @return true if position at (row, col) is open 
	 */
	public boolean isOpen(int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
			return false;
		}
		return board[row][col] == OPEN;
	}
	
	/** Set given position to be a 'T'
	 * @param row
	 * @param col
	 * @throws OccupiedPositionException if given position is not open
	 */
	public void makeTrace(int row, int col) {
		if (isOpen(row, col)) {
			board[row][col] = TRACE;
		} else {
			throw new OccupiedPositionException("row " + row + ", col " + col + "contains '" + board[row][col] + "'");
		}
	}
	
	/** @return starting Point(row,col) */
	public Point getStartingPoint() {
		return new Point(startingPoint);
	}
	
	/** @return ending Point(row,col) */
	public Point getEndingPoint() {
		return new Point(endingPoint);
	}
	
	/** @return number of rows in this CircuitBoard */
	public int numRows() {
		return ROWS;
	}
	
	/** @return number of columns in this CircuitBoard */
	public int numCols() {
		return COLS;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				str.append(board[row][col] + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}
	
}// class CircuitBoard
