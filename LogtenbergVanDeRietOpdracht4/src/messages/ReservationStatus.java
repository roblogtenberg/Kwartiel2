package messages;

public final class ReservationStatus {
	public enum ReservationStatusCode {
		STATUS_NO_SPACE, STATUS_AWAITING_PAIMENT, STATUS_TICKETS_BOUGHT, STATUS_CANCELLED_RESERVATION
	}
	private final int userId;
	private final ReservationStatusCode statusCode;
	
	public ReservationStatus(int userId, ReservationStatusCode statusCode) {
		this.userId = userId;
		this.statusCode = statusCode;
	}
	
	public int getUserId() {
		return userId;
	}

	public ReservationStatusCode getStatusCode() {
		return statusCode;
	}
	@Override
	public String toString(){
		return "Reservation status user id: "+ userId +" status code: "+ statusCode;
	}

}


