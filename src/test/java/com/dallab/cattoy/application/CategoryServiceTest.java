package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Category;
import com.dallab.cattoy.domain.CategoryRepository;
import com.dallab.cattoy.dto.CategoryDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CategoryServiceTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private Category mockCategory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryService(categoryRepository);
    }

    @Before
    public void initMockCategory() {
        mockCategory = Category.builder()
                .id(13L)
                .name("인형")
                .sequence(1)
                .build();
    }

    @Test
    public void getCategories() {
        List<Category> categories = Arrays.asList(mockCategory);

        given(categoryRepository.findAll()).willReturn(categories);

        assertThat(categoryService.getCategories()).isNotEmpty();
    }

    @Test
    public void getCategory() {
        given(categoryRepository.findById(13L))
                .willReturn(Optional.of(mockCategory));

        assertThat(categoryService.getCategory(13L))
                .isEqualTo(mockCategory);
    }

    @Test
    public void addCategory() {
        categoryService.addCategory(mockCategory);

        verify(categoryRepository).save(mockCategory);
    }

    @Test
    public void updateCategory() {
        given(categoryRepository.findById(13L))
                .willReturn(Optional.of(mockCategory));

        CategoryDto categoryDto = CategoryDto.builder()
                .name("인형/쿠션")
                .sequence(2)
                .build();

        Category category = categoryService.updateCategory(13L, categoryDto);

        verify(categoryRepository).findById(13L);

        assertThat(category.getName()).isEqualTo("인형/쿠션");
    }

    @Test
    public void removeCategory() {
        categoryService.removeCategory(13L);

        verify(categoryRepository).deleteById(13L);
    }

}