package com.HarvestHopper.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long product_id;
   private String name;
   private String description;
   private BigDecimal price;
   @JsonBackReference
   @ManyToOne
   @JoinColumn(name = "category_id")
   private Category category;

   private int stock_quantity;
   @CreationTimestamp
   private LocalDateTime createdAt;
}
