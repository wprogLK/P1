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
	
	/*
	 * In recursive functions, terminating conditions are equivalent to conditions in loops.
	 * Increment statements in loops are replaced by an alteration of the recursion parameters
	 * which are then tested for termination condition
	 */
}
