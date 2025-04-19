package com.hh.inventory_service.service;

import com.hh.inventory_service.dto.InventoryWithProductResponse;

public interface InventoryService {

    // Check if the product is in stock
    public boolean IsInStock(String skuCode, Integer quantity);

    //Add stock by SKUCode
    void addStock(String skuCode, Integer quantity);

    public InventoryWithProductResponse getInventoryWithProductName(String skuCode);

    //Delete stock by SKUCode
    void deleteStock(String skuCode, Integer quantity);

    //Update existing Stock by SKUCode
    void updateStockBySKUCode(String skuCode, Integer quantity);

}
