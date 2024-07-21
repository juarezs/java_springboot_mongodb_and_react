package com.exams.spservice.service;

import com.exams.spservice.dto.ProductDto;
import com.exams.spservice.model.Product;
import com.exams.spservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void ProductService_save() {
        // given
        Product product = Product.builder()
                .name("Product")
                .description("Product description")
                .deleted(false)
                .build();
        ProductDto productDto = new ProductDto(product);
        when(productRepository.save(product)).thenReturn(product);

        // when
        var response = productService.save(productDto);

        // then
        verify(productRepository).save(product);
        Assertions.assertEquals(response, productDto);
    }

    @Test
    void ProductService_findAll() {
        // given
        List<ProductDto> products = List.of(
                ProductDto.builder()
                        .name("Product")
                        .description("Product description")
                        .build(),
                ProductDto.builder()
                        .name("Product 2")
                        .description("Product description 2")
                        .build()
        );
        when(productRepository.findProductByDeleted(false)).thenReturn(products);

        // when
        var response = productService.findAll();

        // then
        verify(productRepository).findProductByDeleted(false);
        Assertions.assertIterableEquals(response, products);
    }

    @Test
    void ProductService_delete() {
        // given
        String productId = "123";
        Product product = Product.builder()
                .id(productId)
                .name("Product 1")
                .image("https://")
                .description("Product description 1")
                .price(BigDecimal.ONE)
                .deleted(false)
                .build();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // when
        productService.delete(productId);

        // then
        verify(productRepository).findById(productId);
        verify(productRepository).save(new Product(
                productId, product.getName(), product.getImage(), product.getDescription(),
                product.getPrice(), true
        ));


    }
}