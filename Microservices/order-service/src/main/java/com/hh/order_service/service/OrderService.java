package com.hh.order_service.service;

import com.hh.order_service.dto.OrderRequest;
import com.hh.order_service.model.Order;
import com.hh.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        //map orderRequest to Order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        order.setPrice(orderRequest.price());
        //save order to order Repository

        orderRepository.save(order);

    }
}
