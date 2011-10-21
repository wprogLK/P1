/*
 Programmierung 1 HS 2011
 Aufgabe 2-2
*/

import java.util.Date;
import java.text.*;
import java.util.Scanner;


public class Book{
	private int id;
	private String title;
	private String author;
	private Date dateOfPublication;

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	// constructors
	//TODO


	/** Returns the age of the book in days since publication */
	public int age(){
		//TODO
		return 0; //this is to avoid compiler errors, replace it!
	}

	/** Returns a String representation of the book */
	public String toString(){
		//TODO
		return ""; //this is to avoid compiler errors, replace it!
	}

	/** Reads all book data from user input */
	public void input() throws ParseException{
		Scanner scn = new Scanner(System.in);
		System.out.print("Please enter id: ");
		//TODO
	}

	// Get-/Set-methods
	//TODO
	
	// private methods --------------------------------------------
	/** Converts the Date object d into a String object */
	private String dateToString(Date d){
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.format(d);
	}

	/** Converts the String object s into a Date object */
	private Date stringToDate(String s) throws ParseException{
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.parse(s);
	}
}
