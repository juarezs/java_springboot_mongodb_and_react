package com.exams.spservice.service;

import com.exams.spservice.dto.ProductDto;
import com.exams.spservice.model.Product;
import com.exams.spservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductDto save(ProductDto productDto) {
        Product newProduct = new Product(productDto);
        productRepository.save(newProduct);
        return new ProductDto(newProduct);
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findProductByDeleted(false);
    }

    @Override
    public void delete(String id) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setDeleted(true);
        productRepository.save(product);
    }

}
