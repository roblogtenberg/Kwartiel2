package section;

public class Seat {
	private boolean reserved = false;
	private int customerId = -1;
	private boolean payed = false;

	public void reserve(int customerId) {
		reserved = true;
		this.customerId = customerId;
	}

	public void payedConfirmation() {
		assert reserved : "a seat also needs to be reserved when bought";
		assert customerId != -1 : "this seat needs to have a valid user id";
		payed = true;
	}

	public void unReserve() {
		reserved = false;
		this.customerId = -1;
	}

	public boolean isReserved() {
		return reserved;
	}

	public boolean IsPayed() {
		return payed;
	}

	public int getCustomerId() {
		return customerId;
	}
}
