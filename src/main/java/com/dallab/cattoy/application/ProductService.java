package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.domain.ProductRepository;
import com.dallab.cattoy.dto.ProductDto;
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
        return productRepository.findById(id).get();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDto productDto) {
        // TODO:
        return null;
    }

    public void removeProduct(Long id) {
        productRepository.deleteById(id);
    }

}
