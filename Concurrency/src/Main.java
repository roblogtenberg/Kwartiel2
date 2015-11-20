import java.util.Arrays;
import java.util.Random;

public class Main {

	Random r = new Random();
	long startTime;
	long endTime;
	long timeTaken;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		for (int i = 0; i < 10; i++) {
			int[] num = createArray(400000);
			startTime = System.currentTimeMillis();
			insertionSort(num);
			endTime = System.currentTimeMillis();
			timeTaken = endTime - startTime;
			System.out.println("Time: " + timeTaken + " milliseconds");
		}
	}

	public int[] insertionSort(int[] num) {
		int j; // the number of items sorted so far
		int key; // the item to be inserted
		int i;

		for (j = 1; j < num.length; j++) { // Start with 1 (not 0)
			key = num[j];
			for (i = j - 1; (i >= 0) && (num[i] < key); i--) { // Smaller values are moving up
				num[i + 1] = num[i];
			}
			num[i + 1] = key; // Put the key in its proper location
		}
		return num;
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

}
