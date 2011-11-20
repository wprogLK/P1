package fourInARow;

import java.util.ArrayList;
import fourInARow.VierGewinnt.Token;

public class Board
{
	public static final int COLS = VierGewinnt.COLS;
	public static final int ROWS = VierGewinnt.ROWS;
	
	private Token[][] board = new Token[COLS][ROWS]; // 7 columns with 6 fields each
	public enum Direction { HORIZONTAL, VERTICAL, DIAGONAL_RIGHT, DIAGONAL_LEFT }
	
	public Board(Token[][] board)
	{
		for (int i = 0; i < Board.COLS; i++)
		{
			 for (int j = 0; j < Board.ROWS; j++)
			 {
				 this.board[i][j] = board[i][j];
			 }
		}
	}
	
	public Board()
	{
		this.board = new Token[Board.COLS][Board.ROWS];
	}
	
	public Board(Board board)
	{
		for (int i = 0; i < Board.COLS; i++)
		{
			 for (int j = 0; j < Board.ROWS; j++)
			 {
				 this.board[i][j] = board.board[i][j];
			 }
		}
	}
	
	public Board clone()
	{
		return new Board(this);
	}
	
	public Token getTokenAt(int col, int row)
	{
		return this.board[col][row];
	}
	
	public Board makeGhostMove(int column, Token token)
	{
		Board ghostBoard = new Board();
		ghostBoard = this.clone();
		ghostBoard.insertToken(column, token);
		return ghostBoard;
	}
	
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
	
	public boolean isLegalMove(int column)
	{
		return !this.columnIsFull(column);
	}
	
	public String toString()
	{
		String rowDelimiter = "+";
		String rowNumbering = " ";
		for(int col=0; col<this.board.length; col++){
			rowDelimiter += "---+";
			rowNumbering += " "+(col+1)+"  ";
		}
		rowDelimiter += "\n";
		
		String rowStr;
		String presentation = rowDelimiter;
		for(int row=this.board[0].length-1; row >= 0; row--){
			rowStr = "| ";
			for(int col=0; col < this.board.length; col++){
				rowStr += this.board[col][row].toString() + " | ";
			}
			presentation += rowStr + "\n" + rowDelimiter;
		}
		presentation += rowNumbering;
		return presentation;
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
	
	
	/** Checks for at least four equal tokens in a row in either direction,
	    starting from the given position. */
	private boolean checkWinner(int col, int row)
	{
		for (Direction direction : Direction.values())
		{
			Token lastToken = board[col][row];
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
		return lineOfTokens;
	}
	
	private boolean checkLineOfTokens(ArrayList<Token> lineOfTokens, Token lastToken)
	{
		int counter = 0;
		for (Token token : lineOfTokens)
		{
			if (token == lastToken)
				counter++;
			else
				counter = 0;
			
			if (counter>=4)
				return true;
		}
		return false;
	}
	
	private boolean isValidPosition(int col, int row)
	{
		return (col >= 0 && col < COLS) && (row >= 0 && row < ROWS);
	}
}
