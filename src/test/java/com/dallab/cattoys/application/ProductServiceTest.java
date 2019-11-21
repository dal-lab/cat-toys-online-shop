package com.dallab.cattoys.application;

import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepository();

        productService = new ProductService(productRepository);
    }

    @Test
    public void getProducts() {
        List<Product> products = productService.getProducts();

        assertThat(products).hasSize(1);

        assertThat(products.get(0).getName()).isEqualTo("쥐돌이");
    }

}
