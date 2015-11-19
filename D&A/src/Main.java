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
		Algorithm1(6);
		Algorithm2(6);
		// Algorithm3(1000);
		startTime = System.currentTimeMillis();
		System.err.println(Arrays.toString(Algorithm3(10)));
		endTime = System.currentTimeMillis();
		long diffInMillis = endTime - startTime;
		tijd = diffInMillis + " milliseconds";
		System.out.println("\n" + tijd);
	}

	public void Algorithm1(int number) {
		int[] a = new int[number];
		System.out.println(a.length);
		for (int i = 0; i < number; i++) {
			a[i] = number;
		}
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
