package agents;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class TerminationListenerAgent extends UntypedActor {
	private final int terminationPoint;
	private int currentPoint;
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	public TerminationListenerAgent(int terminationPoint) {
		this.terminationPoint = terminationPoint;
	}
	
	@Override
	public void preStart() throws Exception {
		if(terminationPoint == 0){
			getContext().system().terminate();
		}
		super.preStart();
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof String){
			if(msg.equals(CustomerAgent.DONE)){
				currentPoint++;
				if(currentPoint == terminationPoint){
					log.info("all customers are done terminating program...");
					Thread.sleep(1000); // allows the final customers to receive their reservation
					getContext().system().terminate();
					
				}
			} else {
				unhandled(msg);
			}
		} else {
			unhandled(msg);
		}
		
	}

}
