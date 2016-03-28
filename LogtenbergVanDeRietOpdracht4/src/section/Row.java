package section;

import java.util.ArrayList;
import java.util.List;

import configuration.Config;

public class Row {
	public final static int AANTAL_RIJEN_PER_VAK = 4;
	private ArrayList<Seat> seats = new ArrayList<>();

	public Row(int nrOfSeats) {
		for (int i = 0; i < nrOfSeats; i++) {
			seats.add(new Seat());
		}
	}

	public boolean hasSeatsInARow(int number) {
		int numberInArow = 0;
		for (Seat s : seats) {
			if (s.isReserved()) {
				numberInArow = 0;
			} else {
				numberInArow++;
			}
			if (numberInArow == number) {
				return true;
			}
		}
		return false;
	}

	public List<Integer> getSeats(int nrOfPersons, int customerId) {
		ArrayList<Integer> seatNumbers = new ArrayList<>();
		int numberInArow = 0;
		int index = 0;
		for (Seat s : seats) {
			if (s.isReserved()) {
				numberInArow = 0;
				seatNumbers.clear();
			} else {
				numberInArow++;
				seatNumbers.add(index);
			}
			if (numberInArow == nrOfPersons) {
				// reservation
				for (Integer i : seatNumbers) {
					assert !seats.get(i).isReserved() : "this seat is supposed to be free";
					assert !seats.get(i).IsPayed() : "this seat isn't supposed to be payed for";
					seats.get(i).reserve(customerId);
				}
				return seatNumbers;
			}
			index++;
		}
		return null;
	}

	public Seat getSeat(int seatNumber) {
		return seats.get(seatNumber);
	}

	public String stringRow() {
		StringBuilder b = new StringBuilder();
		b.append("<");
		int idSize = (Config.AANTAL_KLANTEN + "").length(); // for an
																// outlining
																// purpose
		for (Seat s : seats) {
			if (s.IsPayed()) {
				String id = s.getCustomerId() + "";
				while (id.length() != idSize) {
					id = "-" + id;
				}
				b.append("[" + id + "]");
			} else {
				String id = "";
				while (id.length() != idSize) {
					id = "X" + id;
				}
				b.append("[" + id + "]");
			}

		}
		b.append(">");
		return b.toString();
	}

	public int getNumberOfSeats() {
		return seats.size();
	}
}
