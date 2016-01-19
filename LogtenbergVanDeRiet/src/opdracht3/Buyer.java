package opdracht3;

public class Buyer extends Thread {

	private int id;
	private Buyer buyer;
	AutoRAI autorai;

	public Buyer(int id, AutoRAI autoRAI) {
		this.id = id;
		autorai = autoRAI;
	}

	@Override
	public void run() {
		while (true) {

			try {
				justLive();
				System.out.println("Koper " + id + " : Ik sta in de rij");
				autorai.toAutoRAIAsBuyer(buyer);
				System.out.println("Koper " + id + " : Ik ga naar binnen om een auto te kopen");
				buyCar();
				System.out.println("Koper " + id + " : een auto gekocht");
				autorai.leaveAutoRAIAsBuyer(buyer);
				Thread.sleep(50000);// hij mag even niets halen
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void justLive() {
		try {
			Thread.sleep((long) (Math.random() * 30000) + 10000);
			System.out.println("Koper " + id + " : "
					+ "Gewoon chillen");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void buyCar() {
		try {
			System.out.println("Koper " + id + " :Zoeken naar auto");
			Thread.sleep((long) (Math.random() * 20000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
