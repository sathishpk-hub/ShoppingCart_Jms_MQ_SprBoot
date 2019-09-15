package com.springMvc.shoppingCart;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.springMvc.shoppingCart.entities.ProductEntity;
import com.springMvc.shoppingCart.models.ListOfProductsModel;

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringMvcShoppingCart1Application.class)
@WebAppConfiguration*/
@Configuration
@EnableAutoConfiguration
@ComponentScan( basePackages = { "com.springMvc.shoppingCart", "org.springframework.jms", " org.springframework.jms.core"} )
@RunWith(SpringRunner.class)
@WebMvcTest
public class SpringMvcShoppingCartApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	/*
	 * @Autowired private JmsTemplate jmsTemplate;
	 */
	
	@MockBean
    private ListOfProductsModel listOfProductsModel;

	/*
	 * @Autowired WebApplicationContext webApplicationContext;
	 * 
	 * protected void setUp() { mockMvc =
	 * MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); }
	 */
	   @Test
	   public void getProductsList() throws Exception {
	      String uri = "/product";
	      //MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
	      
	      ProductEntity iphone = new ProductEntity("1","Apple IPhone 8 plus","iphone_8_plus.png", 1990D);
	      List<ProductEntity> productEntityList=Arrays.asList(iphone);
	      
	      given(listOfProductsModel.getListOfProducts()).willReturn(productEntityList);
	      
	      // when + then
		/*
		 * this.mockMvc.perform(get("/product")) .andExpect(status().isOk())//;
		 * .andExpect(content().string("/product/ListInventory"));
		 */
	      
	      MvcResult mvcResult = this.mockMvc.perform(get("/product")).andReturn();
	      
	      String productResObject = mvcResult.getResponse().getContentAsString();
	      
	      String viewNameFromResponse = mvcResult.getModelAndView().getViewName();
	        
	      System.out.println("product result Object = "+productResObject);
	        
	      System.out.println("product result View Name = "+viewNameFromResponse);
	       
	      
	      //compare API using mock
	      assertEquals("/product/ListInventory", viewNameFromResponse);
	      
		/*
		 * int status = mvcResult.getResponse().getStatus();
		 * System.out.println("status = "+status); assertEquals(200, status); String
		 * content = mvcResult.getResponse().getContentAsString();
		 */
	     
	   }
	   
	   /*
	    * @RequestMapping(value="/sendMsg/{productId}", method = RequestMethod.GET)    
    		public String edit(@PathVariable String productId, Model m, HttpSession session){    
	    */
	   @Test
	   public void sendMsg() throws Exception {
	      
	      ProductEntity iphone = new ProductEntity("1","Apple IPhone 8 plus","iphone_8_plus.png", 1990D);
	      List<ProductEntity> productEntityList=Arrays.asList(iphone);
	
	     /*
		 * this.mockMvc.perform(get("/product")) .andExpect(status().isOk())//;
		 * .andExpect(content().string("/product/ListInventory"));
		 */
	      String productId="3";
	      MvcResult mvcResult = this.mockMvc.perform(get("/sendMsg/3")).andReturn();
	      
	      String productResObject = mvcResult.getResponse().getContentAsString();
	      
	      String viewNameFromResponse = mvcResult.getModelAndView().getViewName();
	        
	      System.out.println("product result Object = "+productResObject);
	        
	      System.out.println("product result View Name = "+viewNameFromResponse);
	       
	      
	      //compare API using mock
	      assertEquals("redirect:/item/Selection", viewNameFromResponse);
	      
		/*
		 * int status = mvcResult.getResponse().getStatus();
		 * System.out.println("status = "+status); assertEquals(200, status); String
		 * content = mvcResult.getResponse().getContentAsString();
		 */
	     
	   }
	   

}
