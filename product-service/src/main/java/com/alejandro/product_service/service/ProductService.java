package com.alejandro.product_service.service;

import com.alejandro.product_service.client.InventoryClient;
import com.alejandro.product_service.domain.InventoryStatus;
import com.alejandro.product_service.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final InventoryClient inventoryClient;

    public List<Product> getInventoryBySku(String sku) {
        return this.inventoryClient.getInventoryBySku(sku)
                .getBody()
                .stream()
                .filter(product -> !product.getInventoryStatus().equals(InventoryStatus.OUT_OF_STOCK.getName()))
                .collect(Collectors.toList());
    }

    public List<Product> getInventoryByUniqId(String uniqId) {
        return this.inventoryClient.getInventoryByUniqId(uniqId)
                .getBody()
                .stream()
                .filter(product -> !product.getInventoryStatus().equals(InventoryStatus.OUT_OF_STOCK.getName()))
                .collect(Collectors.toList());
    }
}
