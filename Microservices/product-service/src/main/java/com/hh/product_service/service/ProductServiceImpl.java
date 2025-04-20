package com.hh.product_service.service;

import com.hh.product_service.dto.ProductRequest;
import com.hh.product_service.dto.ProductResponse;
import com.hh.product_service.exception.ProductNotFoundException;
import com.hh.product_service.model.Product;
import com.hh.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .imageUrl(productRequest.imageUrl())
                .skuCode(productRequest.skuCode())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);
        log.info("Product created: {}", product);
        return new ProductResponse(String .valueOf(product.getId()),
                product.getName(),
                product.getDescription(),
                product.getSkuCode(),
                product.getPrice());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getSkuCode(),
                        product.getDescription(),
                        product.getPrice()))
                .toList();
    }

    @Override
    public ProductResponse getProductBySku(String skuCode) {
        Product product = productRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with SKU code: " + skuCode));
        log.info("Product found by SKUCode: {}", product);
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSkuCode(),
                product.getDescription(),
                product.getPrice());
    }

    @Override
    public void deleteProductById(String id){
        if(!productRepository.existsById(id)) {
            log.warn("Product with id {} not found", id);
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        log.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        log.info("Product found: {}", product);
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSkuCode(),
                product.getDescription(),
                product.getPrice());
    }

    @Override
    public ProductResponse updateProductById(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        product.setName(productRequest.name());
        product.setImageUrl(productRequest.imageUrl());
        product.setSkuCode(productRequest.skuCode());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        productRepository.save(product);
        log.info("Product updated: {}", product);
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getSkuCode(),
                product.getDescription(),
                product.getPrice());
    }
}
