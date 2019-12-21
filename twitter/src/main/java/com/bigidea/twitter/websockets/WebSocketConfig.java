package com.bigidea.twitter.websockets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketMessageBrokerConfigurer {
    //Server end point
    //URL: http://localhost:8090/ws/
    // *: Allows server requests from any origin
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("[SERVER] - Inside register");
        registry.addEndpoint( "/ws").setAllowedOrigins("*").withSockJS();
    }

    //Will make our URL to send to :  http://localhost:8081/app/
    //Only have 1 subscription  right now and that is /chat
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Bean
    @Scope("singleton")
    public LogicHandler singletonLogicHandler(){
        return new LogicHandler();
    }

}
