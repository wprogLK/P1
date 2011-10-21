/*
 Programmierung 1 HS 2011
 Aufgabe 2-1
 */

import java.text.DecimalFormat;
import java.util.Random;

public class RandomISBN {

	public static void main(String args[]) {
		// TO DO
		System.out.println(makeISBN());
	}

	/** generates and returns a random ISBN number in the format XX-XXX-XX-C */
	public static String makeISBN() {

		String laendercode;
		String bandnr;
		String verlagsnr;
		String checksum;

		Random random = new Random();

		int int_laendercode = random.nextInt(100);

		DecimalFormat formatter = new DecimalFormat("00");
		laendercode = formatter.format(int_laendercode);

		Integer int_bandnr = 100 + random.nextInt(900);
		bandnr = int_bandnr.toString();

		int int_verlagsnr = random.nextInt(100);
		verlagsnr = formatter.format(int_verlagsnr);

		int l1 = int_laendercode / 10;
		int l2 = int_laendercode % 10;

		int b1 = int_bandnr / 100;
		int b2 = (int_bandnr % 100) / 10;
		int b3 = int_bandnr % 10;

		int v1 = int_verlagsnr / 10;
		int v2 = int_verlagsnr % 10;

		Integer int_checksum = (hashOp(l1) + l2 + hashOp(b1) + b2 + hashOp(b3)
				+ v1 + hashOp(v2)) % 10;
		checksum = int_checksum.toString();

		return laendercode + "-" + bandnr + "-" + verlagsnr + "-" + checksum;
	}

	/** multiplies i with 2 and subtracts 9 if result is >= 10 */
	public static int hashOp(int i) {
		int doubled = 2 * i;
		if (doubled >= 10) {
			doubled = doubled - 9;
		}
		return doubled;
	}
}
