/*
 Programmierung 1 HS 2011
 Aufgabe 4-2
*/

public class Book{
	private int id;
	private String title;
	private String author;
	private int year;
	private int price; // CHF

	/** constructor */
	public Book(int id, String title, String author, int year, int price){
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
	}
}
