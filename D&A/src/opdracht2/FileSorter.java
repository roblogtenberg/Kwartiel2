package opdracht2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileSorter {
	RSHeap<Integer> heap;
	Scanner reader;
	Scanner reader2;
	PrintWriter writer;
	int runs = 0;
	int elementenInRun = 0;
	int totElementen = 0;

	public FileSorter(File file, int heapSize) throws FileNotFoundException {

		// heapsize + 1 omdat op de eerste positie het laatst verwijderde
		// element word bijgehouden en eigenlijk geen onderdeel is van de
		// heap/deadspace
		heap = new RSHeap<>(heapSize + 1);

		// reader voor input file
		reader = new Scanner(file);
	}

	public String Sort() {
		File resultFile = new File("runs.txt");
		try {
			// nieuwe writer naar file
			writer = new PrintWriter(resultFile);

			// heap vullen met data
			fillHeapFromReader();

			while (reader.hasNextInt() || !heap.isEmpty()) {
				doRun();
				runs++;

				// als de lijst niet leeg is is het sowieso een complete run kan
				// voorkomen dat het wel een complete run is maar daar gokken we
				// op
				if (!heap.isEmpty()) {
					totElementen += elementenInRun;
					elementenInRun = 0;
				}

				writer.print(";" + "\n");
				heap.buildMinHeap();
				writer.flush();
			}
			writer.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("file not found");
			e.printStackTrace();
		}
		return resultFile.getAbsolutePath();
	}

	public void doRun() {
		Integer i;
		// zolang er nog elementen in de heap zitten
		while ((i = (Integer) heap.deleteNext()) != null) {
			// element uitprinten
			System.out.print(i + ".");
			writer.print(" " + i);
			elementenInRun++;

			// als er nog elementen in file staan
			if (reader.hasNextInt()) {
				try {
					// element inserten.
					heap.insert(reader.nextInt());
				} catch (Exception e) {
					System.err.println("Array zit vol toch geprobeert toe te voegen");
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * fills de heap with elements
	 */
	public void fillHeapFromReader() {
		while (!heap.isFull()) {
			try {
				if (reader.hasNextInt()) {
					// eerst in deadspace
					heap.insertInDeadSpace((Integer) reader.nextInt());
				} else {
					break;
				}
			} catch (Exception e) {
				System.err.println("probeert toch teveel element erin te stoppen");
				e.printStackTrace();
			}
		}
		// dan heap van maken
		heap.buildMinHeap();
	}

	public int getGemElementen() {
		if (runs > 1) {
			return totElementen / (runs - 1);
		} else {
			return -1;
		}

	}

	public int getRuns() {
		return runs;
	}
}
