package logicaevolutiva.ga.virtual.population.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.RequestUpgradeStrategy;
import org.springframework.web.socket.server.standard.TomcatRequestUpgradeStrategy;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import logicaevolutiva.ga.virtual.population.websocket.client.EvolutionSessionHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	private static final Logger log = LoggerFactory.getLogger(WebSocketConfig.class);

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	/**
	 * Registrando o WebSocket evolution.	
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		
		RequestUpgradeStrategy upgradeStrategy = new TomcatRequestUpgradeStrategy();
		
		registry.addEndpoint("/evolution")
		        .withSockJS();

		registry.addEndpoint("/evolution")
		        .setHandshakeHandler(new DefaultHandshakeHandler(upgradeStrategy))
		        .setAllowedOrigins("*");
		  
	}

}	