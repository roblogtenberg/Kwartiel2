package opdracht3;

import java.util.Random;

public class Visitor extends Thread {
	private AutoRAI autoRAI;
	private Random random;
	private int wait;

	public Visitor() {
		this.autoRAI = AutoRAI.getInstance();
		random = new Random();
	}

	@Override
	public void run() {
		while (true) {

			try {
				justLive();
				autoRAI.toAutoRAIAsVisitor();
				watchCars();
				autoRAI.leaveAutoRAIAsVisitor();
				wait = (random.nextInt(4) * 1000);
				Thread.sleep(wait);// na het bezoeken krijgt de bezoeker gratis eten en mag daar van genieten
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void justLive() {
		try {
			wait = (random.nextInt(4) * 1000);
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void watchCars() {
		try {
			wait = (random.nextInt(4) * 1000);
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
