package com.alejandro.inventory_service.client;

import com.alejandro.inventory_service.domain.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("catalog-service")
public interface CatalogClient {

    @GetMapping("/catalog/sku/{sku}")
    @CircuitBreaker(name = "getProductsBySku", fallbackMethod = "fallbackCatalogBySku")
    ResponseEntity<List<Product>> getProductsBySku(@PathVariable(name = "sku") String sku);

    @GetMapping("/catalog/uniqId/{uniqId}")
    @CircuitBreaker(name = "getProductsByUniqId", fallbackMethod = "fallbackCatalogByUniqId")
    ResponseEntity<List<Product>> getProductsByUniqId(@PathVariable(name = "uniqId") String uniqId);

    default List<Product> fallbackCatalogBySku(String sku, Throwable ex) {
        return List.of(new Product());
    }

    default List<Product> fallbackCatalogByUniqId(String uniqId, Throwable ex) {
        return List.of(new Product());
    }
}
