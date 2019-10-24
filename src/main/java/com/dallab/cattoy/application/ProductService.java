package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(String name) {
        products.add(Product.builder().name(name).build());
    }

}
