package router;

import java.util.HashMap;
import java.util.Map;

import agents.DistributionAgent;
import agents.SectionAgent;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import messages.TicketRequest;
import section.Section;

public class MyRouter extends UntypedActor {

	private ActorRef router;

	/**
	 * Creates a router with a number of distribution agents.
	 * 
	 * @param nrOfDistributionAgents
	 *            the number of Distribution agents
	 */
	public MyRouter(int nrOfDistributionAgents) {

		Map<String, ActorRef> sectionAgents = new HashMap<String, ActorRef>();
		// creating all the section agents and adds them to a hashmap
		ActorRef floor = getContext().actorOf(Props.create(SectionAgent.class, Section.FLOOR), Section.FLOOR);
		sectionAgents.put(Section.FLOOR, floor);

		ActorRef firstRingNorth = getContext().actorOf(Props.create(SectionAgent.class, Section.FIRST_RING_NORTH), Section.FIRST_RING_NORTH);
		sectionAgents.put(Section.FIRST_RING_NORTH, firstRingNorth);

		ActorRef firstRingSouth = getContext().actorOf(Props.create(SectionAgent.class, Section.FIRST_RING_SOUTH), Section.FIRST_RING_SOUTH);
		sectionAgents.put(Section.FIRST_RING_SOUTH, firstRingSouth);

		ActorRef firstRingWest = getContext().actorOf(Props.create(SectionAgent.class, Section.FIRST_RING_WEST), Section.FIRST_RING_WEST);
		sectionAgents.put(Section.FIRST_RING_WEST, firstRingWest);

		ActorRef secondRingNorth = getContext().actorOf(Props.create(SectionAgent.class, Section.SECOND_RING_NORTH), Section.SECOND_RING_NORTH);
		sectionAgents.put(Section.SECOND_RING_NORTH, secondRingNorth);

		ActorRef secondRingSouth = getContext().actorOf(Props.create(SectionAgent.class, Section.SECOND_RING_SOUTH), Section.SECOND_RING_SOUTH);
		sectionAgents.put(Section.SECOND_RING_SOUTH, secondRingSouth);

		ActorRef secondRingWest = getContext().actorOf(Props.create(SectionAgent.class, Section.SECOND_RING_WEST), Section.SECOND_RING_WEST);
		sectionAgents.put(Section.SECOND_RING_WEST, secondRingWest);

		router = getContext().actorOf(new RoundRobinPool(nrOfDistributionAgents).props(Props.create(DistributionAgent.class, sectionAgents)),"DistributionAgent");
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if (msg instanceof TicketRequest) {
			router.tell(msg, getSender());
		}
	}
}
