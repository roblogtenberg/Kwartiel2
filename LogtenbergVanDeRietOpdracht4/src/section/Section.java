package section;

import java.util.ArrayList;
import java.util.List;

import configuration.Config;
import messages.Reservation;

public class Section {

	private ArrayList<Row> rows = new ArrayList<>();
	public static final String FLOOR = "FLOOR_AGENT";
	public static final String FIRST_RING_NORTH = "FIRST_RING_NORTH_AGENT";
	public static final String FIRST_RING_WEST = "FIRST_RING_WEST_AGENT";
	public static final String FIRST_RING_SOUTH = "FIRST_RING_SOUTH_AGENT";
	public static final String SECOND_RING_NORTH = "SECOND_RING_NORTH_AGENT";
	public static final String SECOND_RING_WEST = "SECOND_RING_WEST_AGENT";
	public static final String SECOND_RING_SOUTH = "SECOND_RING_SOUTH_AGENT";

	public Section() {
		for (int i = 0; i < Config.AANTAL_RIJEN_PER_VAK; i++) {
			addRow(Config.AANTAL_STOELEN_PER_RIJ);
		}
	}

	/**
	 * Creates a Section with a number of rows and a number of seats for a row.
	 * 
	 * @param nrOfRows
	 *            the number of rows the section will have
	 * @param nrOfSeatsPerRow
	 *            the number of seats a row will have
	 */
	public Section(int nrOfRows, int nrOfSeatsPerRow) {
		for (int i = 0; i < nrOfRows; i++) {
			addRow(nrOfSeatsPerRow);
		}
	}

	/**
	 * Adds a row to the section with a number of seats.
	 * 
	 * @param nrOfSeats
	 *            the amount of seats the added row is suppossed to have
	 */
	public void addRow(int nrOfSeats) {
		rows.add(new Row(nrOfSeats));
	}

	/**
	 * Checks if the section can reserve for an amount of people in a row
	 * 
	 * @param nrOfPersons
	 * @return
	 */
	public boolean canReserve(int nrOfPersons) {
		for (Row r : rows) {
			if (r.hasSeatsInARow(nrOfPersons)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Reserves for a number of people.
	 * 
	 * @param nrOfPersons
	 *            the number of people a reservation needs to be made for
	 * @param userId
	 *            the is of the person that is reserving the seats
	 * @param sectionName
	 *            the name of this section
	 * @return the reservation, returns null if the reservation couldn't be made
	 */
	public Reservation reserve(int nrOfPersons, int userId, String sectionName) {
		int rowNumber = 0;
		for (Row r : rows) {
			List<Integer> seatNumbers = r.getSeats(nrOfPersons, userId);
			if (seatNumbers != null) {
				Reservation reservation = new Reservation(rowNumber, seatNumbers, userId, sectionName);
				return reservation;
			}
			rowNumber++;
		}
		return null;
	}

	/**
	 * Cancels a reservation.
	 * 
	 * @param reservation
	 *            the reservation that needs to be cancelled
	 */
	public void cancelReservation(Reservation reservation) {
		assert reservation.getRowNumber() < rows.size() : "invalid row";
		Row r = rows.get(reservation.getRowNumber());
		for (Integer i : reservation.getSeatNumbers()) {
			Seat s = r.getSeat(i);
			assert s.isReserved() : "this seat is supposed to be reserved";
			s.unReserve();
		}
	}

	/**
	 * Sets the seats for a reservation as payed.
	 * 
	 * @param reservation
	 *            the reservation that needs to be confirmed a payed
	 */
	public void confirmPayed(Reservation reservation) {
		assert reservation.getRowNumber() < rows.size() : "invalid row";
		Row r = rows.get(reservation.getRowNumber());
		for (Integer i : reservation.getSeatNumbers()) {
			Seat s = r.getSeat(i);
			assert s.isReserved() : "this seat is supposed to be reserved";
			assert s.getCustomerId() == reservation.getUserId() : "this seat is supossed to have the same user id for the reservation";
			s.payedConfirmation();
		}
	}

	/**
	 * Generates a String representation of the sections bought seats the first line represents the seat numbers
	 * 
	 * @return a String representation of the section
	 */
	public String stringSection() {
		StringBuilder b = new StringBuilder();
		if (rows.size() != 0) {
			b.append("S<");
			int idSize = (Config.AANTAL_KLANTEN + "").length();
			for (int i = 0; i < rows.get(0).getNumberOfSeats(); i++) {
				String number = i + "";
				while (number.length() != idSize) {
					number = " " + number;
				}
				b.append("[" + number + "]");
			}
			b.append(">\n");
		}
		int index = 0;
		for (Row r : rows) {
			b.append(index + r.stringRow() + "\n");
			index++;
		}
		return b.toString();
	}

}
