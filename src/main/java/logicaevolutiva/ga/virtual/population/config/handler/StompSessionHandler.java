package logicaevolutiva.ga.virtual.population.config.handler;

import java.lang.reflect.Type;

import org.springframework.messaging.Message;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import logicaevolutiva.ga.virtual.population.controller.HelloMessage;

public class StompSessionHandler extends StompSessionHandlerAdapter {

	@Override
	public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
		// logger.info("New session established : " + session.getSessionId());
		session.subscribe("/topic/greetings", this);
		// logger.info("Subscribed to /topic/messages");
		session.send("/app/hello", getSampleMessage());
		// logger.info("Message sent to websocket server");
	}

	    @Override
	    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
	    	System.out.println(exception);
	       //logger.error("Got an exception", exception);
	    }

	    @Override
	    public Type getPayloadType(StompHeaders headers) {
	        return Message.class;
	    }

	    @Override
	    public void handleFrame(StompHeaders headers, Object payload) {
	        Message msg = (Message) payload;
	   //     logger.info("Received : " + msg.getText() + " from : " + msg.getFrom());
	    }

	    /**
	     * A sample message instance.
	     * @return instance of <code>Message</code>
	     */
	    private HelloMessage getSampleMessage() {
	    	HelloMessage msg = new HelloMessage();
	        msg.setName("Aiello");
	        return msg;
	    }

}
