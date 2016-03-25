package agents;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import messages.Reservation;
import messages.ReservationStatus;
import messages.TicketRequest;
import messages.ReservationStatus.ReservationStatusCode;
import messages.ReservationUpdate;
import section.Section;

public class SectionAgent extends UntypedActor {
	private Section mySection;
	private String sectionName;
	private LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	
	public SectionAgent(String sectionName){
		this.sectionName = sectionName;
		mySection = new Section();
	}
	

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof TicketRequest){
			TicketRequest request = ((TicketRequest) msg);
			//if(mySection.canReserve(request.getNrOfTickets())){
				Reservation r = mySection.reserve(request.getNrOfTickets(), request.getUserId(), sectionName);
				if(r!= null){
					log.info("reservation made: "+ r);
					getSender().tell(r, getSelf());
				} else {
					log.info("reservation couldn't be made for: "+ request);
					getSender().tell(new ReservationStatus(request.getUserId(), ReservationStatusCode.STATUS_NO_SPACE), getSelf());
				}
				
			//} else {
				//getSender().tell(new ReservationStatus(request.getUserId(), ReservationStatusCode.STATUS_NO_SPACE), getSelf());
			//}
		} else if(msg instanceof ReservationUpdate){
			ReservationUpdate update = (ReservationUpdate) msg;
			assert update.getReservation().getSectionName().equals(sectionName): "Error this reservation is not for this section";
			if(update.getStatus() == ReservationStatusCode.STATUS_CANCELLED_RESERVATION){
				//reservation needs to be cancelled
				log.info("canceling reservation: "+ update.getReservation());
				mySection.cancelReservation(update.getReservation());
			} else if(update.getStatus() == ReservationStatusCode.STATUS_TICKETS_BOUGHT){
				//reservation needs to be set as bought
				log.info("reservation is payed for: "+update.getReservation());
				mySection.confirmPayed(update.getReservation());
			} else {
				unhandled(msg);
			}
			
		} else {
			unhandled(msg);
		}
		
	}
	
	@Override
	public void aroundPostStop() {
		StringBuilder b = new StringBuilder();
		b.append("\n");
		b.append(mySection.stringSection()+"\n");
		b.append("------------------------\n");
		//System.out.println(b.toString());
		log.info(b.toString());
		super.aroundPostStop();
	}

}
