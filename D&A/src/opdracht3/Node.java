package opdracht3;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {

	private List<Node<T>> children;
	private List<T> values;
	private String key;

	public Node(String s) {
		this.key = s;
		children = new ArrayList<Node<T>>();
	}

	public List<T> find(String word) {
		// if there is no word length left
		if (word.length() == 0)
			return null;

		// if this is the data!
		if (word.equals(key))
			return values;

		String replace = word.replaceFirst(key, "");

		// search for the rest of the data
		for (int i = 0; i < children.size(); i++) {
			List<T> data = children.get(i).find(replace);

			// if there is data to return it means we found it !
			if (data != null) {
				return data;
			}
		}

		return null;
	}

	public boolean delete(String word) {
		// if there is no word length left
		if (word.length() == 0)
			return false;

		// if this is the data!
		if (word.equals(key)) {
			// delete the data
			this.values = null;

			return true;
		}

		String replace = word.replaceFirst(key, "");

		// search for the rest of the data
		for (int i = 0; i < children.size(); i++) {
			boolean data = children.get(i).delete(replace);

			// if there is data to return it means we found it !
			if (data) {
				return true;
			}
		}

		return false;
	}

	public void reduce() {

		// remove empty childs
		for (int i = 0; i < children.size(); i++) {
			children.get(i).reduce();

			// if its empty now (no value and children)
			if (children.get(i).isEmpty()) {
				// remove it
				children.remove(i);
				// go back one step
				i--;
			}
		}

		// if we have values, we should not merge
		if (values != null)
			return;

		// if its 1, we can might be able to reduce
		if (children.size() == 1 && !key.equals("")) {
			Node<T> child = children.get(0);

			// we can only reduce if the child has no children!
			if (!child.hasChildren()) {

				// give the values here
				this.values = child.values;
				// remove this key
				this.key += child.key;

				// remove the last child
				children.clear();
			}
		}
	}

	public boolean insert(String word, T data) {
		// if they are the same, add here.
		if (word.equals(key)) {
			addValue(data);
			return true;
		}

		// not one letter key
		if (key.length() > 1) {

			// start slitting if possible
			if (key.charAt(0) == word.charAt(0)) {
				// add child and insert into that child
				Node<T> child = new Node<T>(key.substring(1));

				// give the values to the child node, because thats the new end point
				child.values = this.values;
				this.values = null;

				key = String.valueOf(key.charAt(0));
				addChild(child);

				// call insert on self, for one letter insertion right now
				insert(word, data);

				return true;
			}

			return false;
		}
		// one (or zero) letter key
		else if (word.startsWith(key)) {

			String value = word.replaceFirst(key, "");

			if (!hasChildren()) {
				Node<T> node = new Node<T>(value);
				addChild(node);
				node.insert(value, data);

				return true;
			}

			for (int i = 0; i < children.size(); i++) {
				if (children.get(i).insert(value, data))
					return true;
			}

			Node<T> newNode = new Node<T>(value);
			addChild(newNode);
			newNode.insert(value, data);

			return true;
		} else {
			return false;
		}
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

	public boolean isExtern() {
		return values != null;
	}

	public boolean isIntern() {
		return values == null;
	}

	public boolean isEmpty() {
		return !hasChildren() && isIntern();
	}

	public List<T> getValues() {
		return values;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Node<T>> getChildren() {
		return children;
	}

	public void addChild(Node<T> node) {
		children.add(node);
	}

	public void addValue(T value) {
		if (values == null)
			values = new ArrayList<T>();

		values.add(value);
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
			String value = "";
			if (lookup.get(i).values != null)
				value = lookup.get(i).values.toString();
			sb.append(String.format("n%d [label=\"%s\"];\n", i, lookup.get(i).key + value));
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