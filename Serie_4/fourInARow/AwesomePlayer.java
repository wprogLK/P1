package fourInARow;

import java.util.ArrayList;

import fourInARow.VierGewinnt.Token;

public class AwesomePlayer implements IPlayer
{
	// ¦1¦2¦3¦4¦3¦2¦1¦
	// ¦2¦3¦4¦5¦4¦3¦2¦
	// ¦3¦4¦5¦6¦5¦4¦3¦
	// ¦2¦3¦4¦5¦4¦3¦2¦
	// ¦1¦2¦3¦4¦3¦2¦1¦
	// ¦0¦1¦2¦3¦2¦1¦0¦

	private static final int[][] quantifierMap = { { 0, 1, 2, 3, 2, 1 }, { 1, 2, 3, 4, 3, 2 }, { 2, 3, 4, 5, 4, 3 },
			{ 3, 4, 5, 6, 5, 4 }, { 2, 3, 4, 5, 4, 3 }, { 1, 2, 3, 4, 3, 2 }, { 0, 1, 2, 3, 2, 1 } };
	private static final int winningQuantifier = 1000;
	private static final int MAX_DEPTH = 5;
	private int evals = 0;

	private static final int VAL_INITIALIZER = Integer.MAX_VALUE;

	private VierGewinnt.Token token;

	public String getProgrammers()
	{
		return "Urs Gerber & Lukas Keller";
	}

	public Token getToken()
	{
		return this.token;
	}

	public int getNextColumn(Token[][] board)
	{
		Board boardCopy = new Board(board);

		return getBestMove(boardCopy, this.token);
	}

	private int getBestMove(Board board, Token player)
	{
		int maxScore = -VAL_INITIALIZER;
		int maxMove = 0;
		for (int move = 0; move < Board.COLS; move++)
		{
			if (board.isLegalMove(move))
			{
				Board copyBoard = board.clone();
				copyBoard.makeMove(move, player);
				int score = minmax(copyBoard, enemy(player), 0);

				if (score >= maxScore)
				{
					maxScore = score;
					maxMove = move;
				}
			}
		}
		System.out.println("Evals:" + this.evals);
		return maxMove;
	}

	private int evaluateBoard(Board board)
	{
		int score = 0;
		evals++;
		for (int i = 0; i < Board.COLS; i++)
		{
			for (int j = 0; j < Board.ROWS; j++)
			{
				Token testToken = board.getTokenAt(i, j);
				if (testToken == this.token)
					score += AwesomePlayer.quantifierMap[i][j];
				else if (testToken == enemy(this.token))
					score -= AwesomePlayer.quantifierMap[i][j];
			}
		}
		
		if (board.getWinner()==this.token)
			score *= AwesomePlayer.winningQuantifier;
		else if (board.getWinner() == enemy(this.token))
			score *= -AwesomePlayer.winningQuantifier;

		return score;
	}

	private int minmax(Board board, Token player, int depth)
	{
		int maxScore = 0;
		int minScore = 0;		 

		if (depth >= MAX_DEPTH)
			return this.evaluateBoard(board);

		if (player == this.token)
		{
			maxScore = -VAL_INITIALIZER;
			ArrayList<Integer> possibleMoves = board.getPossibleMoves();
			for (int column : possibleMoves)
			{
				Board copyBoard = board.clone();
				copyBoard.makeMove(column, player);
				int score = minmax(copyBoard, enemy(player), depth + 1);
				if (score > maxScore)
				{
					maxScore = score;
				}
				board.undoMove();
			}
			return maxScore;
		} 
		else if (player == enemy(this.token))
		{
			minScore = VAL_INITIALIZER;
			ArrayList<Integer> possibleMoves = board.getPossibleMoves();
			for (int column : possibleMoves)
			{
				Board copyBoard = board.clone();
				copyBoard.makeMove(column, player);
				int score = minmax(copyBoard, enemy(player), depth + 1);
				if (score < minScore)
				{
					minScore = score;
				}
			}
			return minScore;
		}
		return 0;
	}

	@Override
	public void setToken(Token token)
	{
		this.token = token;
	}

	public Token enemy(Token player)
	{
		if (player == Token.player1)
			return Token.player2;
		if (player == Token.player2)
			return Token.player1;
		return Token.empty;
	}
}
