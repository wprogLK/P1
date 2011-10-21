/*
 Programmierung 1 HS 2011
 Aufgabe 2-1
*/

import java.text.DecimalFormat;
import java.util.Random;

public class RandomISBN{

	public static void main(String args[]){
		// TO DO
	}

	/** generates and returns a random ISBN number in the format XX-XXX-XX-C */
	public static String makeISBN(){

		String laendercode;
		String bandnr;
		String verlagsnr;
		String checksum;

		// TO DO

		return laendercode+"-"+bandnr+"-"+verlagsnr+"-"+checksum;
	}

	/** multiplies i with 2 and subtracts 9 if result is >= 10 */
	public static int hashOp(int i){
		int doubled = 2 * i;
		if (doubled >= 10){
			doubled = doubled - 9;
		}
		return doubled;
	}
}
