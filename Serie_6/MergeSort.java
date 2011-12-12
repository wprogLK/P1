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
	private static void merge(Comparable[] array, Comparable[] copy, int leftStart, int rightStart, int rightEnd)
	{
		int endLeft = rightStart - 1;
		
		int leftIdx = leftStart;
		int rightIdx = rightStart;
		int idx = leftStart;
		
		while (leftIdx <= endLeft && rightIdx <= rightEnd)
		{
			Comparable leftElement = array[leftIdx];
			Comparable rightElement = array[rightIdx];
			
			if (leftElement.compareTo(rightElement) <= 0)
			{
				copy[idx++] = leftElement;
				leftIdx++;
			}
			else
			{
				copy[idx++] = rightElement;
				rightIdx++;
			}
		}
		
		while (leftIdx <= endLeft) copy[idx++] = array[leftIdx++];
		while (rightIdx <= rightEnd) copy[idx++] = array[rightIdx++];
		
		for (int i = leftStart; i <= rightEnd; i++)
		{
			array[i] = copy[i];
		}
	}
}
