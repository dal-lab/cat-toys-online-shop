package com.dallab.cattoys.domain;

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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String name;

    @Getter
    private String maker;

    @Getter
    private Integer price;

    @Getter
    private boolean deleted;

    public Integer getPrice(int quantity) {
        Integer discount = quantity < 3 ? 0 : 1000;
        return price * quantity - discount;
    }

    public void changeByInformation(Product productInformation) {
        name = productInformation.name;
        maker = productInformation.maker;
        price = productInformation.price;
    }

    public void delete() {
        deleted = true;
    }

}
