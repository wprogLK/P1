import java.util.Scanner;

public class AddressFileLabelled extends AddressFile
{

	public AddressFileLabelled(String filename) 
	{
		super(filename);
	}
	
	@Override
	public Address parseLine(String line) throws AddressFileException
	{
		try
		{
			int id = Integer.parseInt(search(line, "id"));
			String name = search(line, "name");
			String street = search(line, "street");
			int zipCode = Integer.parseInt(search(line, "zip"));
			String city = search(line, "city");
			
			return new Address(id, name, street, zipCode, city);
		}
		catch(NumberFormatException e)
		{
			throw new AddressFileException(e.getMessage());
		}
		catch(IllegalStateException e)
		{
			throw new AddressFileException(e.getMessage());
		}
	}
	
	@Override
	public String toLine(Address addr) 
	{
		return String.format("id: %d; name: %s; street: %s; zip: %d; city: %s;", addr.getId(),
				addr.getName(), addr.getStreet(), addr.getZipCode(),
				addr.getCity());
	}
	
	private String search(String line, String label) throws IllegalStateException
	{
		Scanner scanner = new Scanner(line);
		scanner.findInLine(label + "[\\s]*:[\\s]*([^;]*)");
		return scanner.match().group(1).trim();
	}
}
