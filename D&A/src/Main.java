public class Main {

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		Algorithm1(6);
		Algorithm2(6);
		Algorithm3(6);
	}

	public void Algorithm1(int number) {
		int[] a = new int[number];
		System.out.println(a.length);
		for(int i = 0; i < number; i++) {
			a[i] = number;
		}
	}

	public void Algorithm2(int number) {

	}

	public void Algorithm3(int number) {

	}
}
