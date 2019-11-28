package com.dallab.cattoys.application;

import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.domain.ProductRepository;
import org.hibernate.procedure.ProcedureOutputs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void getProduct() {
        // Given

        Product mockProduct = Product.builder()
                .name("쥐돌이")
                .maker("달랩")
                .price(6000)
                .build();

        given(productRepository.findById(13L))
                .willReturn(Optional.of(mockProduct));

        // When - 테스트 대상

        Product product = productService.getProduct(13L);

        // Then

        assertThat(product.getName()).isEqualTo("쥐돌이");

        verify(productRepository).findById(13L);
    }

    @Test
    public void addProduct() {
        Product product = Product.builder()
                .name("쥐돌이")
                .maker("달랩")
                .price(6000)
                .build();

        productService.addProduct(product);

        verify(productRepository).save(product);
    }

    @Test
    public void updateProduct() {
        given(productRepository.findById(13L))
                .willReturn(Optional.of(
                        Product.builder().name("쥐돌이").build()
                ));

        Product product = Product.builder()
                .name("쥐돌이2")
                .maker("달랩")
                .price(6000)
                .build();

        Product updatedProduct = productService.updateProduct(13L, product);

        verify(productRepository).findById(13L);

        assertThat(updatedProduct.getName()).isEqualTo("쥐돌이2");
        assertThat(updatedProduct.getMaker()).isEqualTo("달랩");
    }

    @Test
    public void deleteProduct() {
        productService.deleteProduct(13L);

        verify(productRepository).deleteById(13L);
    }

}
