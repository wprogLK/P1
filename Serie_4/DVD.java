/*	Exercise 4.2
 * 	authors:
 * 		Urs Gerber  	09-921-156
 * 		Lukas Keller  	10-113-736
 * 
 */


public class DVD implements IArticle
{
	private int id;
	private String title;
	private int year;
	private int price; // CHF

	/** constructor */
	public DVD(int id, String title, int year, int price)
	{
		this.id = id;
		this.title = title;
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
		return id + " (DVD) " + title + ", " + year + ", " + this.price + " CHF";
	}
}
