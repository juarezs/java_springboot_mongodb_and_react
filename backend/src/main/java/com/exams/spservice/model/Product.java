package com.exams.spservice.model;

import com.exams.spservice.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Product {
    @Id
    private String id;
    private String name;
    private String image;
    private String description;
    private BigDecimal price;
    @Indexed
    private boolean deleted;

    public Product(ProductDto productDto) {
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.image = productDto.getImage();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.deleted = false;
    }
}
