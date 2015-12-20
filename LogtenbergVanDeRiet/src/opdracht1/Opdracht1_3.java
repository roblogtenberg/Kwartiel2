package opdracht1;

import java.util.Random;

public class Opdracht1_3 {

	private Random r = new Random();
	private long startTime;
	private long endTime;
	private long timeTaken;

	public static void main(String[] args) {
		new Opdracht1_3();
	}

	public Opdracht1_3() {
		for (int i = 0; i < 10; i++) {
			int[] num = createArray(8000000);
			SortRunnable sr = new SortRunnable(num);
			Thread t = new Thread(sr);
			startTime = System.currentTimeMillis();
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			endTime = System.currentTimeMillis();
			timeTaken = endTime - startTime;
			System.out.println("Time: " + timeTaken + "milliseconds");
		}
	}

	public int[] createArray(int number) {
		int[] a = new int[number];
		int tempNumber;
		int random;
		for (int i = 0; i < number; i++) {
			random = r.nextInt(i + 1);
			a[i] = i;
			tempNumber = a[i];
			a[i] = a[random];
			a[random] = tempNumber;

		}
		return a;
	}

	public int[] insertionSort(int[] num) {
		int j; // the number of items sorted so far
		int key; // the item to be inserted
		int i;

		for (j = 1; j < num.length; j++) { // Start with 1 (not 0)
			key = num[j];
			for (i = j - 1; (i >= 0) && (num[i] < key); i--) {
				num[i + 1] = num[i];
			}
			num[i + 1] = key; // Put the key in its proper location
		}
		return num;
	}

}
