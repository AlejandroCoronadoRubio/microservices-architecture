package com.alejandro.product_service.controller;

import com.alejandro.product_service.domain.Product;
import com.alejandro.product_service.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping("/sku/{sku}")
    @CircuitBreaker(name = "getInventoryBySku", fallbackMethod = "fallbackInventoryBySku")
    public ResponseEntity<?> getProductsBySku(@PathVariable(name = "sku") String sku) {
        log.info("Fetching product with sku: {}", sku);
        return ResponseEntity.ok(this.productService.getInventoryBySku(sku));
    }

    @GetMapping("/uniqId/{uniqId}")
    @CircuitBreaker(name = "getInventoryByUniqId", fallbackMethod = "fallbackInventoryByUniqId")
    public ResponseEntity<?> getProductsByUniqId(@PathVariable(name = "uniqId") String uniqId) {
        log.info("Fetching product with uniqId: {}", uniqId);
        return ResponseEntity.ok(this.productService.getInventoryByUniqId(uniqId));
    }

    private ResponseEntity<String> fallbackInventoryBySku(String sku, Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred, please try again later.");
    }

    private ResponseEntity<String> fallbackInventoryByUniqId(String uniqId, Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred, please try again later.");
    }
}
