	class Customer extends Thread{

		private Barbershop barbershop;
		public Customer(String name, Barbershop theShop){
			super(name);
			barbershop = theShop;
		}
		public void run(){
			while (true) {	
				try{
					justLive();
					barbershop.getHaircut();
				} catch (InterruptedException e){};
			}
		}
		
		private void justLive(){
			try {
				System.out.println(getName() + " living");
				Thread.sleep((int)(Math.random() * 1000));
			} catch (InterruptedException e) {}
			
		}
		
		public String getCustomerName(){
			return getName();
		}
	}
