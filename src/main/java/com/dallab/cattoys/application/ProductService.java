package com.dallab.cattoys.application;

import com.dallab.cattoys.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> getProducts() {
        return new ArrayList<>();
    }

}
