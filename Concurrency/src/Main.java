import java.util.Arrays;
import java.util.Random;

public class Main {

	Random r = new Random();
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		int[] num = createArray(25000);
//		System.out.println(Arrays.toString(insertionSort(num)));
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
		boolean ignoreZero = false;
		for(int i = 0; i < number; i++) {
			int input = r.nextInt(number);
			boolean found = false;
			for(int j = 0; j < a.length; j++) {
				if(input == a[j]) {
					if(input == 0 && !ignoreZero) {
						ignoreZero = true;
						break;
					} else {
						found = true;
						break;
					}
				}
			}
			if(found) {
				i--;
			} else {
				a[i] = input;
			}
			
		}
		return a;
	}

}
