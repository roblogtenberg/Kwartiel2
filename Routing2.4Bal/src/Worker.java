import akka.actor.UntypedActor;

public class Worker extends UntypedActor{
	private static int VolgNr =0;
	private String identity ;
	public Worker (String name){
		identity = name + VolgNr++;
	}
	
	public void onReceive(Object message){
		if ( message instanceof Work){
			System.out.println("" + identity +" " + ((Work) message).payload);
		} else {
			System.out.println("" + identity +" " + "kan boodschap niet verwerken");
			unhandled(message);
		}
	}
}
