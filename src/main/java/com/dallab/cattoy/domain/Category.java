package com.dallab.cattoy.domain;

import com.dallab.cattoy.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String name;

    private Integer sequence;

    public void changeWithDto(CategoryDto categoryDto) {
        name = categoryDto.getName();
        sequence = categoryDto.getSequence();
    }

}
