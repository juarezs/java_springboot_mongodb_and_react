package com.exams.spservice.controller;

import com.exams.spservice.dto.ProductDto;
import com.exams.spservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteProducts(@PathVariable String id) {
        productService.delete(id);
    }
}
