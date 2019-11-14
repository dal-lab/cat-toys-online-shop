package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.CategoryService;
import com.dallab.cattoy.domain.Category;
import com.dallab.cattoy.dto.CategoryDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
@ActiveProfiles("test")
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String ADMIN_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjEsIm5hbWUiOiLqtIDrpqzsnpAifQ." +
            "EyrTP4OAGH9fA7lYxHrmJibf9QpBZnijtet-bWiTu2k";

    @MockBean
    private CategoryService categoryService;

    @Before
    public void setUp() {
        Category category = Category.builder().name("인형").build();

        List<Category> categories = Arrays.asList(category);

        given(categoryService.getCategories()).willReturn(categories);

        given(categoryService.getCategory(any())).willReturn(category);
    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("인형")));

        verify(categoryService).getCategories();
    }

    @Test
    public void detail() throws Exception {
        mockMvc.perform(get("/categories/13"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("인형")));

        verify(categoryService).getCategory(13L);
    }

    @Test
    public void create() throws Exception {
        Category category = Category.builder().id(13L).build();

        given(categoryService.addCategory(any())).willReturn(category);

        mockMvc.perform(
                post("/categories")
                        .header("Authorization", "Bearer " + ADMIN_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"인형\",\"sequence\":1}")
        )
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/categories/13"));

        verify(categoryService).addCategory(any(Category.class));
    }

    @Test
    public void update() throws Exception {
        Category category = Category.builder().id(13L).build();

        given(categoryService.updateCategory(eq(13L), any(CategoryDto.class)))
                .willReturn(category);

        mockMvc.perform(
                patch("/categories/13")
                        .header("Authorization", "Bearer " + ADMIN_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"인형/쿠션\",\"sequence\":2}")
        )
                .andExpect(status().isOk());

        CategoryDto categoryDto = CategoryDto.builder()
                .name("인형/쿠션")
                .sequence(2)
                .build();

        verify(categoryService).updateCategory(13L, categoryDto);
    }

    @Test
    public void destory() throws Exception {
        mockMvc.perform(delete("/categories/13"))
                .andExpect(status().isOk());

        verify(categoryService).removeCategory(13L);
    }

}
