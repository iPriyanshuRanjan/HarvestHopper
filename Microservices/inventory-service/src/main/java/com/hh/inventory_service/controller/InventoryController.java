package com.hh.inventory_service.controller;

import com.hh.inventory_service.dto.InventoryWithProductResponse;
import com.hh.inventory_service.entity.Inventory;
import com.hh.inventory_service.service.serviceImpl.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryServiceImpl inventoryServiceImpl;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(String skuCode, Integer quantity) {
        // Check if the inventory is in stock for a given SKU code and quantity
        return inventoryServiceImpl.IsInStock(skuCode, quantity);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        // Add stock for a given SKU code and quantity
        inventoryServiceImpl.addStock(skuCode, quantity);
        return "Stock added successfully for SKU code: " + skuCode;
    }

    @GetMapping("/product/{skuCode}")
    @ResponseStatus(HttpStatus.OK)
    public InventoryWithProductResponse getInventoryWithProductName(@PathVariable String skuCode) {
        return inventoryServiceImpl.getInventoryWithProductName(skuCode);
    }


    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        // Delete stock for a given SKU code and quantity
        inventoryServiceImpl.deleteStock(skuCode, quantity);
        return "Stock deleted successfully for SKU code: " + skuCode + " with quantity: " + quantity;
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public String updateStockBySKUCode(@RequestParam String skuCode, @RequestParam Integer quantity) {
        // Update stock for a given SKU code and quantity
        inventoryServiceImpl.updateStockBySKUCode(skuCode, quantity);
        return "Stock updated successfully for SKU code: " + skuCode + " with quantity: " + quantity;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getAllInventories() {
        // Get all inventories
        return inventoryServiceImpl.getAllInventories();
    }
}
