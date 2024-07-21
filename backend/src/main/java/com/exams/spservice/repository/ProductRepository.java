package com.exams.spservice.repository;

import com.exams.spservice.dto.ProductDto;
import com.exams.spservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<ProductDto> findProductByDeleted(boolean deleted);
}
