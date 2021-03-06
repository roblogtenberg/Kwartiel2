import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinPool;
import akka.routing.Router;

public class Master extends UntypedActor {
  
  ActorRef router;
  {
//    List<Routee> routees = new ArrayList<Routee>();
//    for (int i = 0; i < 5; i++) {
//      ActorRef r = getContext().actorOf(Props.create(Worker.class, "id"+i));
//      getContext().watch(r);
//      routees.add(new ActorRefRoutee(r));
//    }
//    router = new Router(new RoundRobinRoutingLogic(), routees);
	  router = getContext().actorOf(new RoundRobinPool(5).props(Props.create(Worker.class,"tja")),"router");
  }
 
  public void onReceive(Object msg) {
    if (msg instanceof Work) {
      router.tell(msg, getSender());
//    } else if (msg instanceof Terminated) {
//      router = router.removeRoutee(((Terminated) msg).actor());
//      ActorRef r = getContext().actorOf(Props.create(Worker.class));
//      getContext().watch(r);
//      router = router.addRoutee(new ActorRefRoutee(r));
    }
  }
}
