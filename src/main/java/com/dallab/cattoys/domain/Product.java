package com.dallab.cattoys.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
public class Product {

    @Getter
    private String name;

    @Getter
    private String maker;

    @Getter
    private Integer price;

    public Integer getPrice(int quantity) {
        Integer discount = quantity < 3 ? 0 : 1000;
        return price * quantity - discount;
    }

}
