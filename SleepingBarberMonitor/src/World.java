public class World {
	private static final int NR_OF_CUSTOMERS = 2;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Barbershop barbershop = new Barbershop();
		Thread [] customer;
		Thread barber;
		customer = new Thread[NR_OF_CUSTOMERS];
		
		for(int i = 0; i<NR_OF_CUSTOMERS; i++){
			customer[i] = new Customer ("c"+i, barbershop);
			customer[i].start();
		}
		barber = new Barber(barbershop);
		barber.start();

	}
}
