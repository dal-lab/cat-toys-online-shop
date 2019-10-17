package com.dallab.cattoy.domain;

import lombok.Builder;
import lombok.Getter;

import java.text.NumberFormat;

@Builder
public class Product {

    @Getter
    private String name;

    @Getter
    private String maker;

    private Integer price;

    private String imageUrl;

    public String getPriceWithComma() {
        return NumberFormat.getInstance().format(price);
    }

    public String getImageUrl() {
        return imageUrl == null ? "" : imageUrl;
    }

    public void changeImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
