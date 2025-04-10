package com.hh.product_service.repository;

import com.hh.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    // Custom query methods can be defined here if needed
}
