package opdracht3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AutoRAI {

	private Lock lock;
	private Condition insideAsVisitor, insideAsBuyer;
	private final int MAX_PEOPLE_INSIDE = 10;
	private int PEOPLE_IN_ROW = 0;
	private int nrOfPeopleInside;
	private int nrOfBuyersAchterElkaar, nrOfBuyersInRow, nrOfVisitorsAchterElkaar;
	private boolean ignoreBuyersInRow;

	public AutoRAI() {
		lock = new ReentrantLock();
		insideAsVisitor = lock.newCondition();
		insideAsBuyer = lock.newCondition();
	}

	public void toAutoRAIAsVisitor() throws InterruptedException {
		lock.lock();

		try {
			while (full() || (!noBuyerInRow() && !ignoreBuyersInRow)) {
				insideAsVisitor.await();
			}
			nrOfVisitorsAchterElkaar++;
			nrOfBuyersAchterElkaar = 0;
			nrOfPeopleInside++;
			PEOPLE_IN_ROW--;
		} finally {
			lock.unlock();
		}
	}

	public void toAutoRAIAsBuyer() throws InterruptedException {
		lock.lock();

		try {
			nrOfBuyersInRow++;
			while (!empty() && nrOfBuyersAchterElkaar <= 3) {
				PEOPLE_IN_ROW++;
				if (nrOfBuyersAchterElkaar >= 3) {
					ignoreBuyersInRow = true;
				}
				insideAsBuyer.await();
			}
			nrOfBuyersAchterElkaar++;
			nrOfPeopleInside = 10;

		} finally {
			lock.unlock();
		}
	}

	public void leaveAutoRAIAsBuyer() throws InterruptedException {
		lock.lock();

		try {

			System.out.println("Er gaat een koper gaat naar buiten, Aju!");

			nrOfPeopleInside = 0;
			nrOfBuyersInRow--;
			if (nrOfBuyersAchterElkaar >= 3) {
				ignoreBuyersInRow = true;
				for (int i = 0; i < (MAX_PEOPLE_INSIDE - PEOPLE_IN_ROW); i++) {
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
}