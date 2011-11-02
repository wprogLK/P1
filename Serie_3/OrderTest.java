import java.util.Date;


public class OrderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Book b1 = new Book(1, "Test Title", "Test Author", new Date(), 42);
		Book b2 = new Book(2, "Test Title 2", "Test Author 2", new Date(), 24);
		Book b3 = new Book(3, "Test Title 3", "Test Author 3", new Date(), 19);
		Order order = new Order("Test User", "Test Address");
		order.addBook(b1);
		order.addBook(b2);
		Order order2 = new Order("Test User", "Test Address");
		order2.addBook(b1);
		order2.addBook(b3);
		System.out.println(order.toString());
		System.out.println(order2.toString());

	}

}
