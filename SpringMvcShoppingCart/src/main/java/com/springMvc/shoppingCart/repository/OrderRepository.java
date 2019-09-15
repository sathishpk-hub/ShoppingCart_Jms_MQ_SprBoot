package com.springMvc.shoppingCart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springMvc.shoppingCart.entities.ProductEntity;

public interface OrderRepository extends MongoRepository<ProductEntity, String> {

}
