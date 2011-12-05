/* Programmierung 1 HS 2011 Aufgabe 5-1 */

import java.util.*;

public class FileTest{
	public static void main(String[] args) throws java.io.IOException{
		// Create a list of Address-objects
		ArrayList<Address> list = new ArrayList<Address>();
		list.add(new Address(1, "Max Muster", "Neubrückstr. 10", 3012, "Bern"));
		list.add(new Address(1, "Sophie Meier", "Bahnhofstrasse 2", 3400, "Burgdorf"));
		list.add(new Address(1, "Anna Kunz", "Mittelstrasse 4", 3012, "Bern"));
		AddressFile file;
		

		/* ------------ Testing comma-separated files ------------ */
		// save addresses to a comma-separated file
		file = new AddressFile("adr_out.csv");
		file.save(list);
		System.out.println("\nSaved comma-separated file adr_out.csv.\n");
		
		// load addresses from a comma-separated file
		System.out.println("Reading file adresses.csv...");
		file = new AddressFile("addresses.csv");
		ArrayList<Address> result;
		try{
			result = file.load();
			System.out.println("Loaded the following objects:");
			for(Address adr : result) System.out.println(adr);
		}catch(AddressFileException e){  System.out.println("\nInvalid file.\n");  }
		


		
		// ------------ Testing labelled files ------------
		// save addresses to a labelled file
		file = new AddressFileLabelled("adr_out.txt");  // note the usage of polymorphism!
		file.save(list);
		System.out.println("\nSaved labelled file adr_out.txt.\n");
		
		// load addresses from a labelled file
		System.out.println("Reading file addresses.txt...");
		file = new AddressFileLabelled("addresses.txt");
		try{
			result = file.load();
			System.out.println("Loaded the following objects:");
			for(Address adr : result)	System.out.println(adr);
			System.out.println("");
		}catch(AddressFileException e){  System.out.println("\nInvalid file.\n");  }
	}
}
