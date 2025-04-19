package com.hh.inventory_service.dto;

public record InventoryWithProductResponse(
        String skuCode,
        int quantity,
        boolean isInStock,
        String productName) {

}
