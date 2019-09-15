package com.springMvc.shoppingCart.messageQueue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.springMvc.shoppingCart.entities.ProductEntity;

@Component	
public class OrderReceiver {
	
	
	/*
	 * @Autowired private OrderRepository orderRepository;
	 * 
	 * 
	 */
	
	private int count = 1;
	  
	  @JmsListener(destination = "OrderQueue", containerFactory = "myFactory")
	 public void receiveOrderMessage(ProductEntity productEntity) { 
		  //this will print product entity object by default calling "toString()" method of ProductEntity.class 
		  System.out.println("<" + count + "> Received <" +productEntity + ">"); //orderRepository.save(productEntity2); 
	 }
	

}
