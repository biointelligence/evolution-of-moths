package com.evolutionofmoths.socket.config;

import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer{
	
	@Override
    protected boolean sameOriginDisabled() {
        return true;
    }

}
