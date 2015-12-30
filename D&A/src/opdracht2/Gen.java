package opdracht2;

import java.util.Random;

public class Gen {
	/**
	 * wisselt getallen in de array
	 */
	public int[] generate(int N) {
		int[] array = new int[N];
		Random random = new Random();

		// gesorteerde lijst maken
		// 1 keer doorlopen met N aantallen
		for (int i = 0; i < N; i++) {
			array[i] = i;
			// System.out.println(array[i]);
		}

		// waardes van huidige positie met een van i een N keer antal wisselen
		// 1 keer doorlopen met N aantallen
		for (int i = 0; i < N; i++) {
			int swap = i + random.nextInt(N - i);
			int temp = array[i];
			array[i] = array[swap];
			array[swap] = temp;
		}
		// totaal wordt 2 keer N doorlopen
		return array;
	}
}
