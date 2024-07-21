package com.exams.spservice.repository;

import com.exams.spservice.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class ProductRepositoryTest {

    @Autowired ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void ProductRepository_findProductByDeleted() {
        Product prd1 = Product
                .builder()
                .name("Product 1")
                .deleted(false)
                .build();
        Product prd2 = Product
                .builder()
                .name("Product 2")
                .deleted(true)
                .build();
        Product prd3 = Product
                .builder()
                .name("Product 3")
                .deleted(false)
                .build();
        productRepository.saveAll(List.of(prd1, prd2, prd3));

        // when
        var response = productRepository.findProductByDeleted(false);

        // then
        assertEquals(response.size(), 2);
        assertEquals(response.stream().filter(p -> p.getName().equals(prd1.getName())).count(), 1);
        assertEquals(response.stream().filter(p -> p.getName().equals(prd3.getName())).count(), 1);
    }
}