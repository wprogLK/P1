/*
 Programmierung 1 HS 2011
 Aufgabe 2-1
 
Authors: 	
	Lukas Keller (10-113-736)
	Urs Gerber (09-921-156)
 */

import java.text.DecimalFormat;
import java.util.Random;

public class RandomISBN
{
	private static final int OUTPUT_COUNT = 3;
	
	public static void main(String args[])
	{
		for (int i = 0; i < OUTPUT_COUNT; i++)
			System.out.println(String.format("%d. ISBN: %s", i + 1, RandomISBN.makeISBN()));
	}

	/** generates and returns a random ISBN number in the format XX-XXX-XX-C */
	public static String makeISBN()
	{
		String countryCode;
		String issueNumber;
		String publisherCode;
		String checksum;

		// Create random numbers according to specification
		// and format
		Random random = new Random();

		int rndCountryCode = random.nextInt(100);

		DecimalFormat formatter = new DecimalFormat("00");
		countryCode = formatter.format(rndCountryCode);

		Integer rndIssueNumber = 100 + random.nextInt(900);
		issueNumber = rndIssueNumber.toString();

		int rndPublisherCode = random.nextInt(100);
		publisherCode = formatter.format(rndPublisherCode);

		// Extract digits (hopefully faster than string index operation)
		int l1 = rndCountryCode / 10;
		int l2 = rndCountryCode % 10;

		int b1 = rndIssueNumber / 100;
		int b2 = (rndIssueNumber % 100) / 10;
		int b3 = rndIssueNumber % 10;

		int v1 = rndPublisherCode / 10;
		int v2 = rndPublisherCode % 10;

		// Compute checksum
		Integer int_checksum = (hashOp(l1) + l2 + hashOp(b1) + b2 + hashOp(b3) + v1 + hashOp(v2)) % 10;
		checksum = int_checksum.toString();
		
		// Return concatenated ISBN
		return countryCode + "-" + issueNumber + "-" + publisherCode + "-" + checksum;
	}

	/** multiplies i with 2 and subtracts 9 if result is >= 10 */
	public static int hashOp(int i)
	{
		int doubled = 2 * i;
		if (doubled >= 10)
		{
			doubled = doubled - 9;
		}
		return doubled;
	}
}
