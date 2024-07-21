package com.exams.spservice.service;

import com.exams.spservice.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public ProductDto save(ProductDto product);

    public List<ProductDto> findAll();

    public void delete(String id);
}
