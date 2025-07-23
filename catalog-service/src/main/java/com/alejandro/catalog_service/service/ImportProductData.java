package com.alejandro.catalog_service.service;

import com.alejandro.catalog_service.domain.Product;
import com.alejandro.catalog_service.repository.ProductRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ImportProductData {

    private final ProductRepository productRepository;

    @PostConstruct
    public void importProductData() {

        try {

            Path path = Paths.get(Objects.requireNonNull(FileSystemResourceLoader.class.getClassLoader().getResource("jcpenney_com-ecommerce_sample.csv")).toURI());
            Reader reader = Files.newBufferedReader(path);
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .withSkipLines(1)
                    .build();

            List<Product> products = new ArrayList<>();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {

                Product product = Product.builder()
                .uniqId(nextRecord[0])
                .sku(nextRecord[1])
                .nameTitle(nextRecord[2])
                .description(nextRecord[3])
                .listPrice(nextRecord[4])
                .salePrice(nextRecord[5])
                .category(nextRecord[6])
                .categoryTree(nextRecord[7])
                .averageProductRating(nextRecord[8])
                .productUrl(nextRecord[9])
                .productImageUrls(nextRecord[10])
                .brand(nextRecord[11])
                .totalNumberReviews(nextRecord[12])
                .reviews(nextRecord[13])
                .build();
                products.add(product);
            }

            productRepository.saveAll(products);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
