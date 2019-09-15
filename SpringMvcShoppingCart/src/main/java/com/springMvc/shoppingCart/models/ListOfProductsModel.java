package com.springMvc.shoppingCart.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.springMvc.shoppingCart.entities.ProductEntity;


@Service
public class ListOfProductsModel {
	
	private List<ProductEntity> productList;
	
	public ListOfProductsModel() {
		super();
	}
	
	/*
	 * creating ListOfProductsModel object only one time
	 * assiging value to productList, only one time after created object.
	 */
	
	@PostConstruct
	public void initializeProducts() {
		productList = new ArrayList<ProductEntity>();
		
		//public ProductEntity(String productId, String productName, String productPhoto, Double productPrice) {
		ProductEntity iphone = new ProductEntity("1","Apple IPhone 8 plus","iphone_8_plus.png", 1990D);
		ProductEntity samsung = new ProductEntity("2","Samsung Note 10","samsung_note_10.jpg", 1700D);
		ProductEntity huawei = new ProductEntity("3","Huawei P30 Pro","huawei.jpg", 1500D);
		
		productList.add(iphone);
		productList.add(samsung);
		productList.add(huawei);
		
	}
	
	
	//return all the shopping cart products
	public List<ProductEntity> getListOfProducts(){
		return this.productList;
		
	}
	
	//return particular shopping cart product by productId
	public ProductEntity getProductById(String productId) {
		for(ProductEntity productEntity: this.productList) {
			if(productEntity.getProductId().equalsIgnoreCase(productId)) {
				return productEntity;
			}
		}
		return null;
		
	}

	public List<ProductEntity> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductEntity> productList) {
		this.productList = productList;
	}
	
	
	
	

}
