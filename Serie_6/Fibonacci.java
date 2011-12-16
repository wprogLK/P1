/*
 * Urs Gerber, 09-921-156
 * Lukas Keller, 10-113-736
 * 
 * Aufgabe 6-1-1, 6-1-3
 */

public class Fibonacci
{
	static final boolean iterative = true;
	
	public static void main(String[] args)
	{
		int n = 50;
		if (iterative)
			System.out.print(fibIterative(n));
		else
			System.out.print(fib(n));
	}
	
	public static long fib(int i)
	{
		if (i <= 1)
			return i;
		
		return fib(i-1) + fib(i-2);
	}
	
	public static long fibIterative(int i)
	{
		long addendA = 0, addendB = 1;
		long tmp = 0;
		
		for (long cnt = 0; cnt < i; cnt++)
		{
			tmp = addendA;
			addendA = addendB;
			addendB = tmp + addendB;
		}
				
		return addendA;
	}
}
