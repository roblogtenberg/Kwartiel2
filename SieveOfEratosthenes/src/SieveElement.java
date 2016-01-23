import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
/**
 * 
 * @author Jan Stroet
 * SieveElement manages 1 prime number
 * It checks whether a received number is divisible by the prime number it manages.
 * If so, it discards it
 * If not, it sends it to the next SieveElement
 * 
 * In fact a SieveElement is like a hole in a sieve
 * through this hole all multiples of the managed prime number will leave the sieve
 *
 */

public class SieveElement extends UntypedActor {

	LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	/**
	 * the prime number it manages
	 * when 0, no prime received yet
	 */
	private int myPrime = 0;
	/**
	 * refers to actor which manages the next prime number
	 */
	private ActorRef next = null;
	/**
	 * refers to actor which collects all primes found
	 */
	private final ActorRef primeCollector;
	

	public SieveElement(ActorRef theCollector) {
		primeCollector = theCollector;
	}
	
	/**
	 * Method to create a SieveElement actor
	 * @param collector
	 * @return
	 */
	public static Props prop(ActorRef collector) {
		return Props.create(SieveElement.class, collector);
	}


	@Override
	public void preStart() {
		// log.debug("Starting");
//		System.out.println("Starting new sieve element" + getSelf().path());
	}

	@Override
	public void onReceive(Object message) throws Exception {
 
		if (message instanceof Integer) {
			int number = (int) message;
			log.debug( "received " + number);
//			System.out.println("SE" + myPrime + "received " + number);
			if (myPrime == 0) { /* first value received */
				myPrime = number;
				primeCollector.forward(message, getContext());
			} else if (number % myPrime == 0) {
				/* discard: number is multiple of me */
			} else if (next == null) { /* new prime found */
				log.debug( "creates SE" + number);
//				System.out.println("SE" + myPrime + "creates new SE " + number);
				next = getContext().actorOf(SieveElement.prop(primeCollector), // child of this actor
//				next = getContext().system().actorOf(SieveElement.prop(primeCollector), //child of system actor
						"SE" + number);
				next.tell(number, getSelf());

			} else /* could be a new prime, so forward it */
				next.tell(number, getSelf()); 
//	/* OR	*/		next.forward(message, getContext()); /* original sender is forwarded*/			
		} 
		else if (message instanceof String) {
			String m = (String) message;
			if (m.equals("Finished")) {
				if (next != null) {
					next.forward(message, getContext());
				} else /* last sieve element tells primeCollector all primes have been computed */
					primeCollector.forward(message, getContext());
			}
		}else
			unhandled(message);
	}

}
