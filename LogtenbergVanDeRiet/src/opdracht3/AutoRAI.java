package opdracht3;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AutoRAI {

	private static final int NR_OF_AVAILABLEPLACES = 10;
	private int nrOfBuyers = 0;
	private int nrOfBuyersInside = 0;
	private int nrOfBuyersAchterElkaar = 0;
	private int maxNrOfBuyersAchterElkaar = 0;
	private int maxNrOfBuyers = 0;
	private int nrOfWaitingBuyers = 0;
	private int nrOfVisitors = 0;
	private int nrOfVisitorsInside = 0;
	private int nrOfWaitingVisitors = 0;
	private boolean buyerInBuiling = false;

	Lock lock;
	private Condition placesAvailable, visitorMayInside, buyerMayInside;

	public AutoRAI() {
		lock = new ReentrantLock();
		placesAvailable = lock.newCondition();
	}

	public void toAutoRAI() throws InterruptedException {
		if (Thread.currentThread().getClass() == Visitor.class) {
			System.out.println("Er meld zich een berzoeker");
			nrOfWaitingVisitors++;
			while (nrOfWaitingBuyers > 0 || buyerInBuiling == true) {
				if (nrOfBuyersInside >= maxNrOfBuyers && buyerInBuiling == false) {
					break;
				}
				visitorMayInside.await();
			}
			nrOfWaitingVisitors++;
			nrOfVisitorsInside++;
		} else if (Thread.currentThread().getClass() == Buyer.class) {
			System.out.println("Er meld zich een koper");
			nrOfWaitingBuyers++;
			while (nrOfVisitorsInside > 0 || buyerInBuiling == true || nrOfBuyersAchterElkaar >= maxNrOfBuyersAchterElkaar) {
				buyerMayInside.await();
			}
			buyerInBuiling = true;
			nrOfWaitingBuyers--;
			nrOfBuyersAchterElkaar++;
		}
	}

	public void leaveAutoRAI() {

	}
}
