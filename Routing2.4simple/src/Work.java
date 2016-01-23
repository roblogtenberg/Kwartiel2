
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.netty.channel.socket.Worker;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

/* specifies the work to be done */
		
public final class Work implements Serializable {
  private static final long serialVersionUID = 1L;
  public final String payload;
  public Work(String payload) {
    this.payload = payload;
  }
}
 



