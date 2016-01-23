package opdracht3;

import java.util.ArrayList;

import opdracht3.interfaces.TrieInterface;

public class Node<D> implements TrieInterface<D> {

	private D value;
	private ArrayList<Node<D>> children = new ArrayList<Node<D>>();
	private ArrayList<Character> iets = new ArrayList<>();

	public Node() {
		// TODO Auto-generated constructor stub
	}

	public Node(D data) {
		this.value = data;
	}

	@Override
	public void insert(String word, D data) {
		for (int i = 0; i < word.length(); i++) {
			System.out.println(word.charAt(i));
			iets.add(i, word.charAt(i));
			children.add(new Node<D>(data));
			value = word.charAt(i);
		}
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
		ArrayList<Node<D>> lookup = new ArrayList<Node<D>>();
		lookup.add(this);
		for (int i = 0; i < lookup.size(); i++) {
			Node<D> currentNode = lookup.get(i);
			for (Node<D> child : currentNode.children) {
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
			Node<D> node = lookup.get(i);
			for (Node<D> child : node.children) {
				sb.append(String.format("n%d -> n%d;\n", i, lookup.indexOf(child)));
			}
		}

		sb.append("}");
		return sb.toString();
	}
}
