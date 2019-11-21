package com.dallab.cattoys.application;

import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.domain.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        productService = new ProductService(productRepository);
    }

    @Test
    public void getProducts() {
        Product product = Product.builder()
                .name("쥐돌이")
                .maker("달랩")
                .price(6000)
                .build();

        given(productRepository.findAll()).willReturn(Arrays.asList(product));

        List<Product> products = productService.getProducts();

        assertThat(products).hasSize(1);

        assertThat(products.get(0).getName()).isEqualTo("쥐돌이");

        verify(productRepository).findAll();
    }

}
