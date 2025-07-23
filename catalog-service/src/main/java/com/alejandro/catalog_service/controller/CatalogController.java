package com.alejandro.catalog_service.controller;

import com.alejandro.catalog_service.domain.Product;
import com.alejandro.catalog_service.service.ProductService;
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
@RequestMapping("/catalog")
@Slf4j
public class CatalogController {

    private final ProductService productService;

    @GetMapping("/sku/{sku}")
    public ResponseEntity<List<Product>> getProductsBySku(@PathVariable(name = "sku") String sku) {
        log.info("Fetching catalog with sku: {}", sku);
        return ResponseEntity.ok(this.productService.getProductsBySku(sku));
    }

    @GetMapping("/uniqId/{uniqId}")
    public ResponseEntity<List<Product>> getProductsByUniqId(@PathVariable(name = "uniqId") String uniqId) {
        log.info("Fetching catalog with uniqId: {}", uniqId);
        return ResponseEntity.ok(this.productService.getProductsByUniqId(uniqId));
    }
}
