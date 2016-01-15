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
				justLive();
				autoRAI.toAutoRAI();
				kijken();
				autoRAI.leaveAutoRAI();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void justLive() throws InterruptedException {
		System.out.println(getVisitorName() + " living");
		Thread.sleep((int) 5 * 1000);
	}

	private void kijken() throws InterruptedException {
		System.out.println("Bezoeker: " + getVisitorName() + " is aan het rondkijken");
	}

	private String getVisitorName() {
		return getName();
	}
}
