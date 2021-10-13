import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author HeshamNatouf
 *
 */

public class GridMonitor implements GridMonitorInterface {
	private String topRow = "topRow";
	private String bottomRow = "bottomRow";
	private String rightCol = "rightCol";
	private String leftCol = "leftCol";
	private int columns;
	private int rows;
	static StringBuilder b = new StringBuilder("");
	Scanner scan;
	private double[][] BaseGrid;
	private double[][] SumGrid;
	private double[][] AvgGrid;
	private double[][] DeltaGrid;
	private double[][] DangerGrid;
	
	
	public String[][] fillBase() {
		for (int i = 0; i < BaseGrid.length; i++) {
			for (int j = 0; j < BaseGrid[i].length; j++) {
				if (scan.hasNext()) {
					BaseGrid[i][j] = scan.nextDouble();
				}
			}
		}
		String[][] fillGrid = new String[rows][columns];
		for (int i = 1; i < fillGrid[0].length - 1; i++) {
			fillGrid[0][i] = topRow;
		}
		for (int i = 1; i < fillGrid.length - 1; i++) {
			fillGrid[i][0] = leftCol;
		}
		for (int i = 1; i < fillGrid[0].length - 1; i++) {
			fillGrid[fillGrid.length - 1][i] = bottomRow;
		}
		for (int i = 1; i < fillGrid.length - 1; i++) {
			fillGrid[i][fillGrid[0].length - 1] = rightCol;
		}
		
		return fillGrid;
	}
	
	public GridMonitor(String filename) throws FileNotFoundException {
		scan = new Scanner(new File(filename)); 
		rows = scan.nextInt();
		columns = scan.nextInt();
		BaseGrid = new double[rows][columns];
		fillBase();
	}

	
	public double[][] getBaseGrid() {
		double[][] base = new double[rows][columns];
		for (int i = 0; i < base.length; i++) {
			for (int j = 0; j < base[i].length; j++) {
				base[i][j] = BaseGrid[i][j];
			}
		}
		return base;
	}

	public double[][] getSurroundingSumGrid() {
		double sum = 0;
		SumGrid = new double[rows][columns];
		String[][] fillGrid = fillBase();
		
		if (rows == 1 && columns == 1) {
			SumGrid[0][0] = (BaseGrid[0][0] * 4);
			return SumGrid;
		}

		for (int row = 0; row < SumGrid.length; row++) {
			for (int col = 0; col < SumGrid[row].length; col++) {
				if (row == 0 && col == 0) {
					sum += (BaseGrid[0][0] * 2);
					sum += BaseGrid[row][col + 1];
					sum += BaseGrid[row + 1][col];
				}
				// this for the top right cell of the grid
				if (row == 0 && col == columns - 1) {
					sum += (BaseGrid[0][columns - 1] * 2);
					sum +=  BaseGrid[1][columns - 1];
					sum +=  BaseGrid[0][columns - 2];
				}
				//this for the bottom left cell of the grid
				if (col == 0 && row == rows - 1) {
					sum +=  BaseGrid[rows - 2][0];
					sum +=  BaseGrid[rows - 1][1];
					sum += (BaseGrid[rows - 1][0] * 2);
				}
				// this for the bottom right cell of the grid
				if (row == rows - 1 && col == columns - 1) {
					sum += (BaseGrid[rows - 1][columns - 1] * 2);
					sum +=  BaseGrid[row - 1][col];
					sum +=  BaseGrid[row][col - 1];
				}
		
		if (fillGrid[row][col] != null) {
			// this for the top center row of the border case
			if (fillGrid[row][col].equalsIgnoreCase(topRow)) {
				sum += BaseGrid[row][col];
				sum += BaseGrid[row][col - 1];
				sum += BaseGrid[row][col + 1];
				sum += BaseGrid[row + 1][col];
			// this for the bottom center row of the border case
			} else if(fillGrid[row][col].equalsIgnoreCase(bottomRow)) {
				sum += BaseGrid[row][col];
				sum += BaseGrid[row][col - 1];
				sum += BaseGrid[row][col + 1];
				sum += BaseGrid[row - 1][col];
			// this for the right center columns of the border case
			} else if (fillGrid[row][col].equalsIgnoreCase(rightCol)) {
				sum += BaseGrid[row][col];
				sum += BaseGrid[row][col - 1];
				sum += BaseGrid[row + 1][col];
				sum += BaseGrid[row - 1][col];
			// this for the left center columns of the border case
			} else {
				sum += BaseGrid[row][col];
				sum += BaseGrid[row][col + 1];
				sum += BaseGrid[row + 1][col];
				sum += BaseGrid[row - 1][col];
					
					}
			} else if (row != 0 && row != rows - 1 && col != 0 && col != columns - 1) {
					sum += BaseGrid[row + 1][col];
					sum += BaseGrid[row - 1][col];
					sum += BaseGrid[row][col + 1];
					sum += BaseGrid[row][col - 1];
				}
				SumGrid[row][col] = sum;
				sum = 0;
			}
		}
		return SumGrid;
	}

	public double[][] getSurroundingAvgGrid() {
		double[][] average = new double[AvgGrid.length][AvgGrid[0].length];;
		
		for (int i = 0; i < AvgGrid.length; i++) {
			for (int j = 0; j < AvgGrid[i].length; j++) {
					average[i][j] = average[i][j];
			}
		}
		return average;
	}

	public double[][] getDeltaGrid() {
		double[][] delta = new double[DeltaGrid.length][DeltaGrid[0].length];	
		for (int i = 0; i < DeltaGrid.length; i++) {
			for (int j = 0; j < DeltaGrid[i].length; j++) {
						delta[i][j] = delta[i][j];
			}
		}
		return delta;
	}

	public boolean[][] getDangerGrid() {
		boolean[][] danger = new boolean[DangerGrid.length][DangerGrid[0].length];
		for (int i = 0; i < DangerGrid.length; i++) {
			for (int j = 0; j < DangerGrid[i].length; j++) {
						danger[i][j] = danger[i][j];
			}
		}
		return danger;
	}
	
	

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GridMonitor [topRow=").append(topRow).append(", bottomRow=").append(bottomRow)
				.append(", rightCol=").append(rightCol).append(", leftCol=").append(leftCol).append("]");
		return builder.toString();
	}
	
	
}