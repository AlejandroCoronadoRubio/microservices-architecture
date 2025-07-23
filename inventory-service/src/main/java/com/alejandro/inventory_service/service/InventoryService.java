package com.alejandro.inventory_service.service;

import com.alejandro.inventory_service.client.CatalogClient;
import com.alejandro.inventory_service.domain.Product;
import com.alejandro.inventory_service.utils.InventoryStatusUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class InventoryService {

    private static final Map<String, String> inMemoryStatusByUniq = new HashMap<>();
    private final CatalogClient catalogClient;

    public List<Product> getInventoryBySku(String sku) {
        List<Product> products = this.catalogClient.getProductsBySku(sku).getBody();
        products.forEach(product -> product.setInventoryStatus(getInMemoryStatus(product.getUniqId())));
        return products;
    }

    public List<Product> getInventoryByUniqId(String uniqId) {
        List<Product> products  = this.catalogClient.getProductsByUniqId(uniqId).getBody();
        products.forEach(product -> product.setInventoryStatus(getInMemoryStatus(uniqId)));
        return products;
    }

    private String getInMemoryStatus(String uniqId) {
        if(inMemoryStatusByUniq.get(uniqId) == null) {
            inMemoryStatusByUniq.put(uniqId, InventoryStatusUtil.getRandomInventoryStatus());
        }
        return inMemoryStatusByUniq.get(uniqId);
    }
}
