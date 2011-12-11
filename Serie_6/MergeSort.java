public class MergeSort
{

	public static void sort(Comparable[] array)
	{
		if (array == null)
			return;
		if (array.length < 2)
			return;
		
		mergeSort(array,0, array.length - 1);
	}
	
	private static void mergeSort(Comparable[] array, int left, int right)
	{
		if (right - left <= 0) return;
		
		int center = (left + right) / 2;
		
		mergeSort(array, left, center);
		mergeSort(array, center + 1, right);
		
		merge(array, left, center + 1, right);
	}
	
	private static void merge(Comparable[] array, int left, int center, int right)
	{
		// blabla
	}
}
