package opdracht2;

import java.util.Random;

public class Main {

	private Random r = new Random();

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		// TODO Auto-generated constructor stub
		String iets = "Hoi";

		Integer[] heap = { 4, 9, 3, 6456, 34, 36, 22, 55, 7772, 7456, 5, 3, 3 };

		SimpleTree<Integer, String> tree = new SimpleTree<>();

		for (int i = 0; i < heap.length; i++) {
			tree.put(heap[i], iets);
		}

		tree.displayTree();
	}
}
