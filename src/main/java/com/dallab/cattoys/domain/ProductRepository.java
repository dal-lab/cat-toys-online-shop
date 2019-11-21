package com.dallab.cattoys.domain;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {

    public List<Product> findAll() {
        List<Product> products = Arrays.asList(
                Product.builder()
                        .name("쥐돌이")
                        .maker("달랩")
                        .price(6000)
                        .build()
        );

        return products;
    }

}
