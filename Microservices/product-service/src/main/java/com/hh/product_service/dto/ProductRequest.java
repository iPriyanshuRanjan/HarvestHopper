package com.hh.product_service.dto;

import java.math.BigDecimal;


public record ProductRequest(String name, String imageUrl, int skuCode,  String description, BigDecimal price) {

    public ProductRequest{
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (imageUrl == null || imageUrl.isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty");
        }
        if (skuCode <= 0) {
            throw new IllegalArgumentException("SKU code must be a positive integer");
        }
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be a positive number");
        }
    }

}
