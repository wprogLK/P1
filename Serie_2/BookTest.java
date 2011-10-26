import java.text.ParseException;
import java.util.Scanner;

public class BookTest
{
	public static void main(String[] args) throws ParseException
	{
		// Create new Book instance
		Book book = new Book();
		// Set up properties
		book.input();
		// Show properties to user, test getters
		System.out.println(String.format("This book's ID is %d", book.getId()));
		System.out.println(String.format("The author of this book is '%s'", book.getAuthor()));
		System.out.println(String.format("The title of this book is '%s'", book.getTitle()));
		System.out.println(String.format("This book was published %d days ago.", book.age()));

		// Test setters
		Scanner scn = new Scanner(System.in);

		System.out.print("Enter the book's new ID: ");
		book.setId(scn.nextInt());
		scn.nextLine();
		System.out.print("Enter the book's new Author: ");
		book.setAuthor(scn.nextLine());
		System.out.print("Enter the book's new Title: ");
		book.setTitle(scn.nextLine());
		System.out.print("Enter the book's new Date of Publication: ");
		book.setDateOfPublication(scn.nextLine());

		System.out.println("The properties of the altered book object are now: ");
		System.out.println(book.toString());
	}
}
