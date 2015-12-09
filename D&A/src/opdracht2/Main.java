package opdracht2;

import java.util.Random;

public class Main {

	private Random r = new Random();

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		// TODO Auto-generated constructor stub

		int[] heap = new int[10];

		TreeNode treeNode = new TreeNode(5);
		System.err.println(treeNode.arrayToTree(heap).toString());
	}
}
