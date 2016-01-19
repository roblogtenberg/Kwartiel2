package opdracht3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AutoRAI {

	private Lock lock;
	private Condition insideAsVisitor, insideAsBuyer;
	private final int MAX_PEOPLE_INSIDE = 10;
	private int nrOfPeopleInside;// aantal personen binnen
	private int nrOfBuyersAchterElkaar, nrOfBuyersInRow, nrOfVisitorsAchterElkaar;
	private boolean ignoreBuyersInRow;// boolean om kopers in de rij te negeren

	public AutoRAI() {
		lock = new ReentrantLock();
		insideAsVisitor = lock.newCondition();
		insideAsBuyer = lock.newCondition();

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

	public void toAutoRAIAsVisitor() throws InterruptedException {
		lock.lock();

		try {
			// wachten als de preconditie klopt en doorgaan als die niet klopt
			while (full() || (!noBuyerInRow() && !ignoreBuyersInRow)) {
				insideAsVisitor.await();

			}
			// aantal kijkers achter elkaar verhogen, zodat je weet dat er weer een koper naar
			// mag nadat er achter 10 kijkers naar binnen zijn gegaan
			nrOfVisitorsAchterElkaar++;
			// de aantal kopers achter elkaar op 0 zetten, zodat kopers weer naar binnen kunnen
			nrOfBuyersAchterElkaar = 0;
			nrOfPeopleInside++;// aantal personen binnen verhogen
		} finally {
			lock.unlock();
		}
	}

	public void toAutoRAIAsBuyer(Buyer buyer) throws InterruptedException {

		lock.lock();

		try {
			nrOfBuyersInRow++;// aantal kopers in de rij verhogen
			// wachten als de preconditie klopt en doorgaan als die niet klopt
			while (!empty() && nrOfBuyersAchterElkaar <= 4) {
				// als aantal kopers achter elkaar >4 dan mogen kopers genegeerd worden door kijker
				if (nrOfBuyersAchterElkaar >= 4) {
					ignoreBuyersInRow = true;
				}
				insideAsBuyer.await();

			}
			// aantal kopers achter elkaar verhogen zodat er na 4 keer achter elkaar kijkers weer na
			// binnen mogen
			nrOfBuyersAchterElkaar++;
			nrOfPeopleInside = 10;// vol maken

		} finally {
			lock.unlock();
		}
	}

	public void leaveAutoRAIAsBuyer(Buyer buyer) throws InterruptedException {
		lock.lock();

		try {

			System.out.println("een koper gaat naar buiten");

			nrOfPeopleInside = 0;// niemand meer binnen
			nrOfBuyersInRow--;// kopers in de rij verlagen
			// signaal naar 10 kijkers
			if (nrOfBuyersAchterElkaar >= 4) {
				ignoreBuyersInRow = true;
				System.out.println("kijkers geroepen");
				for (int i = 0; i < MAX_PEOPLE_INSIDE; i++) {
					insideAsVisitor.signal();

				}
			} else {
				// signaal naar kopers
				insideAsBuyer.signal();
			}

		} finally {

			lock.unlock();
		}
	}

	public void leaveAutoRAIAsVisitor(int id) throws InterruptedException {
		lock.lock();

		try {
			// zorgen dat kopers ,na 10x naar binnen zijn, niet meer genegeerd worden
			if (nrOfVisitorsAchterElkaar >= 10) {
				ignoreBuyersInRow = false;
			}
			nrOfPeopleInside--;// aantal personen binnen verlagen
			System.out.println("aantal kijkers binnen: " + nrOfPeopleInside);
			// signaal naar koper als er een koper in de rij staat en iedereen eruit is
			if (empty() && !noBuyerInRow()) {
				insideAsBuyer.signal();
				// signaal naar kijker als er geen koper in de rij staat
			} else if (noBuyerInRow()) {
				insideAsVisitor.signal();
			}
		} finally {
			lock.unlock();
		}
	}

}