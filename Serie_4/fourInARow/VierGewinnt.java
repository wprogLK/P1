package fourInARow;
/* Programmierung 1 HS 2011 Aufgabe 4-1 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VierGewinnt {
	
	public static final int COLS = 7;
	public static final int ROWS = 6;
	
	// Enum for the different tokens (empty, O, X)
	public enum Token { 
		empty(" "),
		player1("O"),
		player2("X");
		private String representation; // string representation of value
		Token(String s) { this.representation = s; }
		public String toString() { return this.representation; }
	}
	
	private Token[][] board = new Token[COLS][ROWS]; // 7 columns with 6 fields each
	private IPlayer[] players = new IPlayer[2]; // two players
	
	/** initialize board and players and start the game */
	public void play() {
		// initialize the board
		for (Token[] column : this.board) {
			Arrays.fill(column, Token.empty);
		}
		// initialize players
		players[0] = new HumanPlayer();
		System.out.print("Play against a human opponent? (y / n) ");
		String opponent = new Scanner(System.in).nextLine();
		if(opponent.toLowerCase().equals("y")){
			players[1] = new HumanPlayer();
		}else{
			players[1] = new ComputerPlayer();
		}
		players[0].setToken(Token.player1);
		players[1].setToken(Token.player2);
		// play...
		boolean solved = false;
		int currentPlayer = (new java.util.Random()).nextInt(2);  //choose randomly who begins
		System.out.println("current player: "+currentPlayer);
		int insertCol, insertRow; // starting from 0
		while (!solved && !this.isBoardFull()){
			// get player's next "move"
			insertCol = players[currentPlayer].getNextColumn(this.board);
			// insert the token and get the row where it landed
			insertRow = this.insertToken(insertCol, players[currentPlayer].getToken());
			// check if the game is over
			solved = this.checkVierGewinnt(insertCol, insertRow);
			//switch to other player
			if (!solved) currentPlayer = (currentPlayer+1) % 2;
		}
		System.out.println(displayBoard(this.board));
		if (solved) System.out.println("Player "+players[currentPlayer].getToken() + " wins!");
		else System.out.println("Draw! Game over.");
	}
	
	
	/** Inserts the token at the specified column (if possible)
	    and returns the row where the token landed */
	private int insertToken(int column, Token tok) {
		
		if (columnIsFull(column))
			return -1;
		int finalRow = 0;
		for (int row = 0; row < ROWS; row++)
		{
			finalRow = row;
			if (this.board[column][row] == Token.empty)
			{
				this.board[column][row] = tok;
				break;
			}
		}
		return finalRow;
	}
	
	private boolean columnIsFull(int column)
	{
		for (int i = 0; i < ROWS; i++)
		{
			if (this.board[column][i] == Token.empty)
				return false;
		}
		return true;
	}
	
	
	/** Checks if every position is occupied */
	private boolean isBoardFull() {
		for (int i = 0; i < COLS; i++)
		{
			for (int j = 0; j < ROWS; j++)
			{
				if (this.board[i][j] == Token.empty)
					return false;
			}
		}
		return true;
	}
	
	public static enum Direction {HORIZONTAL, VERTICAL, DIAGONAL_RIGHT, DIAGONAL_LEFT}
	
	/** Checks for at least four equal tokens in a row in either direction,
	    starting from the given position. */
	private boolean checkVierGewinnt(int col, int row)
	{
		for (Direction direction : Direction.values())
		{
			Token lastToken = board[col][row];
			System.out.println(direction);
			if (checkLineOfTokens(getLineOfTokens(col, row, direction), lastToken))
				return true;
		}
		
		return false;
	}
	
	private ArrayList<Token> getLineOfTokens(int col, int row, Direction direction)
	{
		ArrayList<Token> lineOfTokens = new ArrayList<Token>();
		int[] offset = new int[2];
		int[] dir = new int[2];
		switch (direction)
		{
		case HORIZONTAL:
			offset[0] = -3; offset[1] = 0;
			dir[0] = 1; dir[1] = 0;
			break;
		case VERTICAL:
			offset[0] = 0; offset[1] = -3;
			dir[0] = 0; dir[1] = 1;
			break;
		case DIAGONAL_RIGHT:
			offset[0] = -3; offset[1] = -3;
			dir[0] = 1; dir[1] = 1;
			break;
		case DIAGONAL_LEFT:
			offset[0] = 3; offset[1] = -3;
			dir[0] = -1; dir[1] = 1;
			break;
		}
		
		int[] position = { col + offset[0], row + offset[1] };
		for (int i = 0; i < 7; i++)
		{
			if (isValidPosition(position[0], position[1]))
				lineOfTokens.add(this.board[position[0]][position[1]]);
			
			position[0] += dir[0];
			position[1] += dir[1];
		}
		System.out.println(Arrays.toString(lineOfTokens.toArray()));
		return lineOfTokens;
	}
	
	private boolean checkLineOfTokens(ArrayList<Token> lineOfTokens, Token lastToken)
	{
		int counter = 0;
		for (Token token : lineOfTokens)
		{
			if (token == lastToken)
			{
				counter++;
			}
			else
			{
				counter = 0;
			}
			
			if (counter>=4)
			{
				return true;
			}
			
		}
		
		return false;
	}
	
	private boolean isValidPosition(int col, int row)
	{
		return (col >= 0 && col < COLS) && (row >= 0 && row < ROWS);
	}
	
	/** returns a graphical representation of the board */
	public static String displayBoard(Token[][] myBoard){
		String rowDelimiter = "+";
		String rowNumbering = " ";
		for(int col=0; col<myBoard.length; col++){
			rowDelimiter += "---+";
			rowNumbering += " "+(col+1)+"  ";
		}
		rowDelimiter += "\n";
		
		String rowStr;
		String presentation = rowDelimiter;
		for(int row=myBoard[0].length-1; row >= 0; row--){
			rowStr = "| ";
			for(int col=0; col < myBoard.length; col++){
				rowStr += myBoard[col][row].toString() + " | ";
			}
			presentation += rowStr + "\n" + rowDelimiter;
		}
		presentation += rowNumbering;
		return presentation;
	}
	
	
	
	/** main method, starts the program */
	public static void main(String args[]){
		VierGewinnt game = new VierGewinnt();
		game.play();
	}
}
