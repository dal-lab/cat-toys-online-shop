package com.dallab.cattoy.application;

import com.dallab.cattoy.domain.Category;
import com.dallab.cattoy.domain.CategoryRepository;
import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        category.changeWithDto(categoryDto);

        return category;
    }

    public void removeCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
