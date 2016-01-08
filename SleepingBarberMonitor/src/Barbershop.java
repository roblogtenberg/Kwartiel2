import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 *  
 * @author Jan Stroet
 *
 * The Barbershop is a monitor in which customers get a haircut
 * and the barber gets customers to do a haircut
 */
public class Barbershop {
	
	Lock lock;

	private static final int NR_OF_SEATS = 3;
	private int nrOfCustomers = 0;
	
	private boolean finishedHaircut = false, invited=false;

	private Customer inChair;
	private Condition seatAvailable, invitation, finished, newCustomer,readyForCut;


	public Barbershop(){
		lock = new ReentrantLock();
		seatAvailable = lock.newCondition();
		invitation = lock.newCondition();
		finished = lock.newCondition();
		newCustomer = lock.newCondition();
		readyForCut = lock.newCondition();
	}
	
	/**
	 * a customer will call this method to get a haircut
	 * @throws InterruptedException
	 */
	
	public void getHaircut() throws InterruptedException {
		lock.lock();
		
		try {
			while (noSeatAvailable()) 
				seatAvailable.await();
			
			System.out.println(Thread.currentThread().getName() + " gets a seat in waitingroom");
			
			nrOfCustomers++;
			newCustomer.signal();
			
			while(!invited)
				invitation.await();
			invited = false;
			System.out.println(Thread.currentThread().getName() + " was invited by barber");
					
			nrOfCustomers--;
			seatAvailable.signal();
			System.out.println(Thread.currentThread().getName() + " leaves waitingroom");
			
			/* go sit in Barbers chair */
			inChair = (Customer) Thread.currentThread();
			readyForCut.signal();
			
			System.out.println(Thread.currentThread().getName() + " is seated in barber chair");
			
			while (!finishedHaircut)
				finished.await();
			finishedHaircut = false;
			
			System.out.println(Thread.currentThread().getName() + " leaves the barbershop");
			
		} finally {
			lock.unlock();
		}
		
	}
	
	/**
	 * Called by the barber to get the next customer to cut
	 * @return the customer who will get a haircut
	 * @throws InterruptedException
	 */
	
	public  Customer nextCustomer()throws InterruptedException{
		lock.lock();
		try {
			while (noCustomers()){
				System.out.println("Barber goes to sleep");
				newCustomer.await();
			}

			invited = true;
			invitation.signal();
			
			while (inChair == null)
				readyForCut.await();
		
			return inChair;
		} finally {
			lock.unlock();
		}
	
	}
	
	/**
	 * Called by the barber to show the customer out, 
	 * who just got a haircut
	 * @param customer the customer who got a haircut
	 */
	
	public void showOut(Customer customer){
		lock.lock();
		try{
			inChair = null;
			finishedHaircut = true;
			finished.signal();
		} finally{
			lock.unlock();
		}
	}
	
	private boolean noCustomers(){
		return nrOfCustomers == 0;
	}
	
	private boolean noSeatAvailable(){
		return nrOfCustomers == NR_OF_SEATS;
	}
}