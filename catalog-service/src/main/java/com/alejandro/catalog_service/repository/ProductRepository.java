package com.alejandro.catalog_service.repository;

import com.alejandro.catalog_service.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findBySku(String sku);
    List<Product> findByUniqId(String uniqId);
}
