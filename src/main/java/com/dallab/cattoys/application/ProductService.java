package com.dallab.cattoys.application;

import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAllByDeleted(false);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productInformation) {
        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        product.changeByInformation(productInformation);

        return product;
    }

    public Product deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        product.delete();

        return product;
    }

}
