package com.dallab.cattoy.controller;

import com.dallab.cattoy.application.UserService;
import com.dallab.cattoy.domain.User;
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

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TokenController.class)
@ActiveProfiles("test")
public class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {
        User user = User.builder()
                .name("테스터")
                .email("tester@example.com")
                .build();

        given(userService.authenticate("tester@example.com", "pass"))
                .willReturn(user);

        given(userService.authenticate("x@example.com", "x"))
                .willReturn(null);
    }

    @Test
    public void signinWithValidAttributes() throws Exception {
        mockMvc.perform(
                post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"tester@example.com\"," +
                                "\"password\":\"pass\"}")
        )
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("accessToken")))
                .andExpect(content().string(containsString(".")));

        verify(userService).authenticate("tester@example.com", "pass");
    }

    @Test
    public void signinWithInvalidAttributes() throws Exception {
        mockMvc.perform(
                post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"x@example.com\"," +
                                "\"password\":\"x\"}")
        )
                .andExpect(status().isNotFound());

        verify(userService).authenticate("x@example.com", "x");
    }

    @Test
    public void signinWithNoPassword() throws Exception {
        mockMvc.perform(
                post("/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
        )
                .andExpect(status().isBadRequest());
    }

}
