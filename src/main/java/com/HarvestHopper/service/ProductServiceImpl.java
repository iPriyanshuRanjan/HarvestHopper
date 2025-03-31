package com.HarvestHopper.service;

import com.HarvestHopper.exception.ProductNotFoundException;
import com.HarvestHopper.model.Category;
import com.HarvestHopper.model.Product;
import com.HarvestHopper.repository.CategoryRepository;
import com.HarvestHopper.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

//    @Override
//    public Product createProduct(Product product) {
//        return productRepository.save(product);
//    }

    @Override
    public Product createProduct(Product product) {
       if (product.getCategory()!=null && product.getCategory().getId()!=null){
           Category category= categoryRepository.findById(product.getCategory().getId()).orElseThrow(()->new ProductNotFoundException("Category with ID " + product.getCategory().getId() + " not found!"));
product.setCategory(category);
       } else {
           throw new IllegalArgumentException("Category ID is required.");
       }
        return productRepository.save(product);
    }

    @Override
    public List<Product> createProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product updateProductById(long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product with ID " + id + " not found!"));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setStock_quantity(product.getStock_quantity());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProductById(long id) {
        if (!productRepository.existsById(id)){
            throw new ProductNotFoundException("Product with ID " + id + " not found!");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(long id) {
      return productRepository.findById(id)
              .orElseThrow(()-> new ProductNotFoundException("Product with ID " + id + " not found!"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public List<Product> searchProductByName(String keyword) {
        List<Product> products= productRepository.findByNameContainingIgnoreCase(keyword);
        if (products.isEmpty())
            throw  new ProductNotFoundException("No products found for keyword: " + keyword);
        return products;
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
}
