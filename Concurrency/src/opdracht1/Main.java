package opdracht1;
import java.util.Arrays;
import java.util.Random;

public class Main {

	Random r = new Random();
	long startTime;
	long endTime;
	long timeTaken;

	int[] first = null;
	int[] second = null;

	private static Main instance;

	int timesStarted = 0;

	public static void main(String[] args) {
		instance = new Main();
	}

	public Main() {
		start();
	}

	public void start() {
		// if (timesStarted < 10) {
		// int[] num = createArray(200000);
		//
		// int[] part1 = new int[100000];
		// int[] part2 = new int[100000];
		//
		// System.arraycopy(num, 0, part1, 0, part1.length);
		// System.arraycopy(num, part1.length, part2, 0, part2.length);
		//
		// startTime = System.currentTimeMillis();
		// SortThread thread1 = new SortThread(part1);
		// SortThread thread2 = new SortThread(part2);
		// thread1.start();
		// thread2.start();
		// }
		// timesStarted++;
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

	public void sorted(int[] array) {
		if (first == null) {
			first = array;
		} else {
			second = array;
			Arrays.toString(mergeArrays(first, second));
			endTime = System.currentTimeMillis();
			timeTaken = endTime - startTime;
			System.out.println("Time: " + timeTaken + " milliseconds");
			first = null;
			second = null;
			start();
		}
	}

	public static Main getInstance() {
		return instance;
	}

}
