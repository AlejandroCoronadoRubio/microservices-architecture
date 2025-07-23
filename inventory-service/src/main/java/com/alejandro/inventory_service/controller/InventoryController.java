package com.alejandro.inventory_service.controller;

import com.alejandro.inventory_service.domain.Product;
import com.alejandro.inventory_service.service.InventoryService;
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
@RequestMapping("/inventory")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/sku/{sku}")
    @CircuitBreaker(name = "getProductsBySku", fallbackMethod = "fallbackCatalogBySku")
    public ResponseEntity<?> getInventoryBySku(@PathVariable(name = "sku") String sku) {
        log.info("Fetching inventory with sku: {}", sku);
        return ResponseEntity.ok(this.inventoryService.getInventoryBySku(sku));
    }

    @GetMapping("/uniqId/{uniqId}")
    @CircuitBreaker(name = "getProductsByUniqId", fallbackMethod = "fallbackCatalogByUniqId")
    public ResponseEntity<?> getInventoryByUniqId(@PathVariable(name = "uniqId") String uniqId) {
        log.info("Fetching inventory with uniqId: {}", uniqId);
        return ResponseEntity.ok(this.inventoryService.getInventoryByUniqId(uniqId));
    }

    private ResponseEntity<String> fallbackCatalogBySku(String sku, Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred, please try again later.");
    }

    private ResponseEntity<String> fallbackCatalogByUniqId(String uniqId, Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred, please try again later.");
    }
}
