package opdracht2;

import java.util.concurrent.Semaphore;

public class Company {

	private Semaphore problem, invitation, userConsultation, developerConsultation, report, readyForConversation;
	private SoftwareProgrammer[] softwareProgrammers;
	private ProductOwner productOwner;
	private User[] users;

	public Company() {
		softwareProgrammers = new SoftwareProgrammer[5];
		users = new User[8];
		productOwner = new ProductOwner();
		productOwner.start();

		problem = new Semaphore(0, true);
		invitation = new Semaphore(0, true);
		userConsultation = new Semaphore(0, true);
		developerConsultation = new Semaphore(0, true);
		report = new Semaphore(0, true);

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
			while (true) {
				try {
					problem.release();
					invitation.acquire();
					travel();
					readyForConversation.release();
					userConsultation.acquire();
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
	}

	class SoftwareProgrammer extends Thread {
		@Override
		public void run() {
			while (true) {
				invitation.tryAcquire();
				work();
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
	}

	class ProductOwner extends Thread {
		@Override
		public void run() {
			while (true) {

			}
		}
	}
}