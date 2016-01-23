import akka.actor.UntypedActor;
/*
 * A worker, doing its job according to the message received
 * 
 * This worker only can print a payload-message
*/
 
public class Worker extends UntypedActor{
	private String identity ;
	public Worker (String name){
		identity = name;
	}
	
	public void onReceive(Object message){
		if ( message instanceof Work){
			/* simple work: just printing the payload*/
			System.out.println("" + identity +" " + ((Work) message).payload);
		} else {
			System.out.println("" + identity +" " + "kan boodschap niet verwerken");
			unhandled(message);
		}
	}
}
