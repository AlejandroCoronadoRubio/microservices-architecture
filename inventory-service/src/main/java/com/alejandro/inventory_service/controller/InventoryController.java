package com.alejandro.inventory_service.controller;

import com.alejandro.inventory_service.domain.Product;
import com.alejandro.inventory_service.service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<List<Product>> getInventoryBySku(@PathVariable(name = "sku") String sku) {
        log.info("Fetching inventory with sku: {}", sku);
        return ResponseEntity.ok(this.inventoryService.getInventoryBySku(sku));
    }

    @GetMapping("/uniqId/{uniqId}")
    public ResponseEntity<List<Product>> getInventoryByUniqId(@PathVariable(name = "uniqId") String uniqId) {
        log.info("Fetching inventory with uniqId: {}", uniqId);
        return ResponseEntity.ok(this.inventoryService.getInventoryByUniqId(uniqId));
    }
}
