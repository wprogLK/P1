/*
 Programmierung 1 HS 2011
 Aufgabe 5-1
*/

public class Address{
	private int id;
	private String name, street, city;
	private int zipCode;

	/** constructor */
	public Address(int id, String name, String street, int zipCode, String city){
		this.id = id;
		this.name = name;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
	}

	public int getId(){ return id; }
	public String getName(){ return name; }
	public String getStreet(){ return street; }
	public int getZipCode(){ return zipCode; }
	public String getCity(){ return city; }

	public String toString(){
		return id+", "+name+", "+street+", "+zipCode+" "+city;
	}
}
