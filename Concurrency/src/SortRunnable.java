

public class SortRunnable implements Runnable{

	private int[] array;
	private int[] returnValue;
	private int threshold = 1250;
	
	public SortRunnable(int[] array) {
		this.array = array;
	}
	
	@Override
	public void run() {
		if(array.length > threshold) {
			int[] part1 = new int[(array.length / 2)];
			int[] part2 = new int[(array.length / 2)];
			System.arraycopy(array, 0, part1, 0, part1.length);
			System.arraycopy(array, part1.length, part2, 0, part2.length);
			
			SortRunnable sr1 = new SortRunnable(part1);
			SortRunnable sr2 = new SortRunnable(part2);
			Thread t1 = new Thread(sr1);
			Thread t2 = new Thread(sr2);
			t1.start();
			t2.start();
			try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			returnValue = mergeArrays(sr1.getOutput(), sr2.getOutput());
		} else {
			int j; // the number of items sorted so far
			int key; // the item to be inserted
			int i;

			for (j = 1; j < array.length; j++) { // Start with 1 (not 0)
				key = array[j];
				for (i = j - 1; (i >= 0) && (array[i] < key); i--) { // Smaller values
																	// are moving up
					array[i + 1] = array[i];
				}
				array[i + 1] = key; // Put the key in its proper location
			}
			returnValue = array;
		}
	}
	
	public int[] getOutput() {
		return returnValue;
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
}
