package opdracht3;

public class World {
	private static int NR_OF_MAX_PEOPLE = 10;
	private static int NR_OF_MAX_BUYERS = 5;

	public static void main(String[] args) {
		Thread[] visitor;
		Thread[] buyer;
		visitor = new Thread[NR_OF_MAX_PEOPLE];
		buyer = new Thread[NR_OF_MAX_BUYERS];

		visitor[0] = new Visitor("1");
		visitor[0].start();

		buyer[0] = new Buyer("1", 44000);
		buyer[0].start();

		visitor[1] = new Visitor("2");
		visitor[1].start();

		visitor[2] = new Visitor("3");
		visitor[2].start();

		buyer[1] = new Buyer("2", 0);
		buyer[1].start();

		visitor[3] = new Visitor("4");
		visitor[3].start();

		visitor[4] = new Visitor("5");
		visitor[4].start();

		buyer[2] = new Buyer("3", 6000);
		buyer[2].start();

		visitor[5] = new Visitor("6");
		visitor[5].start();

		visitor[6] = new Visitor("7");
		visitor[6].start();

		buyer[3] = new Buyer("4", 0);
		buyer[3].start();

		visitor[7] = new Visitor("8");
		visitor[7].start();

		visitor[8] = new Visitor("9");
		visitor[8].start();

		visitor[9] = new Visitor("10");
		visitor[9].start();

		buyer[4] = new Buyer("5", 24999);
		buyer[4].start();
	}
}
