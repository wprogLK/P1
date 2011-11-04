public class Order {
	private int id;
	private String customerName;
	private String customerAddress;
	private Book bookA;
	private Book bookB;
	private Book bookC;
	private Book bookD;
	private Book bookE;

	private int bookCount;
	private int totalPrice;

	private static int idCounter = 1;
	private static final int MAX_BOOK_COUNT = 5;

	public Order() {
		this.id = idCounter++;
		this.customerName = "";
		this.customerAddress = "";
		this.bookCount = 0;
		this.bookA = null;
		this.bookB = null;
		this.bookC = null;
		this.bookD = null;
		this.bookE = null;
		this.totalPrice = 0;
	}

	public Order(String customerName, String customerAddress) {
		this.id = idCounter++;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.bookCount = 0;
		this.bookA = null;
		this.bookB = null;
		this.bookC = null;
		this.bookD = null;
		this.bookE = null;
		this.totalPrice = 0;
	}

	public void addBook(Book book) {

		switch (this.bookCount) {
		case 0:
			this.bookA = book;
			break;
		case 1:
			this.bookB = book;
			break;
		case 2:
			this.bookC = book;
			break;
		case 3:
			this.bookD = book;
			break;
		case 4:
			this.bookE = book;
			break;
		default:
			break;
		}

		if (this.bookCount < 5) {
			this.bookCount++;
			this.totalPrice += book.getPrice();
		}
	}

	public int getTotalPrice() {
		return this.totalPrice;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("Order id: %d, Customer: %s, %s\n", this.id,
				this.customerName, this.customerAddress));
		if (this.bookCount == 0)
			sb.append("No Books in Order!");
		else {
			if (this.bookCount > 0)
				sb.append(bookA.toString());
			if (this.bookCount > 1)
				sb.append(bookB.toString());
			if (this.bookCount > 2)
				sb.append(bookC.toString());
			if (this.bookCount > 3)
				sb.append(bookD.toString());
			if (this.bookCount > 4)
				sb.append(bookE.toString());
		}

		sb.append(String.format("Total price: %d CHF\n", this.getTotalPrice()));

		return sb.toString();
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustomerAddress(String address) {
		this.customerAddress = address;
	}
}
