import java.text.ParseException;


public class BookTest
{

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException
	{
		Book book = new Book();
		book.input();
		System.out.println("Book's age: " + book.age() + " days.");
		System.out.println(book.toString());
	}

}
