/*
 Programmierung 1 HS 2011
 Aufgabe 2-2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
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
	public Book()
	{
		this.id = 0;
		this.title = "";
		this.author = "";
		this.dateOfPublication = new Date();		
	}
	
	public Book(int id, String title, String author, Date dateOfPublication) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.dateOfPublication = dateOfPublication;
	}

	/** Returns the age of the book in days since publication */
	public int age() {

		Date today = new Date();
		long diff_ms = today.getTime() - this.dateOfPublication.getTime();
		long diff_days = (diff_ms / (1000*60*60*24));

		return (int)diff_days;
	}

	/** Returns a String representation of the book */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat datefmt = new SimpleDateFormat(DATE_FORMAT);
		
		sb.append(this.id + ", ");
		sb.append(this.title + ", ");
		sb.append(this.author + ", ");
		sb.append(datefmt.format(this.dateOfPublication));
		
		return sb.toString();
	}

	/** Reads all book data from user input */
	public void input() throws ParseException {
		
		//Scanner scn = new Scanner(System.in);
		
		//System.out.print("Please enter id: ");
		//this.id = scn.nextInt();
		
		//System.out.print("Please enter title: ");
		//this.title = scn.nextLine();
		
		//System.out.print("Please enter author: ");
		//this.author = scn.nextLine();
		
		//System.out.print("Please enter date of publication (format dd.mm.yyyy): ");
		//this.dateOfPublication = stringToDate(scn.nextLine());	
		
		
		this.id=Integer.parseInt(this.askUser("Please enter id: "));
		this.title=this.askUser("Please enter title: ");
		this.author=this.askUser("Please enter author: ");
		this.dateOfPublication=this.stringToDate(this.askUser("Please enter date of publication (format dd.mm.yyyy): "));
	}
	
	private String askUser(String message)
	{
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String line = null;

		System.out.print(message);	

		try 
		{
			line = console.readLine();
		} 
		catch (IOException e) 
		{
			askUser(message);
		}
		
		return line;
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
