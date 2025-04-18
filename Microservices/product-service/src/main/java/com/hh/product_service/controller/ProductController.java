package com.hh.product_service.controller;

import com.hh.product_service.dto.ProductRequest;
import com.hh.product_service.dto.ProductResponse;
import com.hh.product_service.model.Product;
import com.hh.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    //Create product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    //Get all products
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    //Delete product by id
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductById(@PathVariable String id){
        productService.deleteProductById(id);
        return "Product with id " + id + " deleted successfully";
    }

    //Get product by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    //Update product by id
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse updateProductById(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        return productService.updateProductById(id, productRequest);
    }

}
