package opdracht2;

import java.util.List;
import java.util.Stack;

public class SimpleTree<K, V> {

	private K key;
	private V value;
	private SimpleTree<K, V> left, right;

	public int size() {
		if (key == null) {
			return 0;
		}
		return 1 + (left.size()) + (right.size());
	}

	public boolean isEmpty() {
		if (left == null || right == null) {
			return true;
		}
		return false;
	}

	public boolean has(K aKey) {
		if (isEmpty()) {
			return false;
		}
		if (key.equals(aKey)) {
			return true;
		} else {
			return left.has(aKey) || right.has(aKey);
		}
	}

	public void put(K aKey, V anItem) {
		if (!isEmpty()) {
			if ((left.size() - right.size()) >= 1) {
				right.put(aKey, anItem);
			} else {
				left.put(aKey, anItem);
			}
		} else {
			key = aKey;
			value = anItem;
			right = new SimpleTree<>();
			left = new SimpleTree<>();
		}
	}

	public void remove(K aKey) {
		if (has(aKey)) {
			return;
		}
		if (this.key.equals(aKey)) {
			key = heaviestLeaf().key;
			value = heaviestLeaf().value;
			key = null;
			value = null;

		}
	}

	public boolean isLeaf() {
		if ((left == null || right == null)) {
			return true;
		} else {
			return left.isLeaf();
		}
	}

	public SimpleTree<K, V> heaviestLeaf() {
		if (isLeaf()) {
			return this;
		}
		if (left.height() > right.height()) {
			return left.heaviestLeaf();
		} else {
			return right.heaviestLeaf();
		}
	}

	public int height() {
		if (isEmpty()) {
			return 0;
		} else if (isLeaf()) {
			return 1;
		} else {
			return Math.max(left.height(), right.height());
		}

	}

	public SimpleTree<K, V> aCopy() {
		SimpleTree<K, V> tree = new SimpleTree<>();
		tree.value = value;
		tree.key = key;
		tree.left = left;
		tree.right = right;
		return tree;

	}

	public List<K> inorderKeys() {
		return null;

	}

	public void mirror() {

	}

	public V itemAt(K aKey) {
		return value;

	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		} else {
			return "(" + left.toString() + "," + key + "," + right.toString()
					+ ")";
		}
	}

	public void displayTree() {
		Stack<SimpleTree<K, V>> globalStack = new Stack<SimpleTree<K, V>>();
		globalStack.push(this);
		int emptyLeaf = 32;
		boolean isRowEmpty = false;
		System.out
				.println("****......................................................****");
		while (isRowEmpty == false) {

			Stack<SimpleTree<K, V>> localStack = new Stack<SimpleTree<K, V>>();
			isRowEmpty = true;
			for (int j = 0; j < emptyLeaf; j++)
				System.out.print(' ');
			while (globalStack.isEmpty() == false) {
				SimpleTree<K, V> temp = globalStack.pop();
				if (temp != null) {
					// System.out.print(temp.value!=null?temp.value:"0");
					System.out.print(temp.key != null ? temp.key : "0");

					localStack.push(temp.left);
					localStack.push(temp.right);
					if (temp.hasLeft() || temp.hasRight())
						isRowEmpty = false;
				} else {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}
				for (int j = 0; j < emptyLeaf * 2 - 2; j++)
					System.out.print(' ');
			}
			System.out.println();
			emptyLeaf /= 2;
			while (localStack.isEmpty() == false)
				globalStack.push(localStack.pop());
		}
		System.out
				.println("****......................................................****");
	}

	private boolean hasRight() {
		return right != null;
	}

	private boolean hasLeft() {
		return left != null;
	}
}
