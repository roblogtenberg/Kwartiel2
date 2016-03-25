package agents;

import java.util.Random;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import config.AplConfig;
import messages.Reservation;
import messages.ReservationStatus;
import messages.TicketRequest;
import messages.ReservationStatus.ReservationStatusCode;
import section.Section;

public class CustomerAgent extends UntypedActor {
	public final static int MAX_NUMBER_OF_TICKETS_PP = 4;
	public final static String DONE = "I AM DONE";
	public final static String START = "Ticket sale started";
	private final int myId;
	private final int nrOfKnownSections = 7;
	private final String myDesiredSection;
	private final int myDesiredNumberOfTickets;
	private ActorRef router;
	private Reservation myReservation;
	private ActorRef terminationListener;
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

	public CustomerAgent(ActorRef router, ActorRef terminationListener, int id) {
		this.myId = id;
		this.router = router;
		myDesiredSection = randomSectionSelector();
		myDesiredNumberOfTickets = generateRandomNumberOfTickets();
		this.terminationListener = terminationListener;
	}

	@Override
	public void preStart() throws Exception {
		log.debug("preStart");
		super.preStart();
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof String) {
			if (msg.equals(START)) {
				log.info("wants " + myDesiredNumberOfTickets + " seat(s) for " + myDesiredSection);
				router.tell(new TicketRequest(myDesiredSection, myDesiredNumberOfTickets, myId), getSelf());
			} else {
				unhandled(msg);
			}
		}
		if (msg instanceof ReservationStatusCode) {
			ReservationStatusCode code = (ReservationStatusCode) msg;
			if (code == ReservationStatusCode.STATUS_AWAITING_PAIMENT) {
				ReservationStatusCode codeToSend = payOrCancel();
				ReservationStatus status = new ReservationStatus(myId, codeToSend);
				getSender().tell(status, getSelf());
				if (codeToSend == ReservationStatusCode.STATUS_CANCELLED_RESERVATION) {
					log.info(myId + " payment cancelled/failed");
					terminationListener.tell(DONE, getSelf());
				}

			} else if (code == ReservationStatusCode.STATUS_NO_SPACE) {

				terminationListener.tell(DONE, getSelf());// as extra feature a
															// customer could
															// say: hey
				// give me the number of tickets for any section.
				log.info(myId + " cant get a reservation; no space");
			}
		} else if (msg instanceof Reservation) {
			myReservation = (Reservation) msg;
			assert myReservation != null : "given reservation is null";
			assert myReservation.getSeatNumbers()
					.size() == myDesiredNumberOfTickets : "to many or to les seats where reserved";
			assert myReservation.getSectionName().equals(myDesiredSection) : "reservation is not for the right section";
			assert myReservation.getUserId() == myId : "this reservation is for another user";
			log.info(myId + " received reservation: " + myReservation.toString());
			terminationListener.tell(DONE, getSelf());
		} else {
			unhandled(msg);
		}

	}

	public int getMyId() {
		return myId;
	}

	/**
	 * Generates the status code pay or cancel.
	 * 
	 * @return ReservationStatusCode that was generated
	 */
	public ReservationStatusCode payOrCancel() {
		Random r = new Random();
		int result = r.nextInt(100 - 1) + 1;
		if (result < AplConfig.PERCENTAGE_KANS_OP_ANNULEREN) {
			return ReservationStatusCode.STATUS_CANCELLED_RESERVATION;
		} else {
			return ReservationStatusCode.STATUS_TICKETS_BOUGHT;
		}
	}

	/**
	 * Randomly picks a Section that this customer wants to go to.
	 * 
	 * @return the Section that was chosen
	 */
	public String randomSectionSelector() {
		Random r = new Random();
		int result = r.nextInt(nrOfKnownSections) + 1;
		switch (result) {
		case 1:
			return Section.FLOOR;
		case 2:
			return Section.FIRST_RING_NORTH;
		case 3:
			return Section.FIRST_RING_SOUTH;
		case 4:
			return Section.FIRST_RING_WEST;
		case 5:
			return Section.SECOND_RING_NORTH;
		case 6:
			return Section.SECOND_RING_SOUTH;
		case 7:
			return Section.SECOND_RING_WEST;
		default:
			assert false : "error uknown section number was generated";
			return null;
		}
	}

	/**
	 * Generates the number of seats this customer wants.
	 * 
	 * @return the number of tickets the customer wants.
	 */
	public int generateRandomNumberOfTickets() {
		Random r = new Random();
		int result = r.nextInt(MAX_NUMBER_OF_TICKETS_PP) + 1;
		return result;
	}

}
