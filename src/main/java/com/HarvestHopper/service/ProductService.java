package com.HarvestHopper.service;

import com.HarvestHopper.model.Product;

import java.util.List;

public interface ProductService {
//    Product createProduct(Product product);
    Product createProduct(Product product);
    List<Product> createProducts(List<Product>products);
    Product updateProductById(long id, Product product);
    void deleteProductById(long id);
    Product getProductById(long id);
    List<Product> getAllProducts();
    List<Product> searchProductByName(String keyword);
    List<Product> getProductsByCategoryId(Long categoryId);
}
