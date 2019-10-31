package com.dallab.cattoy.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Mapping("id")
    private Long id;

    @NotBlank
    @Mapping("name")
    private String name;

    @Mapping("maker")
    private String maker;

    @Min(0)
    @Mapping("price")
    private Integer price;

}
