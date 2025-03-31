package com.HarvestHopper.controller;

import com.HarvestHopper.model.Category;
import com.HarvestHopper.model.Product;
import com.HarvestHopper.service.CategoryService;
import com.HarvestHopper.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

//    @PostMapping
//    ResponseEntity<Product> createProduct(@RequestBody Product product){
//       Product createdProduct =  productService.createProduct(product);
//       return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//    }
@PostMapping
public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product createdProduct = productService.createProduct(product);
    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdProduct.getProduct_id())
            .toUri();
    return ResponseEntity.created(location).body(createdProduct);
}

    @PostMapping("/batch")
    ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products){
        List<Product> createProducts= productService.createProducts(products);
        return new ResponseEntity<>(createProducts,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteProductById(@PathVariable long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>("Product deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity <Product> findProductById(@PathVariable long id){
        Product productById = productService.getProductById(id);
        return new ResponseEntity<>(productById,HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity <List<Product>> showAllProducts(){
       List<Product> allProducts=  productService.getAllProducts();
       return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

    @GetMapping("/search")
    ResponseEntity<List<Product>> searchProductByName(@RequestParam String keyword){
       List<Product> productsByName =  productService.searchProductByName(keyword);
        return new ResponseEntity<>(productsByName,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Product> updateExistingProductById(@PathVariable long id ,@RequestBody Product Product){
        Product updatedProduct= productService.updateProductById(id,Product);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable long id) {
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);

        if (!categoryOptional.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Category with ID " + id + " not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        List<Product> products = productService.getProductsByCategoryId(id);

        if (products.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No products found for category ID " + id);
            return new ResponseEntity<>(response, HttpStatus.OK);  // 200 OK with message
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
