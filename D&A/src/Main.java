import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

	long startTime;
	long endTime;
	String tijd;

	Random r = new Random();

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
//		System.out.println(Arrays.toString(Algorithm1(10000)));
//		Algorithm2(6);
		// Algorithm3(1000);
		startTime = System.currentTimeMillis();
		System.err.println(Arrays.toString(Algorithm3(25000)));
		endTime = System.currentTimeMillis();
		long diffInMillis = endTime - startTime;
		tijd = diffInMillis + " milliseconds";
		System.out.println("\n" + tijd);
	}

	public int[] Algorithm1(int number) {
		int[] a = new int[number];
		System.out.println(a.length);
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
		int[] u = new int[number];
		System.out.println(a.length);
		for (int i = 0; i < a.length; i++) {
			u[i] = i;
			// System.out.println(Arrays.toString(u));
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
