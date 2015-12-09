package opdracht2;

public class TreeNode {
	public TreeNode left, right;
	public int val;

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode arrayToTree(int[] heap) {
		TreeNode root = createTreeNode(heap, 1);
		return root;
	}

	private TreeNode createTreeNode(int[] heap, int index) {
		if (index <= heap.length) {
			Integer value = heap[index - 1];
			if (value != null) {
				TreeNode t = new TreeNode(value);
				t.left = createTreeNode(heap, index * 2);
				t.right = createTreeNode(heap, index * 2 + 1);
				return t;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "[left=" + left + ", right=" + right + "]";
	}
}