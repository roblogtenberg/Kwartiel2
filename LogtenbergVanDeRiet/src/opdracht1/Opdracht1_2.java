package opdracht1;

import java.util.Arrays;
import java.util.Random;

public class Opdracht1_2 {

	private Random r = new Random();
	private long startTime;
	private long endTime;
	private long timeTaken;
	private int timesStarted = 0;

	private int[] first = null;
	private int[] second = null;

	private static Opdracht1_2 instance;

	public static void main(String[] args) {
		instance = new Opdracht1_2();
	}

	public Opdracht1_2() {
		start();
	}

	private void start() {
		if (timesStarted < 10) {
			int[] num = createArray(25000);

			int[] part1 = new int[12500];
			int[] part2 = new int[12500];

			System.arraycopy(num, 0, part1, 0, part1.length);
			System.arraycopy(num, part1.length, part2, 0, part2.length);

			startTime = System.currentTimeMillis();
			SortThread thread1 = new SortThread(part1);
			SortThread thread2 = new SortThread(part2);
			thread1.start();
			thread2.start();
		}
		timesStarted++;

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

	public static Opdracht1_2 getInstance() {
		return instance;
	}

}
