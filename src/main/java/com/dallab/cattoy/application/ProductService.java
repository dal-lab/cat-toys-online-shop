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

    public Product getProduct(Long id) {
        // TODO:
        return null;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }
}
