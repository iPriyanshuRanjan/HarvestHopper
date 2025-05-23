package com.hh.order_service.config;

import com.hh.order_service.client.InventoryClient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {
    @Value("${inventory-service.url}")
    private String inventoryServiceUrl;

    @Bean
    public InventoryClient inventoryClient() {
        RestClient restClient= RestClient.builder()
                .baseUrl(inventoryServiceUrl)
                .build();
        var restClientAdaptor= RestClientAdapter.create(restClient);
        var httpServiceProxyFactory= HttpServiceProxyFactory.builderFor(restClientAdaptor).build();
        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }
}
