package messages;
/**
 * 
 * @author Jan  Stroet
 *
 *Serves as message type for PrimeCollector to log last primes received
 */

public final class Show {
	private int number;
	public Show(int n){
		number = n;
	}
	/**
	 * 
	 * @return the number of primes to show
	 */
	public int getNumber(){
		return number;
	}
}
