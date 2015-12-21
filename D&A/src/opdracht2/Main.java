package opdracht2;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int[] numbers = { 26, 16, 58, 36, 53, 21, 31, 41 };
		RSHeap heap = new RSHeap(10);
		MultiwayMergeSort mergeSort = new MultiwayMergeSort();
//		heap.setInput(numbers);
//		heap.buildHeap();
//		System.out.println(Arrays.toString(heap.getHeap()));

		
		mergeSort.mergeSort(numbers);
		
		
		System.out.println(Arrays.toString(numbers));

	}
}
