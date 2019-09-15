package com.springMvc.shoppingCart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springMvc.shoppingCart.entities.ItemEntity;
import com.springMvc.shoppingCart.entities.ProductEntity;
import com.springMvc.shoppingCart.models.ListOfProductsModel;

@Controller
@RequestMapping(value="item")
public class ItemController {
	
	//autowire by name/type
	@Autowired
	private ListOfProductsModel listOfProductsModel;
	
	@Autowired 
	private JmsTemplate jmsTemplate;
	
	@RequestMapping(value = "/Selection", method = RequestMethod.GET)
	public String index() {
		return "item/Selection";
	}
	
	
	@RequestMapping(value = "/buy/{productId}", method = RequestMethod.GET)
	public String buy(@PathVariable("productId") String productId, HttpSession session) {
		//ListOfProductsModel listOfProductsModel = new ListOfProductsModel();
		List<ItemEntity> itemEntityList = new ArrayList<ItemEntity>();
		
		if (session.getAttribute("item") == null) {
			itemEntityList = new ArrayList<ItemEntity>();
			
			ProductEntity productEntity = listOfProductsModel.getProductById(productId);
			ItemEntity itemEntity = new ItemEntity(productEntity,1);
			itemEntityList.add(itemEntity);
			
			session.setAttribute("item", itemEntityList);
		} else {
			itemEntityList = (List<ItemEntity>) session.getAttribute("item");
			
			//verify whether item is already exist or not
			int index = this.ifProductExists(productId, itemEntityList);
			if (index == -1) {
				ProductEntity productEntity = listOfProductsModel.getProductById(productId);
				ItemEntity itemEntity = new ItemEntity(productEntity,1);
				itemEntityList.add(itemEntity);
			} else {
				int quantity = itemEntityList.get(index).getProductQuantity() + 1;
				itemEntityList.get(index).setProductQuantity(quantity);
			}
			session.setAttribute("item", itemEntityList);
		}
		return "redirect:/item/Selection";
	}
	
	
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.GET)
	public String remove(@PathVariable("productId") String productId, HttpSession session) {
		ListOfProductsModel listOfProductsModel = new ListOfProductsModel();
		List<ItemEntity> itemEntityList = (List<ItemEntity>) session.getAttribute("item");
		int index = this.ifProductExists(productId, itemEntityList);
		itemEntityList.remove(index);
		session.setAttribute("item", itemEntityList);
		return "redirect:/item/Selection";
	}
	
	
	private int ifProductExists(String id, List<ItemEntity> itemEntityList) {
		for (int i = 0; i < itemEntityList.size(); i++) {
			if (itemEntityList.get(i).getProduct().getProductId().equalsIgnoreCase(id)) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	@RequestMapping(value="/sendMsg/{productId}", method = RequestMethod.GET)    
    public String edit(@PathVariable String productId, Model m, HttpSession session){    
		System.out.println("### sending Message ["+productId+"] ###");
		ProductEntity productEntity = listOfProductsModel.getProductById(productId);
		// Post message to the message queue named "OrderTransactionQueue"
	    jmsTemplate.convertAndSend("OrderQueue", productEntity);
		/*
		 * m.addAttribute("command",productEntity); return "empeditform";
		 */
	    
	    List<ItemEntity> itemEntityList = new ArrayList<ItemEntity>();
		if (session.getAttribute("item") == null) {
			itemEntityList = new ArrayList<ItemEntity>();
			ItemEntity itemEntity = new ItemEntity(productEntity,1);
			itemEntityList.add(itemEntity);
			
			session.setAttribute("item", itemEntityList);
		} else {
			itemEntityList = (List<ItemEntity>) session.getAttribute("item");
			
			//verify whether item is already exist or not
			int index = this.ifProductExists(productId, itemEntityList);
			if (index == -1) {
				ItemEntity itemEntity = new ItemEntity(productEntity,1);
				itemEntityList.add(itemEntity);
			} else {
				int quantity = itemEntityList.get(index).getProductQuantity() + 1;
				itemEntityList.get(index).setProductQuantity(quantity);
			}
			session.setAttribute("item", itemEntityList);
		}
	    
	    return "redirect:/item/Selection";
    }    
	
	@PostMapping("/sendMsg1")
	public void sendMsg(@PathVariable("productId") String productId, HttpSession session) {
		System.out.println("### sendMsg... ###");
		ProductEntity productEntity = listOfProductsModel.getProductById(productId);
		// Post message to the message queue named "OrderTransactionQueue"
	    jmsTemplate.convertAndSend("OrderQueue", productEntity);
		
	}
	

	@RequestMapping(value = "/sendMsg2", method = RequestMethod.POST) 
	public @ResponseBody ProductEntity sendMsg(@RequestParam("productId") String productId) { 
		System.out.println("### sendMsg... ###");
		ProductEntity productEntity = listOfProductsModel.getProductById(productId);
		return productEntity;
		
	}
	
	

}
