package opdracht3;

public class Visitor extends Thread {
	private AutoRAI autoRAI;

	public Visitor() {
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
		Thread.sleep((int) Math.random() * 1000);
	}
}
