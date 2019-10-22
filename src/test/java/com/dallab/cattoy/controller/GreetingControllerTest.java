package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.GreetingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @Before
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

}
