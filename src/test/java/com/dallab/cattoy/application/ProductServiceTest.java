package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.domain.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        productService = new ProductService(productRepository);
    }

    @Test
    public void getProductsWithEmpty() {
        List<Product> products = new ArrayList<>();

        given(productRepository.findAll()).willReturn(products);

        assertThat(productService.getProducts()).isEmpty();
    }

    @Test
    public void getProductsWithOneProduct() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().name("쥐돌이").build());

        given(productRepository.findAll()).willReturn(products);

        assertThat(productService.getProducts()).isNotEmpty();
    }

    @Test
    public void addProduct() {
        Product product = Product.builder()
                .name("쥐돌이")
                .maker("달랩")
                .price(5000)
                .build();

        productService.addProduct(product);

        verify(productRepository).save(any());
    }

    @Test
    public void removeProduct() {
        productService.removeProduct(13L);

        verify(productRepository).deleteById(13L);
    }

}
