package com.springMvc.shoppingCart;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
//@ComponentScan(basePackages = "com.springMvc.shoppingCart")
@ComponentScan( basePackages = { "com.springMvc.shoppingCart", "org.springframework.jms" ," org.springframework.jms.core"} )
@EnableMongoRepositories(basePackages = "com.springMvc.shoppingCart")
@SpringBootApplication
@EnableEurekaServer	// Enable eureka server
public class SpringMvcShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcShoppingCartApplication.class, args);
	}
	
	
	// Only required due to defining myFactory in the receiver
	  @Bean
	  public JmsListenerContainerFactory<?> myFactory(
			  ConnectionFactory connectionFactory
			  ,DefaultJmsListenerContainerFactoryConfigurer configurer) {
		  
	    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	    configurer.configure(factory, connectionFactory);
	    return factory;
	    
	  }
	  
	// Serialize message content to json using TextMessage
	  @Bean
	  public MessageConverter jacksonJmsMessageConverter() {
	    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	    converter.setTargetType(MessageType.TEXT);
	    converter.setTypeIdPropertyName("_type");
	    return converter;
	  }
	  

}
