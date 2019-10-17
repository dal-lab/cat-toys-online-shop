package com.dallab.cattoy.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    private Product product;

    @Before
    public void initProduct() {
        product = Product.builder()
                .name("쥐돌이x")
                .maker("펭귄")
                .price(3000)
                .build();
    }

    @Test
    public void 상품_도메인_모델_생성() {
        assertThat(product.getName()).isEqualTo("쥐돌이x");
        assertThat(product.getMaker()).isEqualTo("펭귄");
        assertThat(product.getPriceWithComma()).isEqualTo("3,000");
    }

    @Test
    public void defaultImage() {
        assertThat(product.getImageUrl()).isEqualTo("");
    }

    @Test
    public void changeImage() {
        final String imageUrl = "https://images.cat-toy.com/1234.jpg";

        product.changeImageUrl(imageUrl);

        assertThat(product.getImageUrl()).isEqualTo(imageUrl);
    }

}
