package com.dallab.cattoys.controller;

import com.dallab.cattoys.application.ProductService;
import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> list() {
        List<Product> products = productService.getProducts();

        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(productToDto(product));
        }

        return productDtos;
    }

    private ProductDto productToDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .maker(product.getMaker())
                .price(product.getPrice())
                .build();
    }

}
