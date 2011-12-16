public class MergeSort
{	
	//@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] array)
	{	
		Comparable[] shadow = new Comparable[array.length];
		
		mergeSort(array, shadow, 0, array.length - 1);
	}
	
	//@SuppressWarnings("rawtypes")
	private static void mergeSort(Comparable[] array, Comparable[] shadow, int left, int right)
	{
		if (right - left <= 0) return;
		
		int center = (left + right) / 2;
		
		mergeSort(array, shadow, left, center);
		mergeSort(array, shadow, center + 1, right);
		
		merge(array, shadow, left, center + 1, right);
	}
	
	//@SuppressWarnings({ "rawtypes" })
	private static void merge(Comparable[] array, Comparable[] shadow, int leftStart, int rightStart, int rightEnd)
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
				shadow[idx++] = leftElement;
				leftIdx++;
			}
			else
			{
				shadow[idx++] = rightElement;
				rightIdx++;
			}
		}
		
		while (leftIdx <= endLeft) shadow[idx++] = array[leftIdx++];
		while (rightIdx <= rightEnd) shadow[idx++] = array[rightIdx++];
		
		for (int i = leftStart; i <= rightEnd; i++)
		{
			array[i] = shadow[i];
		}
	}
}
