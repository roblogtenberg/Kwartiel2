package opdracht2;

import java.util.Arrays;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		int[] numbers = {26,16,58,36,53,21,31,41};
		RSHeap heap = new RSHeap(10);
		heap.setInput(numbers);
		heap.buildHeap();
		System.out.println(Arrays.toString(heap.getHeap()));
	}
}
