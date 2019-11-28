package com.dallab.cattoys.controller;

import com.dallab.cattoys.application.ProductService;
import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.dto.ProductDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> list() {
        List<Product> products = productService.getProducts();

        return products.stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(
            @RequestBody ProductDto productDto
    ) {
        Product product = mapper.map(productDto, Product.class);

        productService.addProduct(product);
    }

}
