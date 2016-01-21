package opdracht3;

import java.util.ArrayList;

import opdracht3.interfaces.TrieInterface;

public class Trie<D> implements TrieInterface<D> {

	@Override
	public void insert(String word, D data) {
		// TODO Auto-generated method stub

	}

	@Override
	public D[] search(String word) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String word) {
		// TODO Auto-generated method stub

	}

	@Override
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
