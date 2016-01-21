package opdracht3V;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {

	private static String inputFile = "dickens.txt";
	private static String outputFile = "outputTree.txt";

	public static void main(String[] args) {
		Searcher searcher;

		File file = new File(inputFile);
		if (file.exists()) {
			System.out.println("file gevonden");
		} else {
			System.out.println("file niet gevonden probeer opnieuw");
		}
		PrintStream out = null;
		try {
			File output = new File(outputFile);
			out = new PrintStream(new FileOutputStream(output));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		searcher = new Searcher(file);
		searcher.search();
		searcher.printTabelPosities(out);
		out.println();
		out.println();
		searcher.printTabelVoorkomens(out);

		System.out.println("Done");
		out.close();
	}
}
