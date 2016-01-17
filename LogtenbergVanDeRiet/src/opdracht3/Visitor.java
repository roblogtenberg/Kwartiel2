package opdracht3;

public class Visitor extends Thread {
	private AutoRAI autoRAI;

	public Visitor(String name) {
		super(name);
		autoRAI = new AutoRAI();
	}

	public void run() {
		while (true) {
			try {
				autoRAI.toAutoRAI();
				kijken();
				autoRAI.leaveAutoRAI();
				justLive();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void justLive() throws InterruptedException {
		System.out.println("Bezoeker: " + getVisitorName() + " is living");
		Thread.sleep((int) 3 * 1000);
	}

	private void kijken() throws InterruptedException {
		System.out.println("Bezoeker: " + getVisitorName() + " is aan het rondkijken");
	}

	private String getVisitorName() {
		return getName();
	}
}
