package opdracht3;

public class World {
	private static int NR_OF_MAX_VISITORS = 10;
	private static int NR_OF_MAX_BUYERS = 5;

	public static void main(String[] args) {
		AutoRAI autoRAI = new AutoRAI();
		Thread[] visitor;
		Thread[] buyer;
		visitor = new Thread[NR_OF_MAX_VISITORS];
		buyer = new Thread[NR_OF_MAX_BUYERS];

		for (int i = 0; i < NR_OF_MAX_VISITORS; i++) {
			visitor[i] = new Visitor(i, autoRAI);
			visitor[i].start();
		}

		for (int i = 0; i < NR_OF_MAX_BUYERS; i++) {
			buyer[i] = new Buyer(i, autoRAI);
			buyer[i].start();
		}
	}
}
