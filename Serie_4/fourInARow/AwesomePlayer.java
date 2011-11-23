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
	
	// ¦0¦1¦2¦3¦2¦1¦0¦
	// ¦1¦2¦3¦4¦3¦2¦1¦
	// ¦2¦3¦4¦5¦4¦3¦2¦
	// ¦3¦4¦5¦6¦5¦4¦3¦
	// ¦2¦3¦4¦5¦4¦3¦2¦
	// ¦1¦2¦3¦4¦3¦2¦1¦
	
	// ¦1¦2¦3¦3¦3¦2¦1¦
	// ¦1¦2¦3¦4¦3¦2¦1¦
	// ¦3¦4¦5¦5¦5¦4¦3¦
	// ¦2¦3¦4¦5¦4¦3¦2¦
	// ¦2¦3¦4¦6¦4¦3¦2¦
	// ¦0¦2¦4¦8¦4¦1¦0¦

//	private static final int[][] quantifierMap = { { 0, 1, 2, 3, 2, 1 }, { 1, 2, 3, 4, 3, 2 }, { 2, 3, 4, 5, 4, 3 },
//			{ 3, 4, 5, 6, 5, 4 }, { 2, 3, 4, 5, 4, 3 }, { 1, 2, 3, 4, 3, 2 }, { 0, 1, 2, 3, 2, 1 } };
//	private static final int[][] quantifierMap = { { 1, 2, 3, 2, 1,0 }, { 2, 3, 4, 3, 2,1 }, { 3, 4, 5, 4, 3,2 },
//		{ 4, 5, 6, 5, 4,3 }, { 3, 4, 5, 4, 3,2 }, { 2, 3, 4, 3, 2,1 }, { 1, 2, 3, 2, 1,0 } };
	private static final int[][] quantifierMap = { { 0, 2, 2, 3, 1,1 }, { 2, 3, 3, 4, 2,2 }, { 4, 4, 4, 5, 3,3 },
		{ 8, 6, 5, 5, 4,3 },{ 4, 4, 4, 5, 3,3 }, { 2, 3, 3, 4, 2,2 }, { 0, 2, 2, 3, 1,1 } };
	
	private static final int MAX_DEPTH = 7;
	private static final int WINNING_SCORE = 1000;
	private static final int UNDEF_SCORE = 1000000;
	
	private int evals, betacuts = 0;

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
		System.out.println("\n"+VierGewinnt.displayBoard(board));
		Board boardCopy = new Board(board);

		return getBestMove(boardCopy, this.token);
	}

	private int getBestMove(Board board, Token player)
	{
		int bestMove = -1;
		int bestEval = -UNDEF_SCORE;

		for (int col = 0; col < Board.COLS; col++)
		{
			if (board.isLegalMove(col))
			{
				Board copyBoard = board.clone();
				copyBoard.makeMove(col, player);
				int eval = -negamax(copyBoard, enemy(player), -UNDEF_SCORE, UNDEF_SCORE, MAX_DEPTH);
				if (eval > bestEval)
				{
					bestEval = eval;
					bestMove = col;
				}
			}
		}
		
		System.out.println(String.format("Evals: %d, Score: %d, Cuts: %d", evals, bestEval, betacuts));
		
		if (bestEval >= WINNING_SCORE)
			System.out.println("You're fucked!");
		else if (bestEval <= -WINNING_SCORE)
			System.out.println("I'm fucked...");
		
		return bestMove;
	}

	private int evaluateBoard(Board board, Token currentPlayer)
	{
		int score = 0;
		
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
		evals++;
		int eval = -UNDEF_SCORE;
		
		Token winner = board.getWinner();
		if (winner != Token.empty)
		{
			// Someone won
			if (winner == currentPlayer)
				return WINNING_SCORE;
			else if (winner == enemy(currentPlayer))
				return -WINNING_SCORE;
		}
		
		// Remis, noone won
		if (board.isBoardFull())
			return 0;
		// maximum depth was reached
		if (depth <= 0)
			return this.evaluateBoard(board, currentPlayer);

		// Iterate over all possible moves
		for (int col = 0; col < Board.COLS; col++)
		{
			if (board.isLegalMove(col))
			{
				// Make move
				int row = board.makeMove(col, currentPlayer);
				int[] lastMove = { col, row };
				// Evaluate next move
				eval = -negamax(board, enemy(currentPlayer), -beta, -alpha, depth - 1);
				// Undo previous move
				board.undoMove(lastMove);
				// Pruning
				if (eval > alpha)
					alpha = eval;
				if (alpha >= beta)
				{
					// beta prune
					betacuts++;
					return alpha;
				}
			}
		}
		return alpha;
	}

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
