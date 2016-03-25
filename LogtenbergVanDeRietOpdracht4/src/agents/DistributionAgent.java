package agents;

import java.util.HashMap;
import java.util.Map;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import messages.Reservation;
import messages.ReservationStatus;
import messages.ReservationStatus.ReservationStatusCode;
import messages.ReservationUpdate;
import messages.TicketRequest;

public class DistributionAgent extends UntypedActor {
	
	private Map<String, ActorRef> sectionAgents;
	private Map<Integer, ActorRef> customerRequests;
	private Map<Integer, Reservation> pendingReservations;
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	public DistributionAgent(Map<String, ActorRef> sectionAgents) {
		assert sectionAgents != null : "section agents is null";
		this.sectionAgents = sectionAgents;
		customerRequests = new HashMap<Integer, ActorRef>();
		pendingReservations = new HashMap<Integer, Reservation>();
	}
	
	@Override
	public void preStart() throws Exception {
		log.debug("preStart");
		super.preStart();
	}
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof TicketRequest){
			TicketRequest request = (TicketRequest) msg;
			assert request.getNrOfTickets() <= CustomerAgent.MAX_NUMBER_OF_TICKETS_PP: "too many tickets requested";
			customerRequests.put(request.getUserId(), getSender());
			assert sectionAgents.containsKey(request.getSectionName()) : "a request for an uknown agent was sent";
			log.info("Received "+request);
			sectionAgents.get(request.getSectionName()).tell(request, getSelf());
		} else if(msg instanceof ReservationStatus){
			ReservationStatus status = (ReservationStatus) msg;
			log.info("Reservation status received : "+status);
			switch(status.getStatusCode()){
			case STATUS_NO_SPACE:
				customerRequests.get(status.getUserId()).tell(ReservationStatusCode.STATUS_NO_SPACE, getSelf());
				customerRequests.remove(status.getUserId()); //performance or memory
				break;
			case STATUS_CANCELLED_RESERVATION:
				Reservation r = pendingReservations.get(status.getUserId());
				sectionAgents.get(r.getSectionName()).tell(new ReservationUpdate(r, ReservationStatusCode.STATUS_CANCELLED_RESERVATION), getSelf());
				pendingReservations.remove(status.getUserId()); //performance or memory
				break;
			case STATUS_TICKETS_BOUGHT:
				Reservation reservationBought = pendingReservations.get(status.getUserId());
				getSender().tell(reservationBought, getSelf());
				sectionAgents.get(reservationBought.getSectionName()).tell(new ReservationUpdate(reservationBought, ReservationStatusCode.STATUS_TICKETS_BOUGHT), getSelf());
				pendingReservations.remove(status.getUserId()); //performance or memory
				break;
			case STATUS_AWAITING_PAIMENT:
				assert(false): "Error this actor is not supposed to receive this message";
			default: unhandled(msg);
			}
			
		} else if(msg instanceof Reservation){
			Reservation reservation = (Reservation) msg;
			pendingReservations.put(reservation.getUserId(), reservation);
			assert customerRequests.containsKey(reservation.getUserId()) : "this distributor doesnt know this customer " + reservation.getUserId();
			customerRequests.get(reservation.getUserId()).tell(ReservationStatusCode.STATUS_AWAITING_PAIMENT, getSelf());
			customerRequests.remove(reservation.getUserId()); //performance or memory
			log.info("Reservation received: "+reservation);
			
		} else {
			unhandled(msg);
		}
		
	}
	
	@Override
	public void postStop() throws Exception {
		//checks to see if the maps are kept clean properly
		assert customerRequests.isEmpty(): "there where still customer request on termination";
		assert pendingReservations.isEmpty(): "there where still pending reservations on termination";
		super.postStop();
	}

}
