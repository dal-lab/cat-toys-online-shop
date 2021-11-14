package com.dallab.cattoy.domain;

import com.dallab.cattoy.dto.ProductDto;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    private Product product;

    @BeforeEach
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

    @Test
    public void mapFromDTO() {
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        ProductDto productDto = ProductDto.builder()
                .name("쥐돌이")
                .build();

        Product product = mapper.map(productDto, Product.class);

        assertThat(product.getName()).isEqualTo("쥐돌이");
    }

}
