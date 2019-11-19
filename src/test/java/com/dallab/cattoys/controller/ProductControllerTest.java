package com.dallab.cattoys.controller;

import com.dallab.cattoys.application.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean(ProductService.class)
    private ProductService productService;

    @Test
    public void list() throws Exception {
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
    }

}
