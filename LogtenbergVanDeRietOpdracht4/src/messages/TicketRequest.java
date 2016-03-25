package messages;

public final class TicketRequest {
	private final String sectionName;
	private final int nrOfTickets;
	private final int userId;

	public TicketRequest(String sectionName, int nrOfTickets, int userId) {
		this.sectionName = sectionName;
		this.nrOfTickets = nrOfTickets;
		this.userId = userId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public int getNrOfTickets() {
		return nrOfTickets;
	}

	public int getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "\tTicketRequest for: " + sectionName + " number of seats " + nrOfTickets + " requested by: " + userId;
	}
}
