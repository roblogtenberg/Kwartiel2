package opdracht3;

import java.util.Random;

public class Visitor extends Thread {
	private AutoRAI autoRAI;
	private int id;
	private Random random;
	private int wait;

	public Visitor(int id, AutoRAI autoRAI) {
		this.id = id;
		this.autoRAI = autoRAI;
		random = new Random();
	}

	@Override
	public void run() {
		while (true) {

			try {
				justLive();
				System.out.println("Kijker " + id + " : Ik sta in de rij");
				autoRAI.toAutoRAIAsVisitor();
				System.out.println("Kijker " + id + " : Ik ben binnen en kijk rond");
				watchCars();
				System.out.println("Kijker " + id + " : Ik ben klaar met kijken en ga weg");
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
			System.out.println("Kijker " + id + " : Gewoon chillen");
			wait = (random.nextInt(4) * 1000);
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void watchCars() {
		try {
			System.out.println("Kijker " + id + " : Beetje kiekn");
			wait = (random.nextInt(4) * 1000);
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
