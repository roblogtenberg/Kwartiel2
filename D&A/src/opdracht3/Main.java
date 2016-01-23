package opdracht3;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Node<Integer> trie = new Node<Integer>();
		String word = "knul";

		trie.insert(word, 1);
		trie.insert("knulletje", 1);

		trie.toDOTString();

	}

}
