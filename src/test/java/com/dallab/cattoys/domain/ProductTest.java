package com.dallab.cattoys.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

// 1. 왜 쓰냐
// 2. 어떻게 쓰냐
// 3. 어떻게 되냐

class ProductTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .name("쥐돌이")
                .maker("달랩")
                .price(6000)
                .build();
    }

    @Test
    public void checkAttributes() {
        assertThat(product.getName()).isEqualTo("쥐돌이");
        assertThat(product.getPrice()).isEqualTo(6000);
    }

    @Test
    public void price() {
        assertThat(product.getPrice()).isEqualTo(6000);
        assertThat(product.getPrice(1)).isEqualTo(6000);
        assertThat(product.getPrice(2)).isEqualTo(12000);
    }

    @Test
    public void priceWithDicount() {
        assertThat(product.getPrice(3)).isEqualTo(18000 - 1000);
        assertThat(product.getPrice(4)).isEqualTo(24000 - 1000);
    }

}
