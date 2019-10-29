package com.dallab.cattoy.dto;

import com.github.dozermapper.core.Mapping;
import lombok.Data;

@Data
public class ProductDto {

    @Mapping("name")
    private String name;

    private String maker;

    private Integer price;

}
