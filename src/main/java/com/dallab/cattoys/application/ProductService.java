package com.dallab.cattoys.application;

import com.dallab.cattoys.domain.Product;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getProducts() {
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
