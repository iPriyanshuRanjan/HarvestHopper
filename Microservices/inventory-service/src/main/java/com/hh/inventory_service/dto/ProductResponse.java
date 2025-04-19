package com.hh.inventory_service.dto;

import java.math.BigDecimal;

public record ProductResponse(String skuCode, String name, String description, BigDecimal price) {
}
