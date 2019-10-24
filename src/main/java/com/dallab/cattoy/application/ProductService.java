package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.domain.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(String name) {
        Product product = Product.builder().name(name).build();

        productRepository.save(product);
    }
}
