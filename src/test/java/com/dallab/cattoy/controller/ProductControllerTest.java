package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.ProductService;
import com.dallab.cattoy.domain.Product;
import com.dallab.cattoy.dto.ProductDto;
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

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Before
    public void mockProductService() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().name("쥐돌이").build());

        given(productService.getProducts()).willReturn(products);
    }

    @Test
    public void list() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("쥐돌이")));

        verify(productService).getProducts();
    }

    @Test
    public void detailWhenProductExists() throws Exception {
        Product product = Product.builder()
                .name("쥐돌이")
                .maker("달랩")
                .price(5000)
                .build();

        given(productService.getProduct(13L)).willReturn(product);

        mockMvc.perform(get("/products/13"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("쥐돌이")));

        verify(productService).getProduct(13L);
    }

    @Test
    public void detailWhenProductNotExists() throws Exception {
        given(productService.getProduct(13L))
                .willThrow(new EntityNotFoundException());

        mockMvc.perform(get("/products/13"))
                .andExpect(status().isNotFound());

        verify(productService).getProduct(13L);
    }

    @Test
    public void createWithValidAttributes() throws Exception {
        Product product = Product.builder().id(13L).build();

        given(productService.addProduct(any())).willReturn(product);

        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"낚시대\",\"maker\":\"달랩\"," +
                                "\"price\":5000}")
        )
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/products/13"));

        verify(productService).addProduct(any(Product.class));
    }

    @Test
    public void createWithInvalidAttributes() throws Exception {
        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"maker\":\"\"," +
                                "\"price\":1000}")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateWithValidAttributes() throws Exception {
        mockMvc.perform(
                patch("/products/13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"낚시대\",\"maker\":\"달랩\"," +
                                "\"price\":5000}")
        )
                .andExpect(status().isOk());

        ProductDto productDto = ProductDto.builder()
                .name("낚시대")
                .maker("달랩")
                .price(5000)
                .build();

        verify(productService).updateProduct(13L, productDto);
    }

    @Test
    public void updateWithInvalidAttributes() throws Exception {
        mockMvc.perform(
                patch("/products/13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"   \",\"maker\":\"\"," +
                                "\"price\":5000}")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateWithInvalidPrice() throws Exception {
        mockMvc.perform(
                patch("/products/13")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"낚시대\",\"maker\":\"\"," +
                                "\"price\":-5000}")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void destroy() throws Exception {
        mockMvc.perform(delete("/products/13"))
                .andExpect(status().isOk());

        verify(productService).removeProduct(13L);
    }

}
