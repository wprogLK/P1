package fourInARow;

import java.util.ArrayList;

import fourInARow.VierGewinnt.Direction;
import fourInARow.VierGewinnt.Token;


public class AwesomePlayer implements IPlayer 
{
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
		Board boardCopy = new Board(board);
		
		return getBestMove(boardCopy);
	}
		
	private int getBestMove(Board board)
	{
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		
		for (int col = 0; col < Board.COLS; col++)
		{
			if (board.isLegalMove(col))
			{
				possibleMoves.add(new Move(col, board.makeGhostMove(col, this.token)));
			}
		}
		
		int bestMoveEval = 0;
		int bestColumn = -1;
		
		for (Move move : possibleMoves)
		{
			int eval = evaluateBoard(move.board);
			if (eval >= bestMoveEval)
			{
				bestMoveEval = eval;
				bestColumn = move.column;
			}
		}
		
		return bestColumn;
	}
	
	private int evaluateBoard(Board board)
	{
		return 0;
	}
	
	private boolean checkWinner(Token player)
	{
		return false;
	}

	@Override
	public void setToken(Token token) 
	{
		this.token = token;
	}
	
	private class Move
	{
		public Board board;
		public int column;
		
		public Move()
		{
			this.board = new Board();
			this.column = -1;
		}
		
		public Move(int column, Board board)
		{
			this.column = column;
			this.board = board;
		}
	}
}
