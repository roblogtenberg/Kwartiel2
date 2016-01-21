package opdracht3V;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.scene.Node;

public class Trie<D> {

	private D data;

	private HashMap<Character, Trie<D>> moreTrie;

	public Trie() {
		moreTrie = new HashMap<>();
	}

	/**
	 * insert data into this trie
	 * 
	 * @param s
	 * @param data
	 */
	public void insert(String s, D data) {
		assert (s != null);

		/*
		 * wanneer er geen characters meer over zijn zijn we op de plek waar de data moet
		 */
		if (s.equals("")) {
			this.data = data;
			// klaar
			return;
		}

		Trie<D> nextTrie;

		/*
		 * als we nog geen trie hebben met het volgende character moeten we er een maken anders halen we hem op
		 */
		if (!moreTrie.containsKey(s.charAt(0))) {
			nextTrie = new Trie<>();
			moreTrie.put(s.charAt(0), nextTrie);
		} else {
			nextTrie = moreTrie.get(s.charAt(0));
		}

		// tenslotte de rest data inserten in de rest van de trie
		nextTrie.insert(s.substring(1), data);
	}

	/**
	 * search for data found for the given string
	 * 
	 * @param s
	 * @return the data found for this string
	 */
	public D search(String s) {
		assert (s != null);

		// op de goede plek mijn data moet dus terug
		if (s.equals("")) {
			return data;
		}

		// verder zoeken in de trie als die er is
		if (!moreTrie.containsKey(s.charAt(0))) {
			return null;
		} else {
			return moreTrie.get(s.charAt(0)).search(s.substring(1));
		}
	}

	/**
	 * @param place
	 *            where the data needs to be removed
	 * @return the removed data
	 */
	public D delete(String s) {
		assert (s != null);

		// mijn data moet weg
		if (s.equals("")) {
			D theData = data;
			data = null;
			return theData;
		}

		if (moreTrie.containsKey(s.charAt(0))) {
			Trie<D> trie = moreTrie.get(s.charAt(0));

			// verwijderen
			D theData = trie.delete(s.substring(1));

			// mag subtrie weg?
			if (!trie.hasChilderen() && !trie.hasData()) {
				moreTrie.remove(s.charAt(0));
			}

			return theData;

		}

		// niet gevonden
		return null;
	}

	/**
	 * check if this trie has childeren
	 * 
	 * @return true if this trie has childeren
	 */
	private boolean hasChilderen() {
		return !moreTrie.isEmpty();
	}

	/**
	 * check if this trie has data
	 * 
	 * @return true if this trie has data
	 */
	private boolean hasData() {
		return data != null;
	}

	/**
	 * get all data in a single hashmap
	 * 
	 * @return all data in this trie in a hashmap
	 */
	public HashMap<String, D> getData() {
		return getData("");
	}

	/**
	 * private methode outside doesn't need to worrie about the string
	 * 
	 * @param s
	 * @return
	 */
	private HashMap<String, D> getData(String s) {
		HashMap<String, D> results = new HashMap<>();

		// woorden van kinderen in hasmap stoppen
		for (Entry<Character, Trie<D>> e : moreTrie.entrySet()) {
			results.putAll(e.getValue().getData(s + e.getKey()));
		}

		// heb ik een woord, zoja erin stoppen
		if (data != null) {
			results.put(s, data);
		}

		return results;
	}

	public String toDOTString() {
		StringBuilder sb = new StringBuilder();
		sb.append("digraph mijngraaf {\n");

		// Collect all the nodes
		ArrayList<Node<T>> lookup = new ArrayList<Node<T>>();
		lookup.add(this);
		for (int i = 0; i < lookup.size(); i++) {
			Node<T> currentNode = lookup.get(i);
			for (Node<T> child : currentNode.children) {
				if (!lookup.contains(child)) {
					lookup.add(child);
				}
			}
		}

		// Create nodes
		for (int i = 0; i < lookup.size(); i++) {
			sb.append(String.format("n%d [label=\"%s\"];\n", i, lookup.get(i).value));
		}

		// Create edges
		for (int i = 0; i < lookup.size(); i++) {
			Node<T> node = lookup.get(i);
			for (Node<T> child : node.children) {
				sb.append(String.format("n%d -> n%d;\n", i, lookup.indexOf(child)));
			}
		}

		sb.append("}");
		return sb.toString();
	}

}
