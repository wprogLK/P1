import java.util.Random;
import java.util.Scanner;

/*

*/


public class GuessChess
{

	private GameField secretField;
	public final int MAX_GUESSES = 3;
	
	public static void main (String[] args) 
	{
		GuessChess game = new GuessChess();
		game.start();
	}


	// your code goes here...
	private void start() 
	{
		this.secretField = new GameField(); // Init random
		this.showWelcomeScreen();
		
		this.printEmptyBoard();
		
		for (int i = 0; i < MAX_GUESSES; i++) 
		{
			this.printState(i);
			GameField field = this.guessField();
			this.printBoard(field.getColumn(), field.getRow());
		}
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
		System.out.println(String.format("You have %d attempts left.", MAX_GUESSES - tries));
	}
	
	private Boolean giveFeedback(GameField field)
	{
		if (field.equals(this.secretField))
		{
			System.out.println("You guessed the right field! Congratulations! But you suck anyway...");
			return true;
		}
		else
		{
			
		}
		return false;
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
		
		public enum Direction { UP, RIGHT,  DOWN,  LEFT, NO_CHANGE};
		
		public GameField()
		{
			Random rnd = new Random();
			this.row = rnd.nextInt(8);
			this.column = GuessChess.getColumnAsChar(rnd.nextInt(8));
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
		
		private static Direction compareRows(GameField guessedField, GameField secretField)
		{
			if (guessedField.getRow() > secretField.getRow())
				return Direction.DOWN;
			if (guessedField.getRow()== secretField.getRow())
				return Direction.NO_CHANGE;
			else
				return Direction.UP;
		}
		
		private static Direction compareColumns(GameField gf)
		{
			if (guessedField.getRow() > secretField.getRow())
				return Direction.DOWN;
			if (guessedField.getRow()== secretField.getRow())
				return Direction.NO_CHANGE;
			else
				return Direction.UP;
		}
		
		public Direction getDirection(GameField field)
		{
			if (this.compareRows(field))
		}
	}	
}
