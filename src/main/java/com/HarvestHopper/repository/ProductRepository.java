package com.HarvestHopper.repository;

import com.HarvestHopper.model.Category;
import com.HarvestHopper.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String keyword);
    List<Product> findByCategory(Category category);
    List<Product> findByCategoryId(Long categoryId);
}
