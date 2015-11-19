import java.util.Random;
import java.util.Arrays;

public class Main {

	Random r = new Random();
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		System.out.println(Arrays.toString(Algorithm1(10000)));
		Algorithm2(6);
		Algorithm3(6);
	}

	public int[] Algorithm1(int number) {
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

	public void Algorithm2(int number) {

	}

	public void Algorithm3(int number) {

	}
}
