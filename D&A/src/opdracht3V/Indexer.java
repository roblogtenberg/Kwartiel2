package opdracht3V;

import opdracht3V.impl.Node;

public class Indexer {

	private Node<Integer> node;

	public static void main(String[] args) {
		Indexer indexer = new Indexer();
		indexer.indexText(indexer);
	}

	private void indexText(Indexer indexer) {
		String textInput = "aap";
		String textInput1 = "aapje";
		String textInput2 = "aardbei";
		String aids = textInput + " " + textInput1 + " " + textInput2;

		// indexer.index(textInput);
		// indexer.index(textInput1);
		// indexer.index(textInput2);
		indexer.index(aids);
		System.out.println(node.toDOTString());
	}

	public void index(String textInput) {
		String standardized = textInput.replaceAll("[^a-zA-Z ]", "").toLowerCase();
		String[] words = standardized.split(" ");
		node = new Node<>();
		int wordCounter = 0;
		for (String word : words) {
			node.insert(word, wordCounter);
			wordCounter++;
		}
	}
}
