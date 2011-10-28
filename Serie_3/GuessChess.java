import java.util.Random;
import java.util.Scanner;

public class GuessChess
{
	enum Direction { UP, RIGHT,  DOWN,  LEFT, NO_CHANGE};
	private GameField secretField;
	public final int MAX_GUESSES = 3;
	
	public static void main (String[] args) 
	{
		GuessChess game = new GuessChess();
		game.start();
	}

	private void start() 
	{
		int gamesLost = 0, gamesWon = 0;
		Boolean playAgain = true;
		Boolean won = false, lost = false;
		
		this.showWelcomeScreen();
		
		while (playAgain) 
		{
			this.secretField = new GameField(); // Init random
			this.printEmptyBoard();
			for (int i = 0; i < MAX_GUESSES; i++) 
			{
				this.printState(i);
				GameField guessedField = this.guessField();
				this.printBoard(guessedField.getColumn(), guessedField.getRow());
				won = this.getWinner(guessedField);
				lost = (!won) && (i == MAX_GUESSES - 1);
				System.out.println(this.getFeedback(guessedField, won, lost));
				if (won) break;
			}
			
			if (won) gamesWon++;
			if (lost) gamesLost++;
			
			System.out.print("Do you want to play again? [y/n]: ");
			Scanner scn = new Scanner(System.in);
			playAgain = (scn.nextLine().contains("y")) ? true : false;
		}
		System.out.println(String.format("Games played: %d, Games won: %d, Games lost: %d", 
				gamesLost+gamesWon, gamesWon, gamesLost));		
	}
	
	private GameField guessField()
	{
		Scanner scn = new Scanner(System.in);
		String input = "";
		
		System.out.print("Please guess field: ");
		input = scn.nextLine();
		
		GameField guessedField = new GameField(input.charAt(1) - '0', input.charAt(0));
		
		return guessedField;
	}
	
	private void printState(int tries)
	{
		System.out.print(String.format("You have %d attempts left. ", MAX_GUESSES - tries));
	}
	
	private Boolean getWinner(GameField field)
	{
		return field.equals(this.secretField);
	}
	
	private String getFeedback(GameField field, Boolean won, Boolean gameOver)
	{
		if (won)
			return "You guessed the right field. Congratulations!";
		
		StringBuilder sb = new StringBuilder();
		Direction[] dir = field.getDirectionTo(secretField);
		
		if (gameOver)
		{
			sb.append("Your guess was wrong and you lost. ");
			sb.append("The secret field was on " + this.secretField.toString());
			return sb.toString();
		}
		
		sb.append("Your guess was wrong! You have to go ");
		
		switch (dir[0])
		{
		case LEFT:
			sb.append("LEFT");
			break;
		case RIGHT:
			sb.append("RIGHT");
			break;
			default: break;
		}
		
		if (dir[0] != Direction.NO_CHANGE && dir[1] != Direction.NO_CHANGE)
			sb.append(" and ");
		
		switch (dir[1])
		{
		case UP:
			sb.append("UP");
			break;
		case DOWN:
			sb.append("DOWN");
			break;
			default: break;
		}
		
		return sb.toString();
	}	

	// ==================================================================
	// Helper methods:

	/** prints a chess board with an 'X' on the field defined by column and row	*/
	private void printBoard(char column, int row){
		int column_num = getColumnAsInt(column);
		String board = "  _ _ _ _ _ _ _ _\n";
		for(int i=8; i>0; i--){
			board += i;
			if(row==i){
				for(int j=1; j<=8; j++){
					if(column_num==j){
						board += "|X";
					}else{
						board += "|_";
					}
				}
				board += "|\n";
			}else{
				board += "|_|_|_|_|_|_|_|_|\n";
			}
		}
		board += "  a b c d e f g h ";
		System.out.println(board);
	}
	/** print the board without an 'X' */
	private void printEmptyBoard(){
		printBoard('z',-1);
	}
	
	private void showWelcomeScreen(){
		String s="";
		s += "           ()\n";
		s += "         <~~~~>\n";
		s += "          \\__/                     ___/\"\"\"\n";
		s += "         (____)                   |___ 0 }\n";
		s += "          |  |    *************     /    }\n";
		s += "          |__|    *GUESS CHESS*    /     }\n";
		s += "         /____\\   *************    \\____/\n";
		s += "        (______)                   /____\\\n";
		s += "       (________)                 (______)\n";
		System.out.println(s);
	}
	
	public static int getColumnAsInt(char column){
		switch(column){
			case 'a': return 1;
			case 'b': return 2;
			case 'c': return 3;
			case 'd': return 4;
			case 'e': return 5;
			case 'f': return 6;
			case 'g': return 7;
			case 'h': return 8;
			default: return 0;
		}
	}
	public static char getColumnAsChar(int column){
		switch(column){
			case 1: return 'a';
			case 2: return 'b';
			case 3: return 'c';
			case 4: return 'd';
			case 5: return 'e';
			case 6: return 'f';
			case 7: return 'g';
			case 8: return 'h';
			default: return 'z';
		}
	}
	
	private class GameField 
	{
		private int row;
		private char column;
		
		public GameField()
		{
			Random rnd = new Random();
			this.row = rnd.nextInt(8) + 1;
			this.column = GuessChess.getColumnAsChar(rnd.nextInt(8) + 1);
		}
		public GameField(int row, char column)
		{
			this.row = row;
			this.column = column;
		}
		
		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public char getColumn() {
			return column;
		}

		public void setColumn(char column) {
			this.column = column;
		}

		public Boolean equals(GameField gf)
		{
			if (this.column == gf.getColumn() && this.row == gf.getRow())
				return true;
			return false;
		}
		
		private Direction compareRowsTo(GameField targetField)
		{
			if (targetField.getRow() > this.row)
				return Direction.UP;
			if (targetField.getRow() == this.row)
				return Direction.NO_CHANGE;
			else
				return Direction.DOWN;
		}
		
		private Direction compareColumnsTo(GameField targetField)
		{
			int tfi = getColumnAsInt(targetField.getColumn());
			int ti = getColumnAsInt(this.column);
			
			if (tfi > ti)
				return Direction.RIGHT;
			if (tfi == ti)
				return Direction.NO_CHANGE;
			else
				return Direction.LEFT;
		}
		
		public Direction[] getDirectionTo(GameField targetField)
		{
			Direction[] dir = new Direction[2];
			dir[0] = this.compareColumnsTo(targetField);
			dir[1] = this.compareRowsTo(targetField);
			return dir;
		}
		
		public String toString()
		{
			return String.format("%s%d", this.column, this.row);
		}
	}	
}
