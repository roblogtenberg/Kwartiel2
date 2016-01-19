package opdracht3;

public class World {
	private static int NR_OF_MAX_PEOPLE = 10;
	private static int NR_OF_MAX_BUYERS = 5;

	public static void main(String[] args) {
		Thread[] visitor;
		Thread[] buyer;
		visitor = new Thread[NR_OF_MAX_PEOPLE];
		buyer = new Thread[NR_OF_MAX_BUYERS];

		for (int i = 0; i < NR_OF_MAX_PEOPLE; i++) {
			visitor[i] = new Visitor("" + (i + 1));
			visitor[i].start();
		}

		buyer[0] = new Buyer("1", 44000);
		buyer[0].start();

		buyer[1] = new Buyer("2", 0);
		buyer[1].start();

		buyer[2] = new Buyer("3", 6000);
		buyer[2].start();

		buyer[3] = new Buyer("4", 0);
		buyer[3].start();

		buyer[4] = new Buyer("5", 24999);
		buyer[4].start();
	}
}
