package com.hh.order_service.client;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(name = "inventory", url = "http://localhost:8083")
public interface InventoryClient {

//    @RequestMapping(method= RequestMethod.GET, value= "/api/inventory")
    @GetExchange("/api/inventory")
    boolean isInStock(@RequestParam String skuCode,@RequestParam Integer quantity);
}
