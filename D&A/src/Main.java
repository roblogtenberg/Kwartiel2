import java.util.Random;

public class Main {

	long startTime;
	long endTime;
	long timeTaken;

	Random r = new Random();

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		// System.out.println(Arrays.toString(Algorithm1(10000)));
		// Algorithm2(6);
		// Algorithm3(1000);
		for (int i = 0; i < 10; i++) {
			startTime = System.currentTimeMillis();
			Algorithm1(100000);
			endTime = System.currentTimeMillis();
			timeTaken = endTime - startTime;
			System.out.println(timeTaken + " milliseconds");
		}
	}

	public int[] Algorithm1(int number) {
		int[] a = new int[number];
		boolean ignoreZero = false;
		for (int i = 0; i < number; i++) {
			int input = r.nextInt(number);
			boolean found = false;
			for (int j = 0; j < a.length; j++) {
				if (input == a[j]) {
					if (input == 0 && !ignoreZero) {
						ignoreZero = true;
						break;
					} else {
						found = true;
						break;
					}
				}
			}
			if (found) {
				i--;
			} else {
				a[i] = input;
			}

		}
		return a;
	}

	public void Algorithm2(int number) {
		int[] a = new int[number];
		boolean[] used = new boolean[number];
		for (int i = 0; i < a.length; i++) {
			int input = r.nextInt(number);
			if (used[input] == true) {
				i--;
			} else {
				used[input] = true;
				a[i] = input;
			}
		}
	}

	public int[] Algorithm3(int number) {
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
