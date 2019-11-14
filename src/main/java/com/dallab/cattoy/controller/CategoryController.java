package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.CategoryService;
import com.dallab.cattoy.authentication.AuthorizeRequired;
import com.dallab.cattoy.domain.Category;
import com.dallab.cattoy.dto.CategoryDto;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequestMapping("/categories")
@RestController
public class CategoryController {

    @Autowired
    private Mapper mapper;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> list() {
        List<Category> categories = categoryService.getCategories();

        return categories.stream()
                .map(category -> mapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CategoryDto detail(
            @PathVariable Long id
    ) {
        Category category = categoryService.getCategory(id);

        return mapper.map(category, CategoryDto.class);
    }

    @AuthorizeRequired("ADMIN")
    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody CategoryDto categoryDto
    ) throws URISyntaxException {
        Category category = categoryService.addCategory(
                mapper.map(categoryDto, Category.class));

        String url = "/categories/" + category.getId();

        return ResponseEntity.created(new URI(url)).build();
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('ADMIN')")
    @PatchMapping("{id}")
    public CategoryDto update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryDto categoryDto
    ) {
        Category category = categoryService.updateCategory(id, categoryDto);

        return mapper.map(category, CategoryDto.class);
    }

    @DeleteMapping("{id}")
    public void destroy(
            @PathVariable Long id
    ) {
        categoryService.removeCategory(id);
    }

}
