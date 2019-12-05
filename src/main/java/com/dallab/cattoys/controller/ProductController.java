package com.dallab.cattoys.controller;

import com.dallab.cattoys.application.ProductService;
import com.dallab.cattoys.domain.Product;
import com.dallab.cattoys.dto.ProductDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("{id}")
    public ProductDto detail(
            @PathVariable Long id
    ) {
        Product product = productService.getProduct(id);

        return mapper.map(product, ProductDto.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    public void create(
            Authentication authentication,
            @Valid @RequestBody ProductDto productDto
    ) {
        Product product = mapper.map(productDto, Product.class);

        productService.addProduct(product);
    }

    @PatchMapping("{id}")
    public void update(
            @PathVariable Long id,
            @Valid @RequestBody ProductDto productDto
    ) {
        Product product = mapper.map(productDto, Product.class);

        productService.updateProduct(id, product);
    }

    @DeleteMapping("{id}")
    public void destroy(
            @PathVariable("id") Long productId
    ) {
        productService.deleteProduct(productId);
    }

}
