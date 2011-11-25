/*	Exercise 4.2
 * 	authors:
 * 		Urs Gerber  	09-921-156
 * 		Lukas Keller  	10-113-736
 * 
 */



public class CD implements IArticle
{
	private int id;
	private String title;
	private String author;
	private int year;
	private int price; // CHF

	/** constructor */
	public CD(int id, String title, String author, int year, int price)
	{
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.price = price;
	}

	public int getId()
	{
		return this.id;
	}

	public int getPrice()
	{
		return this.price;
	}

	public String getDescription()
	{
		return this.toString();
	}

	public String toString()
	{
		return id + " (CD) " + title + ", " + author + ", " + year + ", " + this.price + " CHF";
	}
}
