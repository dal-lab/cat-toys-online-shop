package com.dallab.cattoys.application;

import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
