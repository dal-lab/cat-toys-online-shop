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

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {

    private static final String TESTER_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjEzLCJuYW1lIjoi7YWM7Iqk7YSwIn0." +
            "yI3hxmFPMg4tbbxsUh11AzwfgbfxW_jrUaqFuzPTS64";

    private static final String ADMIN_TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjEsIm5hbWUiOiLqtIDrpqzsnpAifQ." +
            "EyrTP4OAGH9fA7lYxHrmJibf9QpBZnijtet-bWiTu2k";

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
    public void detailWithExistedProduct() throws Exception {
        Product product = Product.builder()
                .name("쥐돌이")
                .maker("달랩")
                .price(6000)
                .build();

        given(productService.getProduct(13L)).willReturn(product);

        mockMvc.perform(get("/products/13")
                // 최신 브라우저는 UTF-8이 기본이지만 MockMvc는 아니라 따로 지정해야 함.
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("쥐돌이")));

        verify(productService).getProduct(13L);
    }

    @Test
    public void detailWithNotExistedProduct() throws Exception {
        // ProductService는 없는 상품을 얻으려고 할 때 예외 발생.
        given(productService.getProduct(13L))
                .willThrow(EntityNotFoundException.class);

        mockMvc.perform(get("/products/13")
                // 최신 브라우저는 UTF-8이 기본이지만 MockMvc는 아니라 따로 지정해야 함.
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                // HTTP Status Code: 404 (Not Found)
                .andExpect(status().isNotFound());

        verify(productService).getProduct(13L);
    }

    @Test
    public void createWithAdminAuthentication() throws Exception {
        mockMvc.perform(
                post("/products")
                        .header("Authorization", "Bearer " + ADMIN_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"쥐돌이\",\"maker\":\"달랩\"," +
                                "\"price\":6000}")
        )
                .andExpect(status().isCreated());

        verify(productService).addProduct(any(Product.class));
    }

    @Test
    public void createWithTesterAuthentication() throws Exception {
        mockMvc.perform(
                post("/products")
                        .header("Authorization", "Bearer " + TESTER_TOKEN)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"쥐돌이\",\"maker\":\"달랩\"," +
                                "\"price\":6000}")
        )
                .andExpect(status().isForbidden());
    }

    @Test
    public void createWithoutAuthentication() throws Exception {
        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"쥐돌이\",\"maker\":\"달랩\"," +
                                "\"price\":6000}")
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(
                patch("/products/13")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\":\"쥐돌이\",\"maker\":\"달랩\"," +
                                "\"price\":6000}")
        )
                .andExpect(status().isOk());

        verify(productService).updateProduct(eq(13L), any(Product.class));
    }

    @Test
    public void destroy() throws Exception {
        mockMvc.perform(
                delete("/products/1")
        )
                .andExpect(status().isOk());

        verify(productService).deleteProduct(1L);
    }

}
