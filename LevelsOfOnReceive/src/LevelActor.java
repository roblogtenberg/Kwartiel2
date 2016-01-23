import akka.actor.UntypedActor;

import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;

/**
 * 
 * @author Jan Stroet
 * Inspired by Akka user guide 2.3.9
 * 
 *  Receiving a next-message will result in pushing a new receive-version
 *  receiving a prev-message will result in popping the last receive-level, if possible
 * 
 *
 */

public class LevelActor extends UntypedActor{ 
	LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	/* when active on a next message the next level will be POPPED */
	
	Procedure<Object>first=new Procedure<Object>(){
		@Override 
		public void apply(Object message){ 
			if(message.equals("hello")){ 
				log.debug("first received hello");
			}else if(message.equals("next")){
				log.debug("first received next");
				log.debug("becoming second");
				getContext().become(second,false); /*stack!*/
			}else if(message.equals("prev")){
				log.debug("first received prev");
				log.debug("popping");
				getContext().unbecome();
			}else if(message.equals("first")){
				log.debug("first received message first");
			} else{
				log.debug("unknown message " + (String) message);
				unhandled(message);
			}
		}
	}; 
	
	/* when active on a next message it will be REPLACED by third */
	
	Procedure<Object>second=new Procedure<Object>(){
		@Override 
		public void apply(Object message){ 
			if(message.equals("hello")){ 
				log.debug("second received hello");

			}else if(message.equals("next")){
				log.debug("second received next");
				log.debug("becoming third");
				getContext().become(third,true); /*replace*/
			}else if(message.equals("prev")){
				log.debug("second received prev");
				log.debug("popping");
				getContext().unbecome(); /*stack?*/
			} else{
				log.debug("unknown message " + (String) message);
				unhandled(message);
			}
		}
	}; 
	
	/* when active on a next message it will do nothing: NOOP */
	
	Procedure<Object>third=new Procedure<Object>(){
		@Override 
		public void apply(Object message){ 
			if(message.equals("hello")){ 
				log.debug("third received hello");
			}else if(message.equals("next")){
				log.debug("third received next");
				log.debug("I will remain myself; no next level");			
			}else if(message.equals("prev")){
				log.debug("third received prev");
				log.debug("popping");
				getContext().unbecome();
			} else{
				log.debug("unknown message " + (String) message);
				unhandled(message);
			}
		}
	}; 
	
	public void onReceive(Object message){
		if(message.equals("hello")){ 
			log.debug("original received hello");
		}else if(message.equals("next")){
			log.debug("original received next");
			log.debug("becoming first");
			getContext().become(first, true);		
		}else if(message.equals("prev")){
			log.debug("I can not pop but I try");
			log.debug("popping");
			getContext().unbecome(); /*stack?*/
		} else{
			log.debug("unknown message " + (String) message);
			unhandled(message);
		}
	}
}
