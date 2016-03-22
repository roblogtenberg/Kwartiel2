package opdracht3;


import java.util.Random;

public class Buyer extends Thread {

	private int id;
	private AutoRAI autorai;
	private Random random;
	private int wait;

	public Buyer(int id, AutoRAI autoRAI) {
		this.id = id;
		autorai = autoRAI;
		random = new Random();
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
				wait = (random.nextInt(4) * 1000);
				Thread.sleep(wait);// tijd dat de koper geen auto mag kopen
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void justLive() {
		try {
			System.out.println("Koper " + id + " : Gewoon chillen");
			wait = (random.nextInt(4) * 1000);
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void buyCar() {
		try {
			System.out.println("Koper " + id + " : Ik zoek naar een auto");
			wait = (random.nextInt(4) * 1000);
			Thread.sleep(wait);
			System.out.println("Koper " + id + " : Ik heb een auto gevonden");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
