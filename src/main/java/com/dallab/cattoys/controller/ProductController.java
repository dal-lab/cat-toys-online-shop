package com.dallab.cattoys.controller;

import com.dallab.cattoys.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Product> list() {
        Product product = Product.builder()
                .name("쥐돌이")
                .maker("달랩")
                .price(6000)
                .build();

        List<Product> products = Arrays.asList(product);

        return products;
    }

}
