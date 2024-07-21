package com.exams.spservice.controller;

import com.exams.spservice.dto.ProductDto;
import com.exams.spservice.model.Product;
import com.exams.spservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
class ProductControllerTest {

    private final String ENDPOINT_URL = "/api/product";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void ProductController_getProducts() throws Exception {
        // given
        List<Product> products = List.of(
                Product.builder()
                        .name("Product1")
                        .description("Product description 1")
                        .deleted(true)
                        .build(),
                Product.builder()
                        .name("Product 2")
                        .description("Product description 2")
                        .deleted(false)
                        .build(),
                Product.builder()
                        .name("Product 3")
                        .description("Product description 3")
                        .deleted(false)
                        .build(),
                Product.builder()
                        .name("Product 4")
                        .description("Product description 4")
                        .deleted(true)
                        .build()
        );
        productRepository.saveAll(products);

        // when
        ResultActions response = mockMvc.perform(
                get(ENDPOINT_URL).accept(MediaType.APPLICATION_JSON));

        // then
        response.andExpect(status().isOk());
        var responseDto = objectMapper.readValue(
                response.andReturn().getResponse().getContentAsString(),
                ProductDto[].class
        );
        assertArrayEquals(
                responseDto,
                List.of(
                        new ProductDto(products.get(1)),
                        new ProductDto(products.get(2))
                ).toArray()
        );
    }

    @Test
    void ProductController_deleteProducts() throws Exception {
        // given
        Product product = Product.builder()
                .name("Product1")
                .description("Product description 1")
                .deleted(false)
                .build();

        productRepository.save(product);

        // when
        ResultActions response = mockMvc.perform(
                delete(ENDPOINT_URL + "/" + product.getId()).accept(MediaType.APPLICATION_JSON));

        // then
        response.andExpect(status().isOk());
        product = productRepository.findById(product.getId()).orElseThrow();
        assertTrue(product.isDeleted());
    }
}