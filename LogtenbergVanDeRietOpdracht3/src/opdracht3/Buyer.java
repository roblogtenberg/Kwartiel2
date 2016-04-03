package opdracht3;

import java.util.Random;

public class Buyer extends Thread {

	private AutoRAI autoRAI;
	private Random random;
	private int wait;

	public Buyer() {
		autoRAI = AutoRAI.getInstance();
		random = new Random();
	}

	@Override
	public void run() {
		while (true) {

			try {
				justLive();
				autoRAI.toAutoRAIAsBuyer();
				buyCar();
				autoRAI.leaveAutoRAIAsBuyer();
				wait = (random.nextInt(4) * 1000);
				Thread.sleep(wait);// tijd dat de koper geen auto mag kopen
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

	public void buyCar() {
		try {
			wait = (random.nextInt(4) * 1000);
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
