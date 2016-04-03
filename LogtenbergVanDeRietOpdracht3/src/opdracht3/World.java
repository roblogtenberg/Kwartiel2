package opdracht3;

public class World {
	private static int NR_OF_MAX_VISITORS = 1;
	private static int NR_OF_MAX_BUYERS = 20;

	public static void main(String[] args) {
		Thread[] visitor;
		Thread[] buyer;
		visitor = new Thread[NR_OF_MAX_VISITORS];
		buyer = new Thread[NR_OF_MAX_BUYERS];

		for (int i = 0; i < NR_OF_MAX_VISITORS; i++) {
			visitor[i] = new Visitor();
			visitor[i].start();
		}

		for (int i = 0; i < NR_OF_MAX_BUYERS; i++) {
			buyer[i] = new Buyer();
			buyer[i].start();
		}
	}
}
