import java.util.Random;
import java.util.Scanner;

public class GuessChess
{
	enum Direction { UP, RIGHT,  DOWN,  LEFT, NO_CHANGE};
	private GameField secretField;
	private GameStatistics stats;
	public final int MAX_GUESSES = 3;
	
	public static void main (String[] args) 
	{
		GuessChess game = new GuessChess();
		game.start();
	}

	private void start() 
	{
		Boolean playAgain = true;
		Boolean won = false, lost = false;
		this.stats = new GameStatistics();
		
		// Show welcome screen to player
		this.showWelcomeScreen();
		
		// Game loop
		while (playAgain) 
		{
			// Create random secret field
			this.secretField = GameField.createSecretGameField();
			// Draw emty Board
			this.printEmptyBoard();
			// Loop until maximum guess count is reached
			for (int i = 0; i < MAX_GUESSES; i++) 
			{
				// Print game state
				this.printState(i);
				// Ask user to guess a field
				GameField guessedField = this.guessField();
				// Print board with highlit user field
				this.printBoard(guessedField.getColumn(), guessedField.getRow());
				// Check whether player has guessed the right field
				won = this.getWinner(guessedField);
				// Determine whether player has lost
				lost = (!won) && (i == MAX_GUESSES - 1);
				// present result to player
				System.out.println(this.getFeedback(guessedField, won, lost));
				// if player has won, leave loop
				if (won) break;
			}
			// Update game statistics
			if (won) this.stats.incrementGamesWon();
			if (lost) this.stats.incrementGamesLost();
			// Ask player to start over
			System.out.print("Do you want to play again? [y/n]: ");
			Scanner scn = new Scanner(System.in);
			playAgain = (scn.nextLine().contains("y")) ? true : false;
		}
		// Print game statistics
		System.out.println(this.stats.toString());	
	}
	
	// Asks player to guess a field and returns said field
	private GameField guessField()
	{
		Scanner scn = new Scanner(System.in);
		String input = "";
		
		System.out.print("Please guess field: ");
		input = scn.nextLine();
		
		GameField guessedField = new GameField(input.charAt(1) - '0', input.charAt(0));
		
		return guessedField;
	}
	
	// Presents current game state to player
	private void printState(int tries)
	{
		System.out.print(String.format("You have %d attempts left. ", MAX_GUESSES - tries));
	}
	
	// Determines whether player has guessed the right field
	private Boolean getWinner(GameField field)
	{
		return field.equals(this.secretField);
	}
	
	// Prints feedback to player according to game state
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
	
	public class GameStatistics
	{
		private int totalGamesPlayed;
		private int gamesWon;
		private int gamesLost;
		
		public void incrementGamesWon()
		{
			this.gamesWon++;
			this.totalGamesPlayed = this.gamesWon + this.gamesLost;
		}
		public void incrementGamesLost()
		{
			this.gamesLost++;
			this.totalGamesPlayed = this.gamesWon + this.gamesLost;
		}
		public int getGamesPlayed()
		{
			return this.totalGamesPlayed;
		}
		public int getGamesWon()
		{
			return this.gamesWon;
		}
		public int getGamesLost()
		{
			return this.gamesLost;
		}
		public void reset()
		{
			this.gamesLost = 0;
			this.gamesWon = 0;
			this.totalGamesPlayed = 0;
		}
		public String toString()
		{
			StringBuilder sb = new StringBuilder();
			sb.append("Games played: " + this.totalGamesPlayed);
			sb.append(", Games won: " + this.gamesWon);
			sb.append(", Games lost: " + this.gamesLost);
			return sb.toString();
		}
	}
	
	private static class GameField 
	{
		private int row;
		private char column;
		
		public GameField()
		{
			this.row = 0;
			this.column = 0;
		}
		
		public static GameField createSecretGameField()
		{
			Random rnd = new Random();
			GameField sf = new GameField();
			sf.row = rnd.nextInt(8) + 1;
			sf.column = GuessChess.getColumnAsChar(rnd.nextInt(8) + 1);
			return sf;
		}
		
		public GameField(int row, char column)
		{
			this.row = row;
			this.column = column;
		}
		
		public int getRow() {
			return row;
		}

		@SuppressWarnings("unused")
		public void setRow(int row) {
			this.row = row;
		}

		public char getColumn() {
			return column;
		}

		@SuppressWarnings("unused")
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
