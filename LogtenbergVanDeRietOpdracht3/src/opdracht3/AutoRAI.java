package opdracht3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AutoRAI {

	private Lock lock;
	private Condition insideAsVisitor, insideAsBuyer;
	private final int MAX_PEOPLE_INSIDE = 10;
	private int nrOfPeopleInside;
	private int nrOfBuyersAchterElkaar, nrOfBuyersInRow, nrOfVisitorsAchterElkaar, nrOfVisitorsInRow;
	private boolean ignoreBuyersInRow;
	private boolean buyerInside;

	private static AutoRAI instance;

	public AutoRAI() {
		lock = new ReentrantLock();
		insideAsVisitor = lock.newCondition();
		insideAsBuyer = lock.newCondition();
		System.out.println("RAI is open");
	}

	public void toAutoRAIAsVisitor() throws InterruptedException {
		lock.lock();

		try {
			nrOfVisitorsInRow++;
			System.out.println("Visitor enters row\n" + this.betterString());

			while (full() || (!noBuyerInRow() && !ignoreBuyersInRow)) {
				insideAsVisitor.await();
			}
			nrOfVisitorsAchterElkaar++;
			nrOfBuyersAchterElkaar = 0;
			nrOfPeopleInside++;
			nrOfVisitorsInRow--;
			System.out.println("Visitor inside building\n" + this.betterString());
		} finally {
			lock.unlock();
		}
	}

	public void toAutoRAIAsBuyer() throws InterruptedException {
		lock.lock();

		try {
			nrOfBuyersInRow++;
			System.out.println("Buyer enters row\n" + this.betterString());
			while (!empty() && nrOfBuyersAchterElkaar <= 3) {
				if (nrOfBuyersAchterElkaar >= 3) {
					ignoreBuyersInRow = true;
				}
				insideAsBuyer.await();
			}
			nrOfBuyersAchterElkaar++;
			nrOfPeopleInside = 10;
			nrOfBuyersInRow--;
			buyerInside = true;
			System.out.println("Buyer inside building\n" + this.betterString());
		} finally {
			lock.unlock();
		}
	}

	public void leaveAutoRAIAsBuyer() throws InterruptedException {
		lock.lock();

		try {
			nrOfPeopleInside = 0;
			buyerInside = false;
			System.out.println("Buyer leaves building\n" + this.betterString());
			if (nrOfBuyersAchterElkaar >= 3) {
				ignoreBuyersInRow = true;
				for (int i = 0; i < nrOfVisitorsInRow && i < MAX_PEOPLE_INSIDE; i++) {
					insideAsVisitor.signal();
				}
			} else {
				insideAsBuyer.signal();
			}
		} finally {
			lock.unlock();
		}
	}

	public void leaveAutoRAIAsVisitor() throws InterruptedException {
		lock.lock();

		try {
			if (nrOfVisitorsAchterElkaar >= 10) {
				ignoreBuyersInRow = false;
			}
			nrOfPeopleInside--;
			if (empty() && !noBuyerInRow()) {
				insideAsBuyer.signal();
			} else if (noBuyerInRow()) {
				insideAsVisitor.signal();
			}
			System.out.println("Visitor leaves building\n" + this.betterString());
		} finally {
			lock.unlock();
		}
	}

	public boolean full() {
		return nrOfPeopleInside >= MAX_PEOPLE_INSIDE;
	}

	public boolean empty() {
		return nrOfPeopleInside == 0;
	}

	public boolean noBuyerInRow() {
		return nrOfBuyersInRow == 0;
	}

	public String betterString() {
		String betterString;
		if (buyerInside == true) {
			betterString = "Buyer in building: " + 1 + "\n" + "Buyers in row:\t" + nrOfBuyersInRow + "\n" + "Visitors in row: " + nrOfVisitorsInRow + "\n";
		} else {
			betterString = "Visitor(s) in building: " + nrOfPeopleInside + "\n" + "Buyers in row:\t" + nrOfBuyersInRow + "\n" + "Visitors in row: " + nrOfVisitorsInRow + "\n";
		}
		return betterString;
	}

	public static AutoRAI getInstance() {
		if (instance == null)
			instance = new AutoRAI();

		return instance;
	}
}