import java.util.ArrayList;

import messages.GiveResult;
import messages.Show;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
/**
 * 
 * @author Jan Stroet
 * PrimeCollector collects all primes found
 *
 */
public class PrimeCollector extends UntypedActor {
	private ArrayList<Integer> primes;
	private ActorRef resultRequester = null;
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private boolean finished = false;
	
	public PrimeCollector(){
		primes = new ArrayList<Integer>();		
	}
	
	@Override 
	public void preStart(){
		log.debug("Starting");		
	}

	@Override
	public void onReceive(Object message) throws Exception {
		if ( message instanceof Integer){ /* new prime */
			primes.add(0,(Integer)message);
		} else if (message instanceof Show){
			int numberToShow = ((Show)message).getNumber();
			for(int i = 0; i< numberToShow && i<primes.size(); i++){
				log.info("" + primes.get(i) );
			}
		} else if ( message instanceof GiveResult) {
			resultRequester = getSender();
			if (finished){
				resultRequester.tell(primes.clone(), getSelf());
			}
		}
		else if ( message instanceof String){
			if (((String) message).equals("Finished")) {
				finished = true;
				if (resultRequester != null) {
					resultRequester.tell(primes.clone(), getSelf());
				}
			} else 
				unhandled(message);
		} else
			unhandled(message);
	}
}
