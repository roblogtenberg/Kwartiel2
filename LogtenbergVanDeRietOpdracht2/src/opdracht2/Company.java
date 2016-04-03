package opdracht2;


import java.util.concurrent.Semaphore;

public class Company {

	private Semaphore invitation, usersReadyForConversation, devReadyForConversation, developerRequestedMeetingRoom, inMeetingRoom, meetingDone, productOwnerCheck, mutexDevelopersWaiting, mutexEndUsersWithProblem;
	private SoftwareProgrammer[] softwareProgrammers;
	private ProductOwner productOwner;
	private User[] users;
	private int developersWaiting = 0, endUsersWithProblem = 0;
	private boolean meetingHappening;

	public Company() {
		softwareProgrammers = new SoftwareProgrammer[5];
		users = new User[2];
		productOwner = new ProductOwner();

		invitation = new Semaphore(0, true);
		usersReadyForConversation = new Semaphore(0, true);
		devReadyForConversation = new Semaphore(0, true);
		developerRequestedMeetingRoom = new Semaphore(0, true);
		inMeetingRoom = new Semaphore(0, true);
		meetingDone = new Semaphore(0, true);
		productOwnerCheck = new Semaphore(0, true);
		
		mutexDevelopersWaiting = new Semaphore(1, true);
		mutexEndUsersWithProblem = new Semaphore(1, true);

		productOwner.start();

		for (int i = 0; i < softwareProgrammers.length; i++) {
			softwareProgrammers[i] = new SoftwareProgrammer(i);
			softwareProgrammers[i].start();
		}

		for (int i = 0; i < users.length; i++) {
			users[i] = new User(i);
			users[i].start();
		}

	}

	class User extends Thread {
		
		private int id;
		
		public User(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			setName("user " + id);
			while (true) {
				try {
					mutexEndUsersWithProblem.acquire();
					endUsersWithProblem++;
					mutexEndUsersWithProblem.release();
					productOwnerCheck.release();
					invitation.acquire();
					travel();
					usersReadyForConversation.release();
					inMeetingRoom.release();
					consult();
					meetingDone.acquire();
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
		
		private int id;
		
		public SoftwareProgrammer(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			setName("programmer " + id);
			while (true) {
				try {
					work();
					if(productOwner.isAlive()) {
						if (!meetingHappening) {
							mutexDevelopersWaiting.acquire();
							developersWaiting++;
							mutexDevelopersWaiting.release();
							productOwnerCheck.release();
							devReadyForConversation.acquire();
	
							if (developerRequestedMeetingRoom.tryAcquire()) {
								inMeetingRoom.release();
								consult();
								meetingDone.acquire();
							}
						}
					}
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
					life();
					productOwnerCheck.acquire();
					if (endUsersWithProblem > 0) {
						if (developersWaiting > 0) {
							meetingHappening = true;
							mutexEndUsersWithProblem.acquire();
							final int endUsers = endUsersWithProblem;
							endUsersWithProblem = 0;
							mutexEndUsersWithProblem.release();
							// send invitations
							invitation.release(endUsers);
							// wait for users to arrive
							usersReadyForConversation.acquire(endUsers);
							mutexDevelopersWaiting.acquire();
							// get one developer
							developerRequestedMeetingRoom.release();
							// the rest of developers gets back to work
							devReadyForConversation.release(developersWaiting);
							developersWaiting = 0;
							mutexDevelopersWaiting.release();
							inMeetingRoom.acquire(endUsers + 1);
							consult();
							meetingDone.release(endUsers + 1);
							meetingHappening = false;
						}
					} else if (developersWaiting > 3) {
						meetingHappening = true;
						developerRequestedMeetingRoom.release(3);
						mutexDevelopersWaiting.acquire();
						devReadyForConversation.release(developersWaiting);
						developersWaiting = 0;
						mutexDevelopersWaiting.release();
						inMeetingRoom.acquire(3);
						consult();
						meetingDone.release(3);
						meetingHappening = false;
					}
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
		
		private void life() {
			try {
				System.out.println(getName() + " just living");
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}