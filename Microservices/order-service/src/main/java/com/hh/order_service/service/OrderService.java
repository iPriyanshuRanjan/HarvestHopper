package com.hh.order_service.service;

import com.hh.order_service.client.InventoryClient;
import com.hh.order_service.dto.OrderRequest;
import com.hh.order_service.exception.ProductOutOfStockException;
import com.hh.order_service.model.Order;
import com.hh.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final InventoryClient inventoryClient;
    private final OrderRepository orderRepository;

    @Transactional
    public void placeOrder(OrderRequest orderRequest) {
        log.info("Received order request: {}", orderRequest);

        if (orderRequest == null) {
            log.error("OrderRequest is null");
            throw new IllegalArgumentException("OrderRequest cannot be null");
        }

        log.info("Checking inventory for SKU code: {} and quantity: {}", orderRequest.skuCode(), orderRequest.quantity());
        boolean isProductInStock = inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity());
        if (!isProductInStock) {
            log.warn("Product with SKU code {} is not in stock", orderRequest.skuCode());
            throw new ProductOutOfStockException("Product with SKU code " + orderRequest.skuCode() + " is out of stock");
        }

        log.info("Product is in stock. Creating order...");
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderRequest.skuCode());
        order.setProductName(orderRequest.productName());
        order.setQuantity(orderRequest.quantity());
        order.setPrice(orderRequest.price());

        orderRepository.save(order);
        log.info("Order saved successfully with order number: {}", order.getOrderNumber());
    }
}