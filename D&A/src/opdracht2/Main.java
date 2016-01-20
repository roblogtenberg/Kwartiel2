package opdracht2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static final String TESTFILEPATH = "testGen.txt";
	public static final String DESCENDING = "deCen.txt";
	public static final String ASCENDING = "asCen.txt";
	public static final String RANDOM100 = "Random100.txt";
	public static final String RANDOM20 = "Random20.txt";

	public static void main(String[] args) {

		try {
			System.out.println("heap20Array50():");
			heap20Array50();
			System.out.println("\nheap20Array50Descending():");
			heap20Array50Descending();
			System.out.println("\nheap20Array100Random():");
			heap20Array100Random();
			System.out.println("\nheap50Array20Random():");
			heap50Array20Random();
		} catch (Exception e) {
		}
	}

	public static File generateTestFile(String path, int amountItems) {
		File f = new File(path);
		try {
			PrintWriter pw = new PrintWriter(f);
			for (int i = 0; i < amountItems; i++) {
				pw.print((int) (Math.random() * amountItems * 2) + " ");
			}
			pw.flush();
			pw.close();
			return f;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void heap20Array50() throws IOException {
		File file = null;

		file = new File(ASCENDING);
		FileSorter sorter;
		try {
			sorter = new FileSorter(file, 20);
			sorter.Sort();
			System.out.println("\ngemmidelde aantal elementen in een complete run: " + sorter.getGemElementen());
			System.out.println("aantal runs: " + sorter.getRuns());
		} catch (FileNotFoundException e) {
			System.err.println("het opgegeven bestand bestaat niet");
			e.printStackTrace();
		}
	}

	public static void heap20Array50Descending() throws IOException {
		File file = null;

		file = new File(DESCENDING);
		FileSorter sorter;
		try {
			sorter = new FileSorter(file, 20);
			sorter.Sort();
			System.out.println("\ngemmidelde aantal elementen in een complete run: " + sorter.getGemElementen());
			System.out.println("aantal runs: " + sorter.getRuns());
		} catch (FileNotFoundException e) {
			System.err.println("het opgegeven bestand bestaat niet");
			e.printStackTrace();
		}

	}

	public static void heap20Array100Random() throws IOException {
		File file = null;

		file = generateTestFile(RANDOM100, 100);

		FileSorter sorter;
		try {
			sorter = new FileSorter(file, 20);
			sorter.Sort();
			System.out.println("\ngemmidelde aantal elementen in een complete run: " + sorter.getGemElementen());
			System.out.println("aantal runs: " + sorter.getRuns());
		} catch (FileNotFoundException e) {
			System.err.println("het opgegeven bestand bestaat niet");
			e.printStackTrace();
		}
	}

	public static void heap50Array20Random() throws IOException {
		File file = null;

		file = generateTestFile(RANDOM20, 20);

		FileSorter sorter;
		try {
			sorter = new FileSorter(file, 50);
			sorter.Sort();
			System.out.println("\ngemmidelde aantal elementen in een complete run: " + sorter.getGemElementen());
			System.out.println("aantal runs: " + sorter.getRuns());
		} catch (FileNotFoundException e) {
			System.err.println("het opgegeven bestand bestaat niet");
			e.printStackTrace();
		}
	}

}
