/*
 * Urs Gerber, 09-921-156
 * Lukas Keller, 10-113-736
 * 
 * Aufgabe 6-1-2
 */

public class Factorial
{
	public static void main(String[] args)
	{
		int fac = Integer.parseInt(args[0]);
		
		System.out.println(factorial(fac));
	}
	
	public static long factorial(int n)
	{
		if (n <= 1)
			return 1;
		
		return n * factorial(n-1);
	}
}
