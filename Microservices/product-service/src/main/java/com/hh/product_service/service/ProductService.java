package com.hh.product_service.service;

import com.hh.product_service.dto.ProductRequest;
import com.hh.product_service.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    public ProductResponse createProduct(ProductRequest productRequest);
    public ProductResponse getProductById(String id);
    public void deleteProductById(String id);
    public ProductResponse updateProductById(String id, ProductRequest productRequest);
    public List<ProductResponse> getAllProducts();
    public ProductResponse getProductBySku(String skuCode);
}
