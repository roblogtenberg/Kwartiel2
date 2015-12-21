package opdracht2;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiwayMergeSort {

	private int[] toSort;
	private int[] temp;

	public MultiwayMergeSort() {
		RSHeap rsHeap = new RSHeap(10);
		toSort = rsHeap.getHeap();
	}

	public int[] mergeSort(int[] array) {
		int M = 6;
		int N = (array.length / M) + 1;

		int[] part1;
		int[] part2;
		int[] temp;

		part1 = Arrays.copyOfRange(array, 0, N);
		part2 = Arrays.copyOfRange(array, N, (N + M));

		System.out.println(Arrays.toString(part1) + "  "
				+ Arrays.toString(part2));

		Arrays.sort(part1);
		Arrays.sort(part2);

		System.out.println(Arrays.toString(part1) + "  "
				+ Arrays.toString(part2));

		int[] merge = mergeArrays(part1, part2);

		return merge;
	}

	public int[] mergeArrays(int[] a, int[] b) {
		int[] answer = new int[a.length + b.length];
		int i = 0, j = 0, k = 0;
		while (i < a.length && j < b.length) {
			if (a[i] > b[j]) {
				answer[k] = a[i];
				i++;
			} else {
				answer[k] = b[j];
				j++;
			}
			k++;
		}

		while (i < a.length) {
			answer[k] = a[i];
			i++;
			k++;
		}

		while (j < b.length) {
			answer[k] = b[j];
			j++;
			k++;
		}

		return answer;
	}

}
