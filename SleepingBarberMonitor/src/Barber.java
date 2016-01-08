	class Barber extends Thread{
		private Barbershop barbershop;
		private Customer customer;
		
		public Barber(Barbershop theShop){
			barbershop = theShop;
		}
		public void run(){
			while (true) {
				try {
					customer = barbershop.nextCustomer();				
					cut();
					barbershop.showOut(customer);
				} catch (InterruptedException e){}
			}
		}
		private void cut(){
			try {
				System.out.println("barber cuts " + customer.getCustomerName());
				Thread.sleep((int)(Math.random() * 100));
			} catch (InterruptedException e) {}			
		}
	}
	