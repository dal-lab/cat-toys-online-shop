package com.dallab.cattoys.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return products;
    }

    public Product save(Product product) {
        products.add(product);

        return product;
    }

}
