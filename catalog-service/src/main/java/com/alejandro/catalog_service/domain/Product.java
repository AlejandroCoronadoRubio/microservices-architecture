package com.alejandro.catalog_service.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uniq_id", nullable = false, length = 50)
    private String uniqId;

    @Column(name = "sku", length = 50)
    private String sku;

    @Column(name = "name_title", columnDefinition = "TEXT")
    private String nameTitle;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "list_price")
    private String listPrice;

    @Column(name = "sale_price")
    private String salePrice;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "category_tree", columnDefinition = "TEXT")
    private String categoryTree;

    @Column(name = "average_product_rating", length = 20)
    private String averageProductRating;

    @Column(name = "product_url", columnDefinition = "TEXT")
    private String productUrl;

    @Column(name = "product_image_urls", columnDefinition = "TEXT")
    private String productImageUrls;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "total_number_reviews")
    private String totalNumberReviews;

    @Column(name = "reviews", columnDefinition = "TEXT")
    private String reviews;
}