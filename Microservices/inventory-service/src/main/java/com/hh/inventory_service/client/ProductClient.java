package com.hh.inventory_service.client;

import com.hh.inventory_service.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "http://localhost:8080")
    public interface ProductClient {

        @GetMapping("/api/product/sku/{skuCode}")
        ProductResponse getProductBySku(@PathVariable String skuCode);
}
