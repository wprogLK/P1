public class Fibonacci
{
	public static void main(String[] args)
	{
		System.out.print(fibIterative(2));
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
		long prev = 0;
		
		for (long cnt = 0; cnt < i; cnt++)
		{
			prev = addendA;
			addendA = addendB;
			addendB = prev + addendB;
		}
				
		return addendA;
	}
}
