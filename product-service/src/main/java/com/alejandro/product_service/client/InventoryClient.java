package com.alejandro.product_service.client;

import com.alejandro.product_service.domain.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("inventory-service")
public interface InventoryClient {

    @GetMapping("/inventory/sku/{sku}")
    @CircuitBreaker(name = "getInventoryBySku", fallbackMethod = "fallbackInventoryBySku")
    ResponseEntity<List<Product>> getInventoryBySku(@PathVariable(name = "sku") String sku);

    @GetMapping("/inventory/uniqId/{uniqId}")
    @CircuitBreaker(name = "getInventoryByUniqId", fallbackMethod = "fallbackInventoryByUniqId")
    ResponseEntity<List<Product>> getInventoryByUniqId(@PathVariable(name = "uniqId") String uniqId);

    default List<Product> fallbackInventoryBySku(String sku, Throwable ex) {
        return List.of(new Product());
    }

    default List<Product> fallbackInventoryByUniqId(String uniqId, Throwable ex) {
        return List.of(new Product());
    }
}
