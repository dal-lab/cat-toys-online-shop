package com.dallab.cattoys.controller;

import com.dallab.cattoys.application.ProductService;
import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> list() {
        List<Product> products = productService.getProducts();

        return products.stream()
                .map(product -> productToDto(product))
                .collect(Collectors.toList());
    }

    private ProductDto productToDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .maker(product.getMaker())
                .price(product.getPrice())
                .build();
    }

}
