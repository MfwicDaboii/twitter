package com.bigidea.twitter;

import com.bigidea.twitter.websockets.LogicHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
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
