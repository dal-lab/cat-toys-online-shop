package com.dallab.cattoys.controller;

import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<ProductDto> list() {
        List<Product> products = Arrays.asList(
                Product.builder()
                        .name("쥐돌이")
                        .maker("달랩")
                        .price(6000)
                        .build()
        );

        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(
                    ProductDto.builder()
                            .name(product.getName())
                            .maker(product.getMaker())
                            .price(product.getPrice())
                            .build()
            );
        }

        return productDtos;
    }

}
