package opdracht2;

import java.util.concurrent.Semaphore;

public class Company {

	private Semaphore problem, invitation, userConsultation, developerConsultation, report, readyForConversation, available, consulting;
	private SoftwareProgrammer[] softwareProgrammers;
	private ProductOwner productOwner;
	private User[] users;

	public Company() {
		softwareProgrammers = new SoftwareProgrammer[5];
		users = new User[8];
		productOwner = new ProductOwner();

		problem = new Semaphore(0, true);
		invitation = new Semaphore(0, true);
		userConsultation = new Semaphore(0, true);
		developerConsultation = new Semaphore(0, true);
		report = new Semaphore(0, true);
		readyForConversation = new Semaphore(0, true);
		available = new Semaphore(0, true);
		consulting = new Semaphore(0, true);		

		productOwner.start();

		for (int i = 0; i < softwareProgrammers.length; i++) {
			softwareProgrammers[i] = new SoftwareProgrammer();
			softwareProgrammers[i].start();
		}

		for (int i = 0; i < users.length; i++) {
			users[i] = new User();
			users[i].start();
		}

	}

	class User extends Thread {
		@Override
		public void run() {
			setName("user");
			while (true) {
				try {
					problem.release();
					System.out.println("problem released");
					invitation.acquire();
					System.out.println("invitation acquired");
					travel();
					readyForConversation.release();
					userConsultation.acquire();
					consult();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private void travel() {
			try {
				System.out.println(getName() + " traveling");
				Thread.sleep((int) (Math.random() * 1000));
				System.out.println(getName() + " done traveling");
			} catch (InterruptedException e) {
			}
		}
		
		private void consult() {
			try {
				System.out.println(getName() + " consulting");
				Thread.sleep((int) (Math.random() * 1000));
				System.out.println(getName() + " done consulting");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class SoftwareProgrammer extends Thread {
		@Override
		public void run() {
			setName("programmer");
			while (true) {
				try {
					available.release();
					if(consulting.availablePermits() == 0 && available.availablePermits() <= 1) {
						System.out.println("Programmer waiting for invitation....");
						invitation.acquire();
						System.out.println(".....Programmer invitatino acquired");
						readyForConversation.release();
						userConsultation.acquire();
						consult();
					}
					available.acquire();
					work();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private void work() {
			try {
				System.out.println(getName() + " working");
				Thread.sleep((int) (Math.random() * 1000));
				System.out.println(getName() + " done working");
			} catch (InterruptedException e) {
			}
		}
		
		private void consult() {
			try {
				System.out.println(getName() + " consulting");
				Thread.sleep((int) (Math.random() * 1000));
				System.out.println(getName() + " done consulting");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	class ProductOwner extends Thread {
		@Override
		public void run() {
			setName("productowner");
			while (true) {
				try {
					problem.acquire();
					System.out.println("Problem acquired");
					available.acquire();
					System.out.println("Available acquired");
					int permits = problem.availablePermits() + 1;
					System.out.println("Permits drained: " + problem.drainPermits());
					invitation.release(permits);
					System.out.println(invitation.getQueueLength());
					System.out.println("Invitations released: " + permits);
					readyForConversation.acquire(permits);
					userConsultation.release(permits);
					consulting.release();
					
					consult();
					
					consulting.acquire();
					available.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void consult() {
			try {
				System.out.println(getName() + " consulting");
				Thread.sleep((int) (Math.random() * 1000));
				System.out.println(getName() + " done consulting");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}