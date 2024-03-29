package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)
@ActiveProfiles("test")
public class GreetingControllerTest {

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9." +
            "eyJ1c2VySWQiOjEzLCJuYW1lIjoi7YWM7Iqk7YSwIn0." +
            "yI3hxmFPMg4tbbxsUh11AzwfgbfxW_jrUaqFuzPTS64";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @BeforeEach
    public void mockGreetingService() {
        given(greetingService.getMessage(null)).willReturn("Hello");

        given(greetingService.getMessage("JOKER")).willReturn("Hello, JOKER");
    }

    @Test
    public void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")));

        verify(greetingService).getMessage(null);
    }

    @Test
    public void helloWithName() throws Exception {
        mockMvc.perform(get("/hello").param("name", "JOKER"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, JOKER")));

        verify(greetingService).getMessage("JOKER");
    }

    @Test
    public void helloWithJWT() throws Exception {
        mockMvc.perform(
                get("/hello")
                        .header("Authorization", "Bearer " + TOKEN)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"name\":\"테스터\"")
                ))
                .andExpect(content().string(containsString("Hello")));

        verify(greetingService).getMessage(null);
    }

}
