package com.alejandro.catalog_service.service;

import com.alejandro.catalog_service.domain.Product;
import com.alejandro.catalog_service.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProductsBySku(String sku) {
        return this.productRepository.findBySku(sku);
    }

    public List<Product> getProductsByUniqId(String uniqId) {
        return this.productRepository.findByUniqId(uniqId);
    }
}
