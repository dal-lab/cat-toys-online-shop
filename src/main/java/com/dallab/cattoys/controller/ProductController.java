package com.dallab.cattoys.controller;

import com.dallab.cattoys.application.ProductService;
import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> list() {
//        List<Product> products = Arrays.asList(
//                Product.builder()
//                        .name("쥐돌이")
//                        .maker("달랩")
//                        .price(6000)
//                        .build()
//        );

        // 위의 코드를 이렇게 다른 곳으로 옮길 수 있다면?
        // = Application layer의 객체와 협력해서 이 일을 처리한다면?
        List<Product> products = productService.getProducts();

        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            productDtos.add(
                    ProductDto.builder()
                            .name(product.getName())
                            .maker(product.getMaker())
                            .price(product.getPrice())
                            .build()
            );
        }

        return productDtos;
    }

}
