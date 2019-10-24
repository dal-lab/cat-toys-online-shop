package com.dallab.cattoy.application;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductServiceTest {

    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService();
    }

    @Test
    public void getProducts() throws Exception {
        assertThat(productService.getProducts()).isEmpty();
    }

    @Test
    public void addProduct() throws Exception {
        productService.addProduct("쥐돌이");

        assertThat(productService.getProducts()).isNotEmpty();
    }

}
