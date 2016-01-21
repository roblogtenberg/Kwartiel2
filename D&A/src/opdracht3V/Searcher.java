package opdracht3V;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Searcher {
	Scanner in;
	Trie<ArrayList<Integer>> woordposities;

	public Searcher(File f) {
		try {
			in = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		woordposities = new Trie<>();
	}

	public void search() {
		int pos = 0;
		String read = "";
		while (in.hasNext()) {

			// als er een woord is inlezen en alle niet letters weghalen
			read = in.next().toLowerCase().replaceAll("[^a-zA-Z] ", "");

			// als er zich nog geen arraylist bestaat op deze plek er een
			// instoppen
			if (woordposities.search(read) == null)
				woordposities.insert(read, new ArrayList<Integer>());

			// en dan daar een positie aan toevoegen
			woordposities.search(read).add(pos);
			pos++;
		}
	}

	public void printTabelPosities(PrintStream out) {
		out.println("woord -------------------- posities");

		// alle woorden ophalen
		HashMap<String, ArrayList<Integer>> results = woordposities.getData();

		// keys ophalen en sorteren
		ArrayList<String> keys = new ArrayList<>(results.keySet());
		Collections.sort(keys);

		// waardes in volgorde uitprinten
		for (String key : keys) {
			out.println(String.format("%1$-" + 16 + "s", key) + " | " + results.get(key));
		}
	}

	public void printTabelVoorkomens(PrintStream out) {
		out.println("woord ------------------ voorkomens");

		// alle woorden ophalen
		HashMap<String, ArrayList<Integer>> words = woordposities.getData();

		// in een lijst stoppen om makkelijk op key te kunnen sorteren
		ArrayList<Entry<String, ArrayList<Integer>>> results = new ArrayList<>(words.entrySet());

		// sorteren met eigen comparator
		Collections.sort(results, new setComparator());

		// in goede volgorde uitprinten
		for (Entry<String, ArrayList<Integer>> e : results) {
			out.println(String.format("%1$-" + 16 + "s", e.getKey()) + " | " + e.getValue().size());
		}

	}

	/**
	 * 
	 * @author Patrick
	 * 
	 *         Custom comparator om de lengte van de arraylists met elkaar te kunnen vergelijken
	 * 
	 */
	private class setComparator implements Comparator<Entry<String, ArrayList<Integer>>> {

		@Override
		public int compare(Entry<String, ArrayList<Integer>> item1, Entry<String, ArrayList<Integer>> item2) {
			if (item1.getValue().size() < item2.getValue().size())
				return 1;
			if (item1.getValue().size() > item2.getValue().size())
				return -1;
			return 0;
		}

	}
}
