package com.hh.product_service.dto;

import java.math.BigDecimal;

public record ProductRequest(String name, String description, BigDecimal price) {
    // No additional methods or annotations are needed here
}
