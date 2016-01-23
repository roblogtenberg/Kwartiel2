import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import messages.GiveResult;
import messages.Show;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;

/**
 * 
 * @author Jan Stroet
 * Adapted from C.A.R. Hoare - Communicating Sequential Processes - CACM 1978
 *
 */
public class Eratosthenes {
	public final static int MAXNUMBER = 50000;

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		Timeout timeout = new Timeout(Duration.create(5, "seconds"));
		ActorSystem _system = ActorSystem.create("Eratosthenes");	
		ActorRef collector = _system.actorOf(Props.create(PrimeCollector.class),"PrimeCollector");
		ActorRef first = _system.actorOf(SieveElement.prop(collector),"firstelement");
		
		for( int i=2; i<= MAXNUMBER; i++){
			first.tell(i, null);
		} 
		first.tell("Finished",  null);
		
		Show show = new Show(5);
//		collector.tell(show, null);
		
		ArrayList<Integer> allPrimes = new ArrayList<>();
		boolean finished = false;
		Future<Object> future;
		
		while (! finished){
			try {
				finished = true;
				collector.tell(show, null);
				
				/* an implicit actor is created, which sends a message to the collector
				 * and waits for the reply, which will be the result of Await 
				 */
				future = Patterns.ask(collector, new GiveResult(),timeout);
				allPrimes = (ArrayList<Integer>) Await.result(future,timeout.duration());
			} catch (TimeoutException t){ 
				finished = false;
				System.out.println("time out: result not available yet");
			}
			catch (Exception e) {
				System.out.println("Await exception"+ e.getMessage());
			}
		}

		System.out.println("Number of primes found below " + MAXNUMBER +": " + allPrimes.size());


		System.out.println(allPrimes);
		
		_system.terminate();
	}
}
