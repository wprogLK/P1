import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressFile 
{
	private String filename;

	public AddressFile(String filename)
	{
		this.filename = filename;
	}

	public String toLine(Address addr) 
	{
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%d, %s, %s, %d, %s", addr.getId(),
				addr.getName(), addr.getStreet(), addr.getZipCode(),
				addr.getCity()));

		return sb.toString();
	}

	public Address parseLine(String line) throws AddressFileException
	{
		Scanner scanner = new Scanner(line);
		scanner.useDelimiter(",");
		
		try
		{
			int id = Integer.parseInt(scanner.next().trim());
			String name = scanner.next().trim();
			String street = scanner.next().trim();
			int zipCode = Integer.parseInt(scanner.next().trim());
			String city = scanner.next().trim();
			
			Address addr = new Address(id, name, street, zipCode, city);
			
			return addr;
		}
		catch(Exception e)
		{
			throw new AddressFileException(e.getMessage());
		}
	}
	
	public void save(ArrayList<Address> addresses) throws IOException
	{
		FileWriter fileWriter = new FileWriter(this.filename);
		BufferedWriter bufferdWriter = new BufferedWriter(fileWriter);
		PrintWriter printWriter = new PrintWriter(bufferdWriter);
		
		for(Address addr : addresses)
		{
			printWriter.println(this.toLine(addr));
		}
		
		printWriter.close();
	}
	
	public ArrayList<Address> load() throws AddressFileException, IOException 
	{
		FileReader fileReader = new FileReader(this.filename);
		BufferedReader bufferdReader = new BufferedReader(fileReader);
		ArrayList<Address> addresses = new ArrayList<Address>();

		String line = null;
		
		do
		{
			line = bufferdReader.readLine();
			if (line != null)
				addresses.add(this.parseLine(line));
		}
		while(line != null);
		
		return addresses;
	}
}
