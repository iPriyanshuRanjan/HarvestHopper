package com.hh.order_service.dto;

import java.math.BigDecimal;

public record OrderRequest(
        Long id,
        String orderNumber,
        String skuCode,
        String productName,
        Integer quantity,
        BigDecimal price
) {
}
