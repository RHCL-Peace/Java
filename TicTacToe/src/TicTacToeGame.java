import java.awt.Point;

/**
 * Create a TicTacToeGame class for TicTacToeGUI Peogram
 * 
 * @author HeshamNatouf
 *
 */

public class TicTacToeGame implements TicTacToe {
	private Point[] MAXMOVES = new Point[9];
	private BoardChoice[][] grid = new BoardChoice[3][3];
	private GameState winner;
	boolean gameOver;
	int nextMove;

	// Constructor
	public TicTacToeGame() {
		this.grid = new BoardChoice[][] {
				{ TicTacToe.BoardChoice.OPEN, 
				TicTacToe.BoardChoice.OPEN, 
				TicTacToe.BoardChoice.OPEN },

				{ TicTacToe.BoardChoice.OPEN, 
				TicTacToe.BoardChoice.OPEN, 
				TicTacToe.BoardChoice.OPEN },

				{ TicTacToe.BoardChoice.OPEN, 
				TicTacToe.BoardChoice.OPEN, 
				TicTacToe.BoardChoice.OPEN } };

		nextMove = 0;
		winner = GameState.IN_PROGRESS;
		gameOver = false;
	}

	// To start a New Game
	public void newGame() {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				grid[row][col] = BoardChoice.OPEN;
				MAXMOVES[row * grid[col].length + col] = null;
			}
		}
		nextMove = 0;
		winner = GameState.IN_PROGRESS;
		gameOver = false;
	}

	public boolean choose(BoardChoice player, int row, int col) {

		if (!gameOver() && (row <= 2 && col <= 2)) {
			if (grid[row][col].equals(BoardChoice.OPEN)) {
				grid[row][col] = player;
				MAXMOVES[nextMove] = new Point(row, col);
				nextMove++;
				return true;
			}
		}
		return false;
	}

	// Game Over
	public boolean gameOver() {
		if (!getGameState().equals(GameState.IN_PROGRESS)) {
			gameOver = true;
		}
		return gameOver;
	}

	// Return Game State
	public GameState getGameState() {

		for (int row = 0; row < grid.length; row++) {
			if (grid[row][0].equals(grid[row][1]) && grid[row][1].equals(grid[row][2])) {
				if (grid[row][0].equals(BoardChoice.X)) {
					winner = GameState.X_WON;
				} else if (grid[row][0].equals(BoardChoice.O)) {
					winner = GameState.O_WON;
				}
			}
			for (int col = 0; col < grid.length; col++) {
				if (grid[0][col].equals(grid[1][col]) && grid[1][col].equals(grid[2][col])) {
					if (grid[0][col].equals(BoardChoice.X)) {
						winner = GameState.X_WON;
					} else if (grid[0][col].equals(BoardChoice.O)) {
						winner = GameState.O_WON;
					}
				}
			}
			if (grid[0][0].equals(grid[1][1]) && grid[0][0].equals(grid[2][2])) {
				if (grid[0][0].equals(BoardChoice.X)) {
					winner = GameState.X_WON;
				} else if (grid[0][0].equals(BoardChoice.O)) {
					winner = GameState.O_WON;
				}
			} else if (grid[0][2].equals(grid[1][1]) && grid[0][2].equals(grid[2][0])) {
				if (grid[0][2].equals(BoardChoice.X)) {
					winner = GameState.X_WON;
				} else if (grid[0][2].equals(BoardChoice.O)) {
					winner = GameState.O_WON;
				}
			} else if (nextMove == 9 && winner == GameState.IN_PROGRESS) {
				winner = GameState.TIE;
			}
		}
		return winner;
	}

	// Get Game Grid
	public BoardChoice[][] getGameGrid() {
		BoardChoice[][] boared = this.grid;
		return boared;
	}

	// Get Player Moves
	public Point[] getMoves() {

		Point[] copy = new Point[nextMove];
		for (int i = 0; i < nextMove; i++) {
			copy[i] = MAXMOVES[i];

		}
		return copy;
	}
}
