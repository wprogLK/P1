/*
 * Urs Gerber, 09-921-156
 * Lukas Keller, 10-113-736
 * 
 * Aufgabe 6-1-4
 */

public class Recursion
{
	public static void main(String[] args)
	{
		recursive(0);
	}

	public static void recursive(int i)
	{
		if (i > 30)
			return;
		System.out.println(i);
		recursive(i+3);
	}
}
