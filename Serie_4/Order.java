/*	Exercise 4.2
 * 	authors:
 * 		Urs Gerber  	09-921-156
 * 		Lukas Keller  	10-113-736
 * 
 */

import java.util.ArrayList;

public class Order
{
	private int id;
	private String customerName;
	private String customerAddress;
	private ArrayList<IArticle> articles;

	private static int idCounter = 1;

	public Order()
	{
		this.id = idCounter++;
		this.customerName = "";
		this.customerAddress = "";
		this.articles = new ArrayList<IArticle>();
	}

	public Order(String customerName, String customerAddress)
	{
		this.id = idCounter++;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.articles = new ArrayList<IArticle>();
	}

	public void add(IArticle article)
	{
		this.articles.add(article);
	}

	public int getTotalPrice()
	{
		int totalPrice = 0;
		for (IArticle article : this.articles)
		{
			totalPrice += article.getPrice();
		}
		return totalPrice;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public void setCustomerAddress(String customerAddress)
	{
		this.customerAddress = customerAddress;
	}
	
	public int getId()
	{
		return this.id;
	}

	public String getCustomerName()
	{
		return this.customerName;
	}

	public String getCustomerAddress()
	{
		return this.customerAddress;
	}

	public Iterable<IArticle> getOrderedArticles()
	{
		// Do not return List itself --> security | with a direct reference to the list,
		// it would be possible to alter the list outside of the containing class (eg. .add or .remove)
		return (Iterable<IArticle>)articles;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("Order id: %d, Customer: %s, %s\n", this.id, this.customerName, this.customerAddress));
		for (IArticle article : this.articles)
		{
			sb.append(article.getDescription() + "\n");
		}
		sb.append(String.format("Total price: %d CHF", this.getTotalPrice()));

		return sb.toString();
	}
}
