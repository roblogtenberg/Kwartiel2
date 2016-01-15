package opdracht3;

public class Buyer extends Thread {
	public Buyer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
	}

	private void justLive() throws InterruptedException {
		Thread.sleep((int) Math.random() * 1000);
	}
}
