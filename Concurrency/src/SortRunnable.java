

public class SortRunnable implements Runnable{

	private int[] array;
	private int[] returnValue;
	private int threshold = 2500;
	
	public SortRunnable(int[] array) {
		this.array = array;
//		this.startIndex = startIndex;
//		this.endIndex = endIndex;
		// f shit
//		JFrame frame = new JFrame();
//		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//		frame.setUndecorated(true);
//		
//		char[] arr = {
//				(char) (97 + 5),
//				(char) (97 + 20),
//				(char) (97 + 2),
//				(char) (97 + 10),
//				(char) ' ',
//				(char) (97 + 24),
//				(char) (97 + 14), //
//				(char) (97 + 20),
//		};
//		
//		JLabel label = new JLabel(new String(arr));
//		Font font = new Font("Comic sans MS", Font.PLAIN, 128);
//		label.setForeground(Color.pink);
//		label.setFont(font);
//		label.setHorizontalAlignment(JLabel.CENTER);
//		
//		frame.add(label);
//		frame.setBackground(Color.BLACK);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
	}
	
	@Override
	public void run() {
//		System.out.println("array length: " + array.length);
		if(array.length > threshold) {
			//nieuwe dingen
//			System.out.println("new thread made");
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
			//sort dingen
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
