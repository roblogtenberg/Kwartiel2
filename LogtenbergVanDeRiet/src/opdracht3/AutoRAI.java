package opdracht3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AutoRAI {

	private int nrOfBuyers = 0;
	private int nrOfBuyersInside = 0;
	private int nrOfBuyersAchterElkaar = 0;
	private int maxNrOfBuyersAchterElkaar = 3;
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
		visitorMayInside = lock.newCondition();
		buyerMayInside = lock.newCondition();
	}

	public void toAutoRAI() throws InterruptedException {
		try {
			lock.lock();
			if (Thread.currentThread().getClass() == Visitor.class) {
				System.out.println("Bezoeker: " + Thread.currentThread().getName() + " meld zich");
				nrOfWaitingVisitors++;
				while (nrOfWaitingBuyers > 0 || buyerInBuiling == true) {
					if (nrOfBuyersInside >= maxNrOfBuyers && buyerInBuiling == false) {
						break;
					}
					visitorMayInside.await();
				}
				nrOfWaitingVisitors--;
				nrOfVisitorsInside++;
			} else if (Thread.currentThread().getClass() == Buyer.class) {
				System.out.println("Koper: " + Thread.currentThread().getName() + " meld zich");
				nrOfWaitingBuyers++;
				while (buyerInBuiling == true || nrOfBuyersAchterElkaar >= maxNrOfBuyersAchterElkaar) {
					buyerMayInside.await();
				}
				buyerInBuiling = true;
				nrOfWaitingBuyers--;
				nrOfBuyersAchterElkaar++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void leaveAutoRAI() {

		if (Thread.currentThread().getClass() == Visitor.class) {
			System.out.println("Bezoeker: " + Thread.currentThread().getName() + " verlaat de AutoRAI");
			nrOfVisitorsInside--;
			nrOfBuyersAchterElkaar = 0;
		}

		if (Thread.currentThread().getClass() == Buyer.class) {
			System.out.println("Koper: " + Thread.currentThread().getName() + " verlaat de AutoRAI");
			buyerInBuiling = false;
		}

		if (nrOfWaitingBuyers > 0) {
			System.out.println("Wachtende kopers = " + nrOfWaitingBuyers);

			if (nrOfBuyersAchterElkaar >= maxNrOfBuyersAchterElkaar) {
				System.out.println("Te veel kopers achter elkaar!");
				if (nrOfVisitors == 0) {
					System.out.println("Er is geen bezoeker aan het wachten dus teller verlagen met 1");
					nrOfBuyersAchterElkaar--;
					buyerMayInside.signal();
				} else {
					sendAllVisitors();
				}
			} else {
				buyerMayInside.signal();
			}
		}
	}

	private void sendAllVisitors() {
		System.out.println("Aantal lui kunnen naar binnen");
		for (int i = 0; i < nrOfWaitingVisitors; i++) {
			visitorMayInside.signal();
		}
	}
}
