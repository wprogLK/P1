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
	private static final int MAX_DEPTH = 6;

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

		return getBestMove(boardCopy);
	}

	private int getBestMove(Board board)
	{

		int bestMoveEval = 0;
		int bestColumn = -1;

		ArrayList<Move> possibleMoves = board.getPossibleMoves(this.token);

		for (Move move : possibleMoves)
		{
			int eval = -minmax(move.board, enemy(this.token), 0);
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
		int score = 0;

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

		return score;
	}

	private int minmax(Board board, Token player, int depth)
	{
		int maxScore = 0;
		int minScore = 0;

		/*if (board.getWinner() == this.token)
			return maxScore;
		if (board.getWinner() == enemy(this.token))
			return -maxScore;*/

		if (depth >= MAX_DEPTH)
			return this.evaluateBoard(board);

		if (player == this.token)
		{
			maxScore = -VAL_INITIALIZER;
			ArrayList<Move> possibleMoves = board.getPossibleMoves(player);
			for (Move move : possibleMoves)
			{
				int score = minmax(move.board.clone(), enemy(player), depth + 1);
				if (score > maxScore)
				{
					maxScore = score;
				}
			}
			return maxScore;
		}
		else if (player == enemy(this.token))
		{
			minScore = VAL_INITIALIZER;
			ArrayList<Move> possibleMoves = board.getPossibleMoves(player);
			for (Move move : possibleMoves)
			{
				int score = minmax(move.board.clone(), enemy(player), depth + 1);
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
