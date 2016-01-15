package opdracht3;

public class Buyer extends Thread {

	private AutoRAI autoRAI;

	public Buyer(String naam) {
		super(naam);
		autoRAI = new AutoRAI();
	}

	@Override
	public void run() {
		try {
			justLive();
			autoRAI.toAutoRAI();
			kijken();
			autoRAI.leaveAutoRAI();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void justLive() throws InterruptedException {
		System.out.println(getBuyerName() + " living");
		Thread.sleep((int) 5 * 1000);
	}

	private void kijken() throws InterruptedException {
		System.out.println("Koper: " + getBuyerName() + " is aan het rondkijken");
	}

	public String getBuyerName() {
		return getName();
	}
}
