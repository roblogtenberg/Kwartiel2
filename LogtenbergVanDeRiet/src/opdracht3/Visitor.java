package opdracht3;

public class Visitor extends Thread {
	private AutoRAI autoRAI;
	private int id;

	public Visitor(int id, AutoRAI autoRAI) {
		this.id = id;
		this.autoRAI = autoRAI;
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
				autoRAI.leaveAutoRAIAsVisitor(id);

				Thread.sleep(20000);// na het kijken mag die even uitrusten
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void justLive() {
		try {
			Thread.sleep((long) (Math.random() * 10000));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void watchCars() {
		try {
			Thread.sleep((long) (Math.random() * 10000));

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
