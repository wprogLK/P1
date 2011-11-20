package fourInARow;

public class Move
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
