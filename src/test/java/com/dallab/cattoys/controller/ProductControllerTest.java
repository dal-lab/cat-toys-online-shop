package com.dallab.cattoys.controller;

import com.dallab.cattoys.application.ProductService;
import com.dallab.cattoys.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void list() throws Exception {
        given(productService.getProducts()).willReturn(Arrays.asList(
                Product.builder()
                        .name("쥐돌이")
                        .maker("달랩")
                        .price(6000)
                        .build()
        ));

        mockMvc.perform(get("/products")
                // 최신 브라우저는 UTF-8이 기본이지만 MockMvc는 아니라 따로 지정해야 함.
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                // 인코딩이 올바른지 검사.
                .andExpect(content().encoding("UTF-8"))
                // JSON 데이터 검사.
                .andExpect(content().string(
                        containsString("\"name\":\"쥐돌이\"")
                ));

        verify(productService).getProducts();
    }

    @Test
    public void create() throws Exception {
        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"쥐돌이\",\"maker\":\"달랩\"," +
                                "\"price\":6000}")
        )
                .andExpect(status().isCreated());

        verify(productService).addProduct(any(Product.class));
    }

}
