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
	private static final int MAX_DEPTH = 8;
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
		int bestMove = -1;
		int bestEval = -99999;

		ArrayList<Integer> possibleMoves = board.getPossibleMoves();

		for (int move : possibleMoves)
		{
			Board copyBoard = board.clone();
			copyBoard.makeMove(move, player);
			int eval = -negamax(copyBoard, enemy(player), -99999, 99999, MAX_DEPTH);
			if (eval > bestEval)
			{
				bestEval = eval;
				bestMove = move;
			}
		}
		System.out.println("Evals:" + this.evals);
		return bestMove;
	}

	private int evaluateBoard(Board board, Token currentPlayer)
	{
		int score = 0;
		evals++;
		
		for (int i = 0; i < Board.COLS; i++)
		{
			for (int j = 0; j < Board.ROWS; j++)
			{
				Token testToken = board.getTokenAt(i, j);
				if (testToken == currentPlayer)
					score += AwesomePlayer.quantifierMap[i][j];
				else if (testToken == enemy(currentPlayer))
					score -= AwesomePlayer.quantifierMap[i][j];
			}
		}
		
		return score;
	}

	private int negamax(Board board, Token currentPlayer, int alpha, int beta, int depth)
	{
		int eval = 0;
		
		if (depth <= 0)
			return this.evaluateBoard(board, currentPlayer);
		
		if (board.getWinner() == currentPlayer)
			return 9999999;
		else if (board.getWinner() == enemy(currentPlayer))
			return -9999999;

		ArrayList<Integer> possibleMoves = board.getPossibleMoves();
		for (int move : possibleMoves)
		{
			Board copyBoard = board.clone();
			copyBoard.makeMove(move, currentPlayer);
			eval = -negamax(copyBoard, enemy(currentPlayer), -beta, -alpha, depth - 1);
		
			if (eval >= beta)
				return beta;
			if (eval > alpha)
				alpha = eval;
		}

		return alpha;
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
