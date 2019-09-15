package com.springMvc.shoppingCart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springMvc.shoppingCart.entities.ProductEntity;
import com.springMvc.shoppingCart.models.ListOfProductsModel;

//@RestController
@Controller
@RequestMapping(value="product")
public class ProductController {
	
	//adding logger statement
	private  Logger logger = LoggerFactory.getLogger( ProductController.class.getName() );
	
	//autowire by name/type
	@Autowired
	private ListOfProductsModel listOfProductsModel;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public String showAllProducts1(ModelMap modelMap) {
		logger.info("### Initially load all the inventories... ###");
		
		//ListOfProductsModel listOfProductsModel = new ListOfProductsModel();
		modelMap.put("listProducts", listOfProductsModel.getListOfProducts());
		return "product/ListInventory";
		
	}
	
	
	@RequestMapping(value="/product1",method=RequestMethod.GET)
	public ModelAndView showAllProducts(ModelMap modelMap) {
		logger.info("### Initially load all the inventories... ###");
		List<ProductEntity> listOfProducts = listOfProductsModel.getListOfProducts();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "";
		try {
			json = mapper.writeValueAsString(listOfProducts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//ListOfProductsModel listOfProductsModel = new ListOfProductsModel();
		ModelAndView model = new ModelAndView("product/ListInventory");
		model.addObject("listProducts", json);
		
		return model;
		
	}
	
	
	@RequestMapping(value="/product2",method=RequestMethod.GET)
	public List<ProductEntity> showAllProducts2(ModelMap modelMap) {
		logger.info("### Initially load all the inventories... ###");
		
		//ListOfProductsModel listOfProductsModel = new ListOfProductsModel();
		return listOfProductsModel.getListOfProducts();
		
	}

}
