package com.hh.inventory_service.service.serviceImpl;

import com.hh.inventory_service.client.ProductClient;
import com.hh.inventory_service.dto.InventoryWithProductResponse;
import com.hh.inventory_service.dto.ProductResponse;
import com.hh.inventory_service.entity.Inventory;
import com.hh.inventory_service.exception.ProductNotFoundInInventoryException;
import com.hh.inventory_service.repository.InventoryRepository;
import com.hh.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
    public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductClient productClient;

    @Override
    public boolean IsInStock(String skuCode, Integer quantity) {
            // Find the inventory for a given SKU code when the quantity is greater than 0
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
        }

        @Override
        public void addStock(String skuCode, Integer quantity) {
            // Check if the product with the given SKU already exists in the inventory
            Inventory existingInventory = inventoryRepository.findBySkuCode(skuCode)
                    .orElse(null);

            if (existingInventory != null) {
                // If product exists, update the quantity by adding the new quantity
                existingInventory.setQuantity(existingInventory.getQuantity() + quantity);
                inventoryRepository.save(existingInventory);
            } else {
                // If product doesn't exist, create a new inventory entry
                Inventory inventory = new Inventory();
                inventory.setSkuCode(skuCode);
                inventory.setQuantity(quantity);
                inventoryRepository.save(inventory);
            }
        }

        @Override
        public InventoryWithProductResponse getInventoryWithProductName(String skuCode) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ProductNotFoundInInventoryException("Product not found with SKU code: " + skuCode));

        ProductResponse product = productClient.getProductBySku(skuCode);

        return new InventoryWithProductResponse(
                skuCode,
                inventory.getQuantity(),
                inventory.getQuantity() > 0,
                product.name()
        );
    }

    @Override
    public void deleteStock(String skuCode, Integer quantity) {
        // Find the inventory for the given SKU code
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ProductNotFoundInInventoryException("Product not found with SKU code: " + skuCode));

        // Check if the quantity to be deleted is less than or equal to the current quantity
        if (quantity <= inventory.getQuantity()) {
            // Update the quantity by subtracting the deleted quantity
            inventory.setQuantity(inventory.getQuantity() - quantity);
            inventoryRepository.save(inventory);
        } else {
            throw new ProductNotFoundInInventoryException("Insufficient stock to delete");
        }
    }

    @Override
    public void updateStockBySKUCode(String skuCode, Integer quantity) {
        // Find the inventory for the given SKU code
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ProductNotFoundInInventoryException("Product not found with SKU code: " + skuCode));

        // Update the quantity
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }


}
