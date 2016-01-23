
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class World {

	public static void main(String[] args) throws Exception {

		ActorSystem system = ActorSystem.create("Routing");
		
		ActorRef master = system.actorOf(Props.create(Master.class),"Master");

		for ( int i= 0; i<25; i++){
			
			master.tell(new Work("portie "+ i), null);
			
		}
		Thread.sleep(1000);		
		system.terminate();
	}
}
