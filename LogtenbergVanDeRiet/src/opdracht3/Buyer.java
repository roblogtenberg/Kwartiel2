package opdracht3;

public class Buyer extends Thread {

	private AutoRAI autoRAI;
	private int budget;

	public Buyer(String naam, int budget) {
		super(naam);
		autoRAI = new AutoRAI();
		this.budget = budget;
	}

	@Override
	public void run() {
		try {
			autoRAI.toAutoRAI();
			kijken();
			autoRAI.leaveAutoRAI();
			justLive();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void justLive() throws InterruptedException {
		System.out.println("Koper: " + getBuyerName() + " is living");
		Thread.sleep((int) 5 * 1000);
	}

	private void kijken() throws InterruptedException {
		System.out.println("Koper: " + getBuyerName() + " is aan het rondkijken");
	}

	public String getBuyerName() {
		return getName();
	}

	public int getBudget() {
		return budget;
	}
}
