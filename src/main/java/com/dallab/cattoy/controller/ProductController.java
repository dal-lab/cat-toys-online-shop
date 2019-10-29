package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.ProductService;
import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.dto.ProductDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    Mapper mapper;

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> list() {
        List<Product> products = productService.getProducts();

        return products.stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

}
