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
				autorai.toAutoRAIAsBuyer();
				System.out.println("Koper " + id + " : Ik ga naar binnen om een auto te kopen");
				buyCar();
				System.out.println("Koper " + id + " : een auto gekocht");
				autorai.leaveAutoRAIAsBuyer();
				Thread.sleep(50000);// tijd dat de koper geen auto mag kopen
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void justLive() {
		try {
			Thread.sleep(3000);
			System.out.println("Koper " + id + " : Gewoon chillen");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void buyCar() {
		try {
			System.out.println("Koper " + id + " : Ik zoek naar een auto");
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
