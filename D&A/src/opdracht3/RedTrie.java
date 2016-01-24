package opdracht3;

import java.lang.reflect.Array;
import java.util.List;

import opdracht3.interfaces.TrieInterface;

public class RedTrie<D> implements TrieInterface<D> {

	private Class<?> tClass;

	private Node<D> parent;

	public RedTrie() {
		parent = new Node<D>("");
	}

	@Override
	public void insert(String word, D data) {
		if (tClass == null)
			tClass = data.getClass();

		int p = pathToObject(tClass);
		int n = pathToObject(data.getClass());

		if (n < p)
			tClass = data.getClass();

		parent.insert(word, data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public D[] search(String word) {
		if (tClass == null)
			return null;

		List<D> l = searchL(word);
		if (l == null)
			return null;

		D[] array = (D[]) Array.newInstance(tClass, l.size());

		for (int i = 0; i < l.size(); i++) {
			array[i] = l.get(i);
		}

		return array;
	}

	public List<D> searchL(String word) {
		return parent.find(word);
	}

	@Override
	public void delete(String word) {
		parent.delete(word);

		// rebuild the tree
		parent.reduce();
	}

	@Override
	public String toDOTString() {
		return parent.toDOTString();
	}

	public static int pathToObject(Class<?> cls) {
		int counter = 0;
		Class<?> current = cls;
		while (current != Object.class) {
			current = current.getSuperclass();
			counter++;
		}
		return counter;
	}
}
