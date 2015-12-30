package opdracht2;

public class Heap {
	private int[] heap;
	private int maxsize;
	private int size;

	public Heap(int max) {
		maxsize = max;
		heap = new int[maxsize];
		size = -1;
	}

	private int leftchildPosition(int pos) {
		int leftchildpos = 0;
		if (pos % 2 == 0) {
			leftchildpos = (pos + 1) + (pos);
		} else {
			leftchildpos = 2 * pos + 1;
		}
		return 2 * pos;
	}

	private int leftchildValue(int pos) {
		int leftchildpos = 0;
		if (pos % 2 == 0) {
			leftchildpos = (pos + 1) + (pos);
		} else {
			leftchildpos = 2 * pos + 1;
		}
		return getElement(leftchildpos);
	}

	private int parent(int pos) {
		int returnint = 0;
		if (pos % 2 == 0) {
			returnint = (pos - 1) / 2; // anders is 1 de parent van 2 en de
										// parent van 2 moet 0 zijn
		} else {
			returnint = pos / 2;
		}
		return returnint; // geef de positie van de parent
	}

	private boolean isleaf(int pos) {
		return ((pos > size / 2) && (pos <= size));
	}

	private void swap(int pos1, int pos2) {
		int tmp;

		tmp = heap[pos1];
		heap[pos1] = heap[pos2];
		heap[pos2] = tmp;
	}

	/**
	 * voegt nieuw getal toe aan de heap
	 * 
	 * @param elem
	 *            het getal dat moet worden toegevoegd
	 */
	public void insert(int elem) {
		size++;
		heap[size] = elem;
		int current = size;

		while (heap[current] < heap[parent(current)]) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public int getLast() {
		return heap[size];
	}

	public int size() {
		return size;
	}

	public void moveLastToDeadspace() {
		size--;
	}

	public void setLast(int newLast) {
		heap[size] = newLast;
	}

	public int getMin() {
		return heap[0];
	}

	public int getElement(int index) {
		return heap[index];
	}

	public void setElement(int index, int element) {
		heap[index] = element;
	}

	public void setMin(int newMin) {
		heap[0] = newMin;
	}

	public int[] getHeapArray() {
		return heap;
	}

	public int removemin() {
		swap(1, size);
		size--;
		if (size != 0)
			naarBeneden(1);
		return heap[size + 1];
	}

	/**
	 * wisselt getal van een positie om met een van zijn kinderen
	 * 
	 * @param pos
	 *            getal van deze positie gaat naar beneden
	 */
	public void naarBeneden(int position) {
		int smallestchild;
		while (!isleaf(position)) {
			smallestchild = leftchildPosition(position);
			if ((smallestchild < size) && (heap[smallestchild] > heap[smallestchild + 1]))
				smallestchild = smallestchild + 1; // links + 1 is het rechter
													// kind
			if (heap[position] <= heap[smallestchild])
				return;
			swap(position, smallestchild);
			position = smallestchild;
		}
	}

	public Heap getheap() {
		// je geeft kopie want je wilt niet dat heap van buiten deze class kan worden aangepast
		Heap heapcopy = this;

		return this;
	}

	/**
	 * print de heap af (om te testen)
	 */
	public void printHeap() {// print de heap
		System.out.println("\n-----heap-----");
		for (int i = 0; i < heap.length; i++) {
			System.out.print(heap[i] + " ");
		}
		System.out.println("");
		System.out.println("");
	}

	/**
	 * print de heap e geeft deze terug
	 * 
	 * @return
	 */
	public int[] returnHeap() {
		// printHeap();
		return heap; // geeft de heap terug
	}
}
