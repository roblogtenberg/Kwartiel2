package opdracht3;

public class Main {

	public static void main(String[] args) {
		RedTrie<Integer> trie = new RedTrie<Integer>();

		trie.insert("aap", 1);
		trie.insert("aapje", 2);
		trie.insert("aapjes", 3);
		trie.insert("aaphoofd", 4);

		System.out.println(trie.toDOTString());

	}

}
