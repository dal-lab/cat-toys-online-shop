package com.dallab.cattoy.dto;

import com.github.dozermapper.core.Mapping;
import lombok.Data;

@Data
public class ProductDto {

    @Mapping("name")
    private String name;

    @Mapping("maker")
    private String maker;

    @Mapping("price")
    private Integer price;

}
