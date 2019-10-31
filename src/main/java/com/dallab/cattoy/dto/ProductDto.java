package com.dallab.cattoy.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Mapping("id")
    private Long id;

    @Mapping("name")
    private String name;

    @Mapping("maker")
    private String maker;

    @Mapping("price")
    private Integer price;

}
