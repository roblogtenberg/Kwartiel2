import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.BalancingPool;

public class Master extends UntypedActor {
  
  ActorRef router;
  {
	  router = getContext().actorOf(new BalancingPool(5).props(Props.create(Worker.class,"tja")),"router");
  }
 
  public void onReceive(Object msg) {
    if (msg instanceof Work) {
      router.tell(msg, getSender());
    }
  }
}
