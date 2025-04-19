package com.hh.product_service.controller;

import com.hh.product_service.dto.ProductRequest;
import com.hh.product_service.dto.ProductResponse;
import com.hh.product_service.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    //Create product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productServiceImpl.createProduct(productRequest);
    }

    //Get all products
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productServiceImpl.getAllProducts();
    }

    //Delete product by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductById(@PathVariable String id){
        productServiceImpl.deleteProductById(id);
        return "Product with id " + id + " deleted successfully";
    }

    //Get product by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable String id) {
        return productServiceImpl.getProductById(id);
    }

    //Update product by id
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProductById(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        return productServiceImpl.updateProductById(id, productRequest);
    }

    //Get product by sku
    @GetMapping("/sku/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductBySku(@PathVariable String skuCode) {
        return productServiceImpl.getProductBySku(skuCode);
    }

}
