package opdracht2;

public class RSHeap {

	private int[] heap;
	private int[] temp;
	private int heapSize;

	public RSHeap(int heapSize) {
		this.heapSize = heapSize;
		heap = new int[heapSize];
	}

	public void buildHeap() {
		for (int j = 0; j < heap.length; j++) {
			for (int i = heap.length - 1; i >= 0; i--) {
				int current = i;
				int parent = (int) Math.floor(current / 2);
				while (heap[current] > heap[parent]) {
					int temp = heap[current];
					heap[current] = heap[parent];
					heap[parent] = temp;
					current = parent;
					parent = (int) Math.floor(current / 2);
				}

			}
		}
	}

	public int[] getHeap() {
		return heap;
	}

	public void setInput(int[] input) {
		heap = input;
	}

	public void percolateDown() {

	}

	public void percolateUp() {

	}
}
