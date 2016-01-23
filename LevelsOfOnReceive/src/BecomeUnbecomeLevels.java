
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * 
 * @author Jan Stroet
 * Inspired by Akka user guide 2.3.9
 * 
 * Testing become/unbecome: a mechanism to change behaviour of an actor by installing a new onReceive()
 * become (.., true) or become(...) replaces (overwrites) the current version of onReceive()
 * become(..., false) stacks the new version; you can return 
 * to the previous version with unbecome()
 * 
 * However the first new version is always stacked, 
 * independent of the second argument. 
 * 
 *
 */
public class BecomeUnbecomeLevels {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		
		ActorSystem _system = ActorSystem.create("Swapping");	
		ActorRef swapper = _system.actorOf(Props.create(LevelActor.class),"LevelActor");
	
		
		for( int i=0; i<4; i++){ /* push for times */
			swapper.tell("hello", null);
			swapper.tell("hello", null);
			swapper.tell("first", null);
			swapper.tell("next", null);			
		} 
		
		for( int i=0; i<4; i++){  /* pop 4 times */
			swapper.tell("hello", null);
			swapper.tell("prev", null);			
		} 
		swapper.tell("hello", null);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_system.terminate();
	}
}
