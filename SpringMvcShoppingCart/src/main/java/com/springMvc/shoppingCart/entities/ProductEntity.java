package com.springMvc.shoppingCart.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ProductEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String productId;
	
	private String productName;
	private String productPhoto;
	private Double productPrice;
	
	
	public ProductEntity() {
		super();
	}

	public ProductEntity(String productId, String productName, String productPhoto, Double productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPhoto = productPhoto;
		this.productPrice = productPrice;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPhoto() {
		return productPhoto;
	}
	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "ProductEntity2 [productId=" + productId + ", productName=" + productName + ", productPhoto="
				+ productPhoto + ", productPrice=" + productPrice + "]";
	}

	
	
	
	
	
	
	

}
