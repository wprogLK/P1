/*
 Programmierung 1 HS 2011
 Aufgabe 2-2
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Book {
	private int id;
	private String title;
	private String author;
	private Date dateOfPublication;

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	// constructors
	public Book(int id, String title, String author, Date dateOfPublication) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.dateOfPublication = dateOfPublication;
	}

	/** Returns the age of the book in days since publication */
	public int age() {

		Date today = new Date();
		int dayToday=today.getDate();
		int monthToday=today.getMonth();
		int yearToday=today.getTime();	//TODO
		
		
		today.;

		return 0; // this is to avoid compiler errors, replace it!
	}

	/** Returns a String representation of the book */
	public String toString() {
		// TODO
		return ""; // this is to avoid compiler errors, replace it!
	}

	/** Reads all book data from user input */
	public void input() throws ParseException {
		Scanner scn = new Scanner(System.in);
		System.out.print("Please enter id: ");
		// TODO
	}

	/** Getters and setters */

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Date getDateOfPublication() {
		return dateOfPublication;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDateOfPublication(Date dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	// private methods --------------------------------------------
	/** Converts the Date object d into a String object */
	private String dateToString(Date d) {
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.format(d);
	}

	/** Converts the String object s into a Date object */
	private Date stringToDate(String s) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat(DATE_FORMAT);
		return fmt.parse(s);
	}
}
