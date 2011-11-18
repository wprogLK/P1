package fourInARow;

import fourInARow.VierGewinnt.Token;


public class AwesomePlayer implements IPlayer {
	private VierGewinnt.Token token;
	@Override
	public String getProgrammers() 
	{
		return "Urs Gerber & Lukas Keller";
	}


	@Override
	public Token getToken() 
	{
		return this.token;
	}

	@Override
	public int getNextColumn(Token[][] board)
	{
		
		return 0;
	}

	@Override
	public void setToken(Token token) 
	{
		this.token=token;
		
	}
	
	
	private class Board
	{
		private final int COLS=VierGewinnt.COLS;
		private final int ROWS=VierGewinnt.ROWS;
		
		private Token[][] board = new Token[COLS][ROWS]; // 7 columns with 6 fields each
		
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
	}

}
