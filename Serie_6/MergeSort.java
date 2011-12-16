/*
 * Urs Gerber, 09-921-156
 * Lukas Keller, 10-113-736
 * 
 * Aufgabe 6-2
 */

public class MergeSort
{	
	public static void sort(Comparable[] array)
	{	
		Comparable[] shadow = new Comparable[array.length];
		
		mergeSort(array, shadow, 0, array.length - 1);
	}
	
	private static void mergeSort(Comparable[] array, Comparable[] shadow, int leftStart, int rightStart)
	{
		if (rightStart - leftStart <= 0) return;
		
		int center = (leftStart + rightStart) / 2;
		
		// Sort left subarray
		mergeSort(array, shadow, leftStart, center);
		// Sort right subarray
		mergeSort(array, shadow, center + 1, rightStart);
		// Merge both arrays
		merge(array, shadow, leftStart, center + 1, rightStart);
	}
	
	private static void merge(Comparable[] array, Comparable[] shadow, int leftStart, int rightStart, int rightEnd)
	{
		int endLeft = rightStart - 1;
		
		int leftIdx = leftStart;
		int rightIdx = rightStart;
		int idx = leftStart;
		
		// Merge both sorted subarrays
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
		
		// Merge all leftovers
		while (leftIdx <= endLeft)
			shadow[idx++] = array[leftIdx++];
		while (rightIdx <= rightEnd)
			shadow[idx++] = array[rightIdx++];
		
		// Copy sorted values back to original array
		for (int i = leftStart; i <= rightEnd; i++)
			array[i] = shadow[i];
	}
}
