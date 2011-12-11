public class MergeSort
{	
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] array)
	{	
		Comparable[] shadow = new Comparable[array.length];
		
		mergeSort(array, shadow, 0, array.length - 1);
	}
	
	@SuppressWarnings("rawtypes")
	private static void mergeSort(Comparable[] array, Comparable[] copy, int left, int right)
	{
		if (right - left <= 0) return;
		
		int center = (left + right) / 2;
		
		mergeSort(array, copy, left, center);
		mergeSort(array, copy, center + 1, right);
		
		merge(array, copy, left, center + 1, right);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void merge(Comparable[] array, Comparable[] copy, int left, int center, int right)
	{
		int endLeft = center - 1;
		int endRight = right;
		
		int leftIdx = left;
		int rightIdx = center;
		int idx = left;
		
		while (leftIdx <= endLeft && rightIdx <= endRight)
		{
			Comparable leftElement = array[leftIdx];
			Comparable rightElement = array[rightIdx];
			
			if (leftElement.compareTo(rightElement) <= 0)
			{
				copy[idx] = leftElement;
				leftIdx++;
				idx++;
			}
			else
			{
				copy[idx] = rightElement;
				rightIdx++;
				idx++;
			}
		}
		
		while (leftIdx <= endLeft) { copy[idx] = array[leftIdx]; leftIdx++; idx++; }
		while (rightIdx <= endRight) { copy[idx] = array[rightIdx]; rightIdx++; idx++; }
		
		for (int i = left; i <= endRight; i++)
		{
			array[i] = copy[i];
		}
	}
}
